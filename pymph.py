import setup
import substrate
import etch
import deposit
import gate
import material
import es
import mesh
import numpy as np
import config

def build():
    # configuration
    model = setup.build()
    config.model               = model
    config.numOfGDSlayers      = 6
    config.gds_addr            = "double_dot.GDS"
    config.substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs']       
    config.layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120])      
    config.dot_depth           = -134.5    
    config.etch_depth          = -134.5     
    config.substrate_size      = 40e3
    config.dots_sep            = 2240
    config.numOfdots           = 2
    config.filename            = "test1.mph"

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
    qpc     = gate.build(gate_depth = 35, gate_name = 'QPC', gdslayerID = 1)
    plunger = gate.build(gate_depth = -(4+10.5+120-35), gate_name = 'Plunger', gdslayerID = 3)

    # assign material
    material.assign(material = 'Al2O3', selList = config.al2o3)

    # assign electrostatics module
    es.assign(es = "DomainTerminal", selList = [config.dot[0],config.metal[0]])
    es.assign(es = "DomainTerminal", selList = [config.dot[1],config.metal[1]])
    es.assign(es = 'Ground', selList = [qpc, plunger])

    # build mesh
    mesh.build()

    return config.model