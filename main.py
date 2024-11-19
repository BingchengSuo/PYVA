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
config.gds_addr            = "single_dot.GDS"
config.substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs']       
config.layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120])      
config.dot_depth           = -134.5    
config.etch_depth          = -134.5     
config.substrate_size      = 40e3

# build substrate
substrate.build()


# etch
config.mesa = etch.build(etch_name = "mesa_etch", gdslayerID=0, chamfer = 1 - 2*(4+10.5+120)/390)

# deposition
config.dot    = deposit.build(thickness = 4, deposit_name = 'dot', gdslayerID = 4)
config.metal  = deposit.build(thickness = 100, deposit_name = 'metal', gdslayerID = 2)
Al2O3         = deposit.build(thickness = 35, deposit_name = 'Al2O3', gdslayerID = 5)

# gates
qpc     = gate.build(gate_depth = 35, gate_name = 'QPC', gdslayerID = 1)
plunger = gate.build(gate_depth = -(4+10.5+120-35), gate_name = 'Plunger', gdslayerID = 3)

# assign material
material.assign(material = 'Al2O3', selList = [Al2O3])

# assign electrostatics module
es.assign(es = "DomainTerminal", selList = [config.dot, config.metal])
es.assign(es = 'Ground', selList = [qpc, plunger])

fileName = 'test1.mph'
model.save(fileName)


