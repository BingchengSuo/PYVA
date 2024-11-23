import mph
import config
from functions import *

def build():

    # model Start
    print(f"setting up the model\033[K", end='\r', flush = True)

    client = mph.start()
    config.modelpy  = client.create('Model') 
    model           = config.modelpy.java

    # create component nodes
    model.component().create("comp1", True)
    model.component("comp1").geom().create("geom1", 3)
    model.component("comp1").mesh().create("mesh1")
    model.component("comp1").physics().create("es", "Electrostatics", "geom1")

    # create study
    model.study().create("std1")
    model.study("std1").create("stssw", "StationarySourceSweep")
    model.study("std1").feature("stssw").set("solnum", "auto")
    model.study("std1").feature("stssw").set("notsolnum", "auto")
    model.study("std1").feature("stssw").set("outputmap", jstr(''))
    model.study("std1").feature("stssw").set("ngenAUX", "1")
    model.study("std1").feature("stssw").set("goalngenAUX", "1")
    model.study("std1").feature("stssw").set("ngenAUX", "1")
    model.study("std1").feature("stssw").set("goalngenAUX", "1")
    model.study("std1").feature("stssw").setSolveFor("/physics/es", True)

    config.model = model