from functions import *
import config

@CallCounter
def build(**kwargs):
    print(f"etching mesa\033[K", end='\r',flush = True)

    identifier   = CallCounter.total_call_count
    etch_name    = kwargs.get('etch_name', None)    # name of the etch 
    id           = kwargs.get('gdslayerID', None)   # etch layer number 
    chamfer      = config.trench_chamfer 
    numOflayers  = config.numOfGDSlayers
    gds_address  = config.gds_addr
    model        = config.model
    etch_depth   = -config.etch_depth
    numOfdots    = config.numOfdots
    dot_sep      = config.dots_sep
    mv           = ((numOfdots-1)/2) * dot_sep

    workplane_name = 'wp'  + str(identifier)
    import_name    = 'imp' + str(identifier)
    extrude_name   = 'ext' + str(identifier)
    dif_name       = 'dif' + str(identifier)
    move_name      = 'mov' + str(identifier)
    arr_name       = 'arr' + str(identifier)

    model.component("comp1").geom("geom1").create(workplane_name, "WorkPlane")
    model.component("comp1").geom("geom1").feature(workplane_name).set("unite", True)
    model.component("comp1").geom("geom1").feature(workplane_name).geom().create(import_name, "Import")
    model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name)\
        .set("filename", gds_address)
    
    for i in range(numOflayers):
        if i != id:
            model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name).setIndex("importlayer", False, jint(i))
    
    model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name).label(etch_name)
    model.component("comp1").geom("geom1").feature(workplane_name).label(etch_name+"_wp")
    model.component("comp1").geom("geom1").feature(workplane_name).geom().run(import_name)
    model.component("comp1").geom("geom1").feature().create(extrude_name, "Extrude")
    model.component("comp1").geom("geom1").feature(extrude_name).set("workplane", workplane_name)
    model.component("comp1").geom("geom1").feature(extrude_name).selection("input").set(workplane_name)
    model.component("comp1").geom("geom1").feature(extrude_name).set("inputhandling", "remove")
    model.component("comp1").geom("geom1").feature(extrude_name).label(etch_name+"_Etch")
    model.component("comp1").geom("geom1").feature(extrude_name).set("reverse", True)
    model.component("comp1").geom("geom1").feature(extrude_name).setIndex("distance", str(etch_depth)+'[nm]', 0)
    model.component("comp1").geom("geom1").feature(extrude_name).setIndex("scale", jpype.JDouble(chamfer), 0, 1)
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
    model.component("comp1").geom("geom1").create(dif_name, "Difference")
    model.component("comp1").geom("geom1").feature(dif_name).selection("input").set(config.mesa)
    model.component("comp1").geom("geom1").feature(dif_name).selection("input2").set(arr_name)
    model.component("comp1").geom("geom1").run(dif_name)
    config.mesa = dif_name


