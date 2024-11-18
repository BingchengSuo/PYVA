import setup
import substrate
import etch
import deposit
import gate
import numpy as np

# build model
model = setup.build()

# build substrate
substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs','Al2O3']        # from bottom to top
layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120, 35])                    # from bottom to top [nm]
gds_layer_num       = 5
substrate_size      = 40e3                                                        # width x width (assume square shape)[nm]
substrate.build(model, substrate_layers, layer_thickness, substrate_size) # build substrate

# etch
addr = "C:/Users/DGG/Documents/comsol/GDS/dot_3layers.GDS"
etch.build(model, gds_address = addr, etch_name = "mesa",\
            etch_depth = 4+10.5+120+35, gdslayerID=0, numOflayers = gds_layer_num)

etch.build(model, gds_address = addr, etch_name = "metal_island",\
            etch_depth = 4+10.5+120+35, gdslayerID=2, numOflayers = gds_layer_num)

# deposition
deposit.build(model, gds_address = addr, deposit_depth = -(4+10.5+120+35),\
              thickness = 50, deposit_name = 'island', gdslayerID = 2, numOflayers = gds_layer_num)

deposit.build(model, gds_address = addr, deposit_depth = -(4+10.5+120+35),\
              thickness = 35, deposit_name = 'al2o3_mesa', gdslayerID = 0, numOflayers = gds_layer_num)

deposit.build(model, gds_address = addr, deposit_depth = -(4+10.5+120+35-50),\
              thickness = 35, deposit_name = 'al2o3_island', gdslayerID = 2, numOflayers = gds_layer_num)

deposit.build(model, gds_address = addr, deposit_depth = -(4+10.5+120+35),\
              thickness = 4, deposit_name = 'dot', gdslayerID = 4, numOflayers = gds_layer_num)

# build gates
gate.build(model, gds_address = addr, gate_depth = 0, gate_name = 'QPC', gdslayerID = 1, numOflayers = gds_layer_num)
gate.build(model, gds_address = addr, gate_depth = -(4+10.5+120+35-35), gate_name = 'Plunger', gdslayerID = 3, numOflayers = gds_layer_num)

fileName = 'test.mph'
model.save(fileName)







