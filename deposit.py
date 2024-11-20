from functions import *
import config

@CallCounter
def build(**kwargs): 
    identifier        = CallCounter.total_call_count
    deposit_thickness = kwargs.get('thickness', None)      # deposit thickness [nm]
    deposit_name      = kwargs.get('deposit_name', None)   # name of the deposition 
    id                = kwargs.get('gdslayerID', None)        # deposit layer number 
    mesa              = config.mesa
    numOflayers       = config.numOfGDSlayers
    gds_address       = config.gds_addr
    model             = config.model
    numOfdots         = config.numOfdots
    dot_sep           = config.dots_sep
    mv                = ((numOfdots-1)/2) * dot_sep

    workplane_name = 'wp'  + str(identifier)
    import_name    = 'imp' + str(identifier)
    extrude_name   = 'ext' + str(identifier)
    dif_name       = 'dif' + str(identifier)
    move_name      = 'mov' + str(identifier)
    int_name       = 'int' + str(identifier)
    arr_name       = 'arr' + str(identifier)

    if deposit_name == 'dot':
        model.component("comp1").geom("geom1").create(workplane_name, "WorkPlane")
        model.component("comp1").geom("geom1").feature(workplane_name).set("unite", True)
        model.component("comp1").geom("geom1").feature(workplane_name).set("quickz", str(config.dot_depth)+"[nm]")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature(workplane_name).geom().create(import_name, "Import")
        model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name)\
            .set("filename", gds_address)
        
        for i in range(numOflayers):
            if i != id:
                model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name).setIndex("importlayer", False, jint(i))
        
        model.component("comp1").geom("geom1").feature(workplane_name).geom().run(import_name)
        model.component("comp1").geom("geom1").feature(workplane_name).label(deposit_name+"_wp")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature().create(extrude_name, "Extrude")
        model.component("comp1").geom("geom1").feature(extrude_name).set("extrudefrom", "workplane")
        model.component("comp1").geom("geom1").feature(extrude_name).set("inputhandling", "remove")
        model.component("comp1").geom("geom1").feature(extrude_name).setIndex("distance", str(deposit_thickness)+"[nm]", 0)
        model.component("comp1").geom("geom1").feature(extrude_name).label(deposit_name+"_ext")
        model.component("comp1").geom("geom1").run(extrude_name)

        model.component("comp1").geom("geom1").create(move_name, "Move")
        model.component("comp1").geom("geom1").feature(move_name).selection("input").set(extrude_name)
        model.component("comp1").geom("geom1").feature(move_name).set("disply", str(-mv)+'[nm]')
        model.component("comp1").geom("geom1").run(move_name)
        model.component("comp1").geom("geom1").create(arr_name, "Array")
        model.component("comp1").geom("geom1").feature(arr_name).selection("input").set(move_name)
        model.component("comp1").geom("geom1").feature(arr_name).set("fullsize", jarr([1, numOfdots, 1]))
        model.component("comp1").geom("geom1").feature(arr_name).set("displ", jstr(["0", str(dot_sep)+"[nm]", "0"]))
        model.component("comp1").geom("geom1").run(arr_name)
        config.dot = [f"{arr_name}(1,{i},1)" for i in range(1, numOfdots + 1)]
    
    elif deposit_name == 'metal':
        model.component("comp1").geom("geom1").create(workplane_name, "WorkPlane")
        model.component("comp1").geom("geom1").feature(workplane_name).set("unite", True)
        model.component("comp1").geom("geom1").feature(workplane_name).set("quickz", "800[nm]")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature(workplane_name).geom().create(import_name, "Import")
        model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name)\
            .set("filename", gds_address)
        for i in range(numOflayers):
            if i != id:
                model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name).setIndex("importlayer", False, jint(i))
        

        model.component("comp1").geom("geom1").feature(workplane_name).geom().run(import_name)
        model.component("comp1").geom("geom1").feature(workplane_name).label(deposit_name+"_wp")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature().create(extrude_name, "Extrude")
        model.component("comp1").geom("geom1").feature(extrude_name).set("reverse", True)
        model.component("comp1").geom("geom1").feature(extrude_name).set("inputhandling", "remove")
        model.component("comp1").geom("geom1").feature(extrude_name).setIndex("distance", "1000[nm]", 0)
        model.component("comp1").geom("geom1").run(extrude_name)

        model.component("comp1").geom("geom1").create(move_name, "Move")
        model.component("comp1").geom("geom1").feature(move_name).selection("input").set(extrude_name)
        model.component("comp1").geom("geom1").feature(move_name).set("disply", str(-mv)+'[nm]')
        model.component("comp1").geom("geom1").run(move_name)

        model.component("comp1").geom("geom1").create(dif_name, "Difference")
        model.component("comp1").geom("geom1").feature(dif_name).selection("input").set(move_name)
        model.component("comp1").geom("geom1").feature(dif_name).selection("input2").set(mesa)
        model.component("comp1").geom("geom1").feature(dif_name).set("keepsubtract", True)
        model.component("comp1").geom("geom1").run(dif_name)

        move_name_2 = move_name + "00"
        model.component("comp1").geom("geom1").create(move_name_2, "Move")
        model.component("comp1").geom("geom1").feature(move_name_2).selection("input").set(dif_name)
        model.component("comp1").geom("geom1").feature(move_name_2).set("displz", str(-deposit_thickness)+"[nm]")
        model.component("comp1").geom("geom1").run(move_name_2)

        model.component("comp1").geom("geom1").create(int_name, "Intersection")
        model.component("comp1").geom("geom1").feature(int_name).selection("input").set(mesa, move_name_2)
        model.component("comp1").geom("geom1").feature(int_name).set("keep", True)
        model.component("comp1").geom("geom1").run(int_name)

        move_name_3 = move_name + "10"
        model.component("comp1").geom("geom1").create(move_name_3, "Move")
        model.component("comp1").geom("geom1").feature(move_name_3).set("displz", str(deposit_thickness)+"[nm]")
        model.component("comp1").geom("geom1").feature(move_name_3).selection("input").set(int_name)
        model.component("comp1").geom("geom1").run(move_name_3)

        int_name_2 = int_name + "00"
        model.component("comp1").geom("geom1").create(int_name_2, "Intersection")
        model.component("comp1").geom("geom1").feature(int_name_2).selection("input").set(move_name_3, move_name_2)
        model.component("comp1").geom("geom1").run(int_name_2)
        model.component("comp1").geom("geom1").feature(int_name).set("intbnd", False)
        model.component("comp1").geom("geom1").feature(int_name_2).set("intbnd", False)

        model.component("comp1").geom("geom1").create(arr_name, "Array")
        model.component("comp1").geom("geom1").feature(arr_name).selection("input").set(int_name_2)
        model.component("comp1").geom("geom1").feature(arr_name).set("fullsize", jarr([1, numOfdots, 1]))
        model.component("comp1").geom("geom1").feature(arr_name).set("displ", jstr(["0", str(dot_sep)+"[nm]", "0"]))
        model.component("comp1").geom("geom1").run(arr_name)
        model.component("comp1").geom("geom1").run()
        config.metal = [f"{arr_name}(1,{i},1)" for i in range(1, numOfdots + 1)]

    elif deposit_name == 'Al2O3':
        metal = config.metal
        model.component("comp1").geom("geom1").create(workplane_name, "WorkPlane")
        model.component("comp1").geom("geom1").feature(workplane_name).set("unite", True)
        model.component("comp1").geom("geom1").feature(workplane_name).set("quickz", "800[nm]")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature(workplane_name).geom().create(import_name, "Import")
        model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name)\
            .set("filename", gds_address)
        for i in range(numOflayers):
            if i != id:
                model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name).setIndex("importlayer", False, jint(i))
        

        model.component("comp1").geom("geom1").feature(workplane_name).geom().run(import_name)
        model.component("comp1").geom("geom1").feature(workplane_name).label(deposit_name+"_wp")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature().create(extrude_name, "Extrude")
        model.component("comp1").geom("geom1").feature(extrude_name).set("reverse", True)
        model.component("comp1").geom("geom1").feature(extrude_name).set("inputhandling", "remove")
        model.component("comp1").geom("geom1").feature(extrude_name).setIndex("distance", "1000[nm]", 0)
        model.component("comp1").geom("geom1").run(extrude_name)

        model.component("comp1").geom("geom1").create(dif_name, "Difference")
        model.component("comp1").geom("geom1").feature(dif_name).selection("input").set(extrude_name)
        output = [mesa] + metal
        model.component("comp1").geom("geom1").feature(dif_name).selection("input2").set(*output)

        model.component("comp1").geom("geom1").feature(dif_name).set("keepsubtract", True)
        model.component("comp1").geom("geom1").run(dif_name)

        model.component("comp1").geom("geom1").create(move_name, "Move")
        model.component("comp1").geom("geom1").feature(move_name).selection("input").set(dif_name)
        model.component("comp1").geom("geom1").feature(move_name).set("displz", str(-deposit_thickness)+"[nm]")
        model.component("comp1").geom("geom1").run(move_name)

        output = [mesa] + [move_name]
        model.component("comp1").geom("geom1").create(int_name, "Intersection")
        model.component("comp1").geom("geom1").feature(int_name).selection("input").set(*output)
        model.component("comp1").geom("geom1").feature(int_name).set("keep", True)
        model.component("comp1").geom("geom1").run(int_name)

        move_name_2 = move_name + "00"
        model.component("comp1").geom("geom1").create(move_name_2, "Move")
        model.component("comp1").geom("geom1").feature(move_name_2).set("displz", str(deposit_thickness)+"[nm]")
        model.component("comp1").geom("geom1").feature(move_name_2).selection("input").set(int_name)
        model.component("comp1").geom("geom1").run(move_name_2)

        int_name_2 = int_name + "00"
        model.component("comp1").geom("geom1").create(int_name_2, "Intersection")
        model.component("comp1").geom("geom1").feature(int_name_2).selection("input").set(move_name, move_name_2)
        model.component("comp1").geom("geom1").run(int_name_2)
        model.component("comp1").geom("geom1").feature(int_name).set("intbnd", False)
        model.component("comp1").geom("geom1").run()
        (config.al2o3).append(int_name_2)

    elif deposit_name == 'metal_Al2O3':
        metal = config.metal
        model.component("comp1").geom("geom1").create(workplane_name, "WorkPlane")
        model.component("comp1").geom("geom1").feature(workplane_name).set("unite", True)
        model.component("comp1").geom("geom1").feature(workplane_name).set("quickz", "800[nm]")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature(workplane_name).geom().create(import_name, "Import")
        model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name)\
            .set("filename", gds_address)
        for i in range(numOflayers):
            if i != id:
                model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name).setIndex("importlayer", False, jint(i))
        

        model.component("comp1").geom("geom1").feature(workplane_name).geom().run(import_name)
        model.component("comp1").geom("geom1").feature(workplane_name).label(deposit_name+"_wp")
        model.component("comp1").geom("geom1").run(workplane_name)
        model.component("comp1").geom("geom1").feature().create(extrude_name, "Extrude")
        model.component("comp1").geom("geom1").feature(extrude_name).set("reverse", True)
        model.component("comp1").geom("geom1").feature(extrude_name).set("inputhandling", "remove")
        model.component("comp1").geom("geom1").feature(extrude_name).setIndex("distance", "1000[nm]", 0)
        model.component("comp1").geom("geom1").run(extrude_name)

        model.component("comp1").geom("geom1").create(move_name, "Move")
        model.component("comp1").geom("geom1").feature(move_name).selection("input").set(extrude_name)
        model.component("comp1").geom("geom1").feature(move_name).set("disply", str(-mv)+'[nm]')
        model.component("comp1").geom("geom1").run(move_name)

        model.component("comp1").geom("geom1").create(dif_name, "Difference")
        model.component("comp1").geom("geom1").feature(dif_name).selection("input").set(move_name)
        model.component("comp1").geom("geom1").feature(dif_name).selection("input2").set(metal[0])
        model.component("comp1").geom("geom1").feature(dif_name).set("keepsubtract", True)
        model.component("comp1").geom("geom1").run(dif_name)

        move_name_2 = move_name + "00"
        model.component("comp1").geom("geom1").create(move_name_2, "Move")
        model.component("comp1").geom("geom1").feature(move_name_2).selection("input").set(dif_name)
        model.component("comp1").geom("geom1").feature(move_name_2).set("displz", str(-deposit_thickness)+"[nm]")
        model.component("comp1").geom("geom1").run(move_name_2)

        model.component("comp1").geom("geom1").create(int_name, "Intersection")
        model.component("comp1").geom("geom1").feature(int_name).selection("input").set(metal[0], move_name_2)
        model.component("comp1").geom("geom1").feature(int_name).set("keep", True)
        model.component("comp1").geom("geom1").run(int_name)

        move_name_3 = move_name + "10"
        model.component("comp1").geom("geom1").create(move_name_3, "Move")
        model.component("comp1").geom("geom1").feature(move_name_3).set("displz", str(deposit_thickness)+"[nm]")
        model.component("comp1").geom("geom1").feature(move_name_3).selection("input").set(int_name)
        model.component("comp1").geom("geom1").run(move_name_3)

        int_name_2 = int_name + "00"
        model.component("comp1").geom("geom1").create(int_name_2, "Intersection")
        model.component("comp1").geom("geom1").feature(int_name_2).selection("input").set(move_name_3, move_name_2)
        model.component("comp1").geom("geom1").run(int_name_2)
        model.component("comp1").geom("geom1").feature(int_name).set("intbnd", False)
        model.component("comp1").geom("geom1").feature(int_name_2).set("intbnd", False)

        model.component("comp1").geom("geom1").create(arr_name, "Array")
        model.component("comp1").geom("geom1").feature(arr_name).selection("input").set(int_name_2)
        model.component("comp1").geom("geom1").feature(arr_name).set("fullsize", jarr([1, numOfdots, 1]))
        model.component("comp1").geom("geom1").feature(arr_name).set("displ", jstr(["0", str(dot_sep)+"[nm]", "0"]))
        model.component("comp1").geom("geom1").run(arr_name)
        model.component("comp1").geom("geom1").run()
        config.al2o3 += [f"{arr_name}(1,{i},1)" for i in range(1, numOfdots + 1)]





