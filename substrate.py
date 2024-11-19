from functions import *
import config

def build():
    model = config.model
    substrate_layers = config.substrate_layers
    layer_thickness  = config.layer_thickness
    substrate_size   = config.substrate_size
    material_rp = { # material relative permittivity
        "InAs"  : 15.1,
        "InGaAs": 14.1,
        "InAlAs": 13.3,
        "Al2O3" : 4.5,
    }
    substrate_thickness = layer_thickness.sum()

    # set global parameters
    model.param().set("substrate_size", str(substrate_size)+'[nm]')

    # create substrate layer geometry
    model.component("comp1").geom("geom1").create("blk1", "Block")
    model.component("comp1").geom("geom1").feature("blk1").label("substrate")
    model.component("comp1").geom("geom1").feature("blk1").set("size", jstr(["substrate_size", "substrate_size", str(substrate_thickness)+'[nm]']))
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center")
    model.component("comp1").geom("geom1").feature("blk1").set("pos", jstr(["0", "0", str(-substrate_thickness/2)+'[nm]']))
    for i in range(len(substrate_layers)-1):
        model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", str(layer_thickness[i])+'[nm]',i)
    model.component("comp1").geom("geom1").run("blk1")
    model.component("comp1").geom("geom1").run()
    write_log('blk1')

    # create material
    for i,material in enumerate(material_rp.keys()):
        model.component("comp1").material().create("mat"+str(i+1), "Common")
        model.component("comp1").material("mat"+str(i+1)).label(material)
        model.component("comp1").material("mat"+str(i+1)).tag(material)
        model.component("comp1").material(material).propertyGroup("def").set("relpermittivity", jstr([str(material_rp[material])]))

    # assign material to layers
    layer_idx = getLayerIndexDict(substrate_layers)
    for material in layer_idx.keys(): 
        model.component("comp1").material(material).selection().set(layer_idx[material])





