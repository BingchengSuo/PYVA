from functions import *
import config

@CallCounter
def build(**kwargs): 
    identifier        = CallCounter.total_call_count
    deposit_depth     = kwargs.get('deposit_depth', None)  # depth of the deposition (negative number [nm])
    deposit_thickness = kwargs.get('thickness', None)      # deposit thickness [nm]
    deposit_name      = kwargs.get('deposit_name', None)   # name of the deposition 
    id                = kwargs.get('gdslayerID', None)        # deposit layer number 
    mesa              = kwargs.get('mesa', None)    # number of layers
    numOflayers       = config.numOfGDSlayers
    gds_address       = config.gds_addr
    model             = config.model

    workplane_name = 'wp'  + str(identifier)
    import_name    = 'imp' + str(identifier)
    extrude_name   = 'ext' + str(identifier)
    dif_name       = 'dif' + str(identifier)
    move_name      = 'mov' + str(identifier)
    int_name       = 'int' + str(identifier)

    if deposit_name == 'dot':
        model.component("comp1").geom("geom1").create(workplane_name, "WorkPlane")
        model.component("comp1").geom("geom1").feature(workplane_name).set("unite", True)
        model.component("comp1").geom("geom1").feature(workplane_name).set("quickz", str(deposit_depth)+"[nm]")
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
        return extrude_name # return the extrude name for selection 
    
    else:
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
        model.component("comp1").geom("geom1").feature(dif_name).selection("input2").set(mesa)
        model.component("comp1").geom("geom1").feature(dif_name).set("keepsubtract", True)
        model.component("comp1").geom("geom1").run(dif_name)

        model.component("comp1").geom("geom1").create(move_name, "Move")
        model.component("comp1").geom("geom1").feature(move_name).selection("input").set(dif_name)
        model.component("comp1").geom("geom1").feature(move_name).set("displz", str(-deposit_thickness)+"[nm]")
        model.component("comp1").geom("geom1").run(move_name)

        model.component("comp1").geom("geom1").create(int_name, "Intersection")
        model.component("comp1").geom("geom1").feature(int_name).selection("input").set(mesa, move_name)
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
        return int_name_2




