import setup
import substrate
import etch
import deposit
import gate
import material
import es
import numpy as np
import config

# configuration
model = setup.build()
config.model               = model
config.numOfGDSlayers      = 6
config.gds_addr            = "dot_3layers.GDS"
config.substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs']       
config.layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120])               
config.substrate_size      = 40e3

# build substrate
substrate.build(config.substrate_layers, config.layer_thickness, config.substrate_size) # build substrate


# etch
mesa = etch.build(etch_name = "mesa_etch",\
            etch_depth = 4+10.5+120, gdslayerID=0, chamfer = 1 - 2*(4+10.5+120)/390)


# deposition
semi_dot = deposit.build(mesa = mesa, deposit_depth = -(4+10.5+120),\
                         thickness = 4, deposit_name = 'dot', gdslayerID = 4)

metal = deposit.build(mesa = mesa, deposit_depth = -(4+10.5+120),\
                         thickness = 50, deposit_name = 'metal', gdslayerID = 2)

# build gates
qpc     = gate.build(gate_depth = 35, gate_name = 'QPC', gdslayerID = 1)
plunger = gate.build(gate_depth = -(4+10.5+120-35), gate_name = 'Plunger', gdslayerID = 3)

# assign material
# material.assign(model, material = 'Al2O3', selList = [al2o3_mesa])

# assign electrostatics module
es.assign(es = "DomainTerminal", selList = [semi_dot])
es.assign(es = 'Ground', selList = [qpc, plunger])

fileName = 'test1.mph'
model.save(fileName)


