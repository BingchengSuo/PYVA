import setup
import substrate
import etch
import deposit
import gate
import material
import es
import numpy as np

# build model
model = setup.build()

# build substrate
substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs']        # from bottom to top
layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120])                # from bottom to top [nm]
gds_layer_num       = 6
substrate_size      = 40e3                                                # width x width (assume square shape)[nm]
substrate.build(model, substrate_layers, layer_thickness, substrate_size) # build substrate

# etch
# addr = "C:/Users/DGG/Documents/comsol/GDS/dot_3layers.GDS"
addr = "/Users/bing/Desktop/COMSOL_pyva/GDS/dot_3layers.GDS"
etch.build(model, gds_address = addr, etch_name = "mesa_etch",\
            etch_depth = 4+10.5+120, gdslayerID=0, chamfer = 1 - 2*(4+10.5+120)/390, numOflayers = gds_layer_num)


# deposition

semi_dot     = deposit.build(model, gds_address = addr, deposit_depth = -(4+10.5+120),\
                             thickness = 4, deposit_name = 'semi_dot', gdslayerID = 4, numOflayers = gds_layer_num)

# build gates
qpc     = gate.build(model, gds_address = addr, gate_depth = 35, gate_name = 'QPC', gdslayerID = 1, numOflayers = gds_layer_num)
plunger = gate.build(model, gds_address = addr, gate_depth = -(4+10.5+120-35), gate_name = 'Plunger', gdslayerID = 3, numOflayers = gds_layer_num)

# assign material
# material.assign(model, material = 'Al2O3', selList = [al2o3_mesa])

# assign electrostatics module
es.assign(model, es = "DomainTerminal", selList = [semi_dot])
es.assign(model, es = 'Ground', selList = [qpc, plunger])

fileName = 'test1.mph'
model.save(fileName)


