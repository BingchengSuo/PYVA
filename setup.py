import mph
from functions import *

def build():

    # model Start
    client = mph.start()
    model  = client.create('Model') 
    model  = model.java

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

    return model