import config

def build():
    print(f"building the mesh     \033[K", end='\r',flush = True)
    model = config.model
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4)
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1")
    model.component("comp1").mesh("mesh1").run()
    fileName = config.mph_addr
    config.model.save(fileName)




