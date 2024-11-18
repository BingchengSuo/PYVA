from functions import *

@CallCounter
def build(model, **kwargs):
    identifier   = CallCounter.total_call_count
    gds_address  = kwargs.get('gds_address', None)  # gds file address
    etch_name    = kwargs.get('etch_name', None)    # name of the etch 
    etch_depth   = kwargs.get('etch_depth', None)   # depth of the etch 
    id           = kwargs.get('gdslayerID', None)   # etch layer number 
    numOflayers  = kwargs.get('numOflayers', None)  # number of layers


    workplane_name = 'wp'  + str(identifier)
    import_name    = 'imp' + str(identifier)
    extrude_name   = 'ext' + str(identifier)
    dif_name       = 'dif' + str(identifier)

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
    model.component("comp1").geom("geom1").run(extrude_name)
    model.component("comp1").geom("geom1").create(dif_name, "Difference")
    main_body = read_log()
    model.component("comp1").geom("geom1").feature(dif_name).selection("input").set(main_body)
    model.component("comp1").geom("geom1").feature(dif_name).selection("input2").set(extrude_name)
    model.component("comp1").geom("geom1").run(dif_name)
    model.component("comp1").geom("geom1").feature(dif_name).label(etch_name)
    write_log(dif_name)
    model.component("comp1").geom("geom1").runPre("fin")
    model.component("comp1").geom("geom1").run()



