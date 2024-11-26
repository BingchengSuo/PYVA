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

    # etch
    etch.build(etch_name = "mesa_etch", gdslayerID=0, chamfer = 1 - 2*(4+10.5+120)/390)

    # deposition
    deposit.build(thickness = 4, deposit_name = 'dot', gdslayerID = 5)
    deposit.build(thickness = 100, deposit_name = 'metal', gdslayerID = 2)
    deposit.build(thickness = 35, deposit_name = 'Al2O3', gdslayerID = 4)
    deposit.build(thickness = 35, deposit_name = 'metal_Al2O3', gdslayerID = 2)

    # gates
    qpc     = gate.build(gate_depth = config.qpc_depth, gate_name = 'QPC', gdslayerID = 1)
    plunger = gate.build(gate_depth = config.plunger_depth, gate_name = 'Plunger', gdslayerID = 3)

    # assign material
    material.assign(material = 'Al2O3', selList = config.al2o3)

    # assign electrostatics module
    es.assign(es = "DomainTerminal", selList = [config.dot[0],config.metal[0]])
    es.assign(es = "DomainTerminal", selList = [config.dot[1],config.metal[1]])
    es.assign(es = 'Ground', selList = [qpc, plunger])

    fileName = config.mph_addr
    config.model.save(fileName)
    # build mesh
    #mesh.build()
    
def study():
    config.modelpy.solve('Study 1')
    config.cmatrix = getCmatrix()
    config.modelpy.clear()
    config.modelpy.reset()