import setup
import substrate
import etch
import deposit
import gate
import material
import es
import mesh
import config
from functions import *

def build():
    # configuration
    setup.build()

    # build substrate
    substrate.build()

    for params in config.etch_params:
        etch.build(**params)

    # deposition
    for params in config.deposit_params:
        deposit.build(**params)

    # gates
    for params in config.gate_params:
        config.gates.append(gate.build(**params))

    # assign material
    material.assign(material = 'Al2O3', selList = config.al2o3)

    # assign electrostatics module
    for i in range(len(config.dot)):
        es.assign(es = "DomainTerminal", selList = [config.dot[i],config.metal[i]])
    es.assign(es = 'Ground', selList = config.gates)

    # build mesh
    mesh.build()
    
def study():
    config.modelpy.solve('Study 1')
    config.cmatrix = getCmatrix()
    config.modelpy.clear()
    config.modelpy.reset()