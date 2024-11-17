from functions import *

@CallCounter
def build(model, **kwargs):
    identifier        = CallCounter.total_call_count
    gds_address       = kwargs.get('gds_address', None)    # gds file address
    deposit_depth     = kwargs.get('deposit_depth', None)  # depth of the deposition (negative number [nm])
    deposit_thickness = kwargs.get('thickness', None)      # deposit thickness [nm]
    deposit_name      = kwargs.get('deposit_name', None)   # name of the deposition 
    id                = kwargs.get('gdslayerID', None)        # deposit layer number 
    numOflayers       = kwargs.get('numOflayers', None)    # number of layers

    workplane_name = 'wp'  + str(identifier)
    import_name    = 'imp' + str(identifier)
    extrude_name   = 'ext' + str(identifier)

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




