import config

def build():
    print(f"building the mesh     \033[K", end='\r',flush = True)
    model = config.model
    model.component("comp1").mesh("mesh1")
    model.component("comp1").mesh("mesh1").autoMeshSize(config.mesh)
    model.component("comp1").mesh("mesh1").run()
    fileName = config.mph_addr
    config.model.save(fileName)




