from functions import *
import config

@CallCounter
def build(**kwargs):
    identifier        = CallCounter.total_call_count
    gate_depth        = kwargs.get('gate_depth', None)     # depth of the deposition (negative number [nm])
    gate_name         = kwargs.get('gate_name', None)      # name of the deposition
    id                = kwargs.get('gdslayerID', None)     # deposit layer number 
    numOflayers  = config.numOfGDSlayers
    gds_address  = config.gds_addr
    model        = config.model

    print(f"building {gate_name} gates\033[K", end='\r',flush = True)

    workplane_name = 'wp'  + str(identifier)
    import_name    = 'imp' + str(identifier)

    model.component("comp1").geom("geom1").create(workplane_name, "WorkPlane")
    model.component("comp1").geom("geom1").feature(workplane_name).set("unite", True)
    model.component("comp1").geom("geom1").feature(workplane_name).set("quickz", str(gate_depth)+"[nm]")
    model.component("comp1").geom("geom1").run(workplane_name)
    model.component("comp1").geom("geom1").feature(workplane_name).geom().create(import_name, "Import")
    model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name)\
        .set("filename", gds_address)
    
    for i in range(numOflayers):
        if i != id:
            model.component("comp1").geom("geom1").feature(workplane_name).geom().feature(import_name).setIndex("importlayer", False, jint(i))
    
    model.component("comp1").geom("geom1").feature(workplane_name).label(gate_name+"_wp")
    model.component("comp1").geom("geom1").feature(workplane_name).geom().run(import_name)
    model.component("comp1").geom("geom1").feature(workplane_name).geom().run(import_name)
    model.component("comp1").geom("geom1").feature(workplane_name).set("selresult", True)
    model.component("comp1").geom("geom1").feature(workplane_name).set("selresultshow", "all")
    model.component("comp1").geom("geom1").feature(workplane_name).set("color", "18")
    model.component("comp1").geom("geom1").run()
    return workplane_name
