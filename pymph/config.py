import numpy as np 

# user defined parameters (default unit is [nm])
model               = 0                                            # mph java model register
modelpy             = 0                                            # mph python model register
numOfGDSlayers      = 6                                            # number of gds design layers
gds_addr            = "double_dots.gds"                            # gds address 
substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs'] # substrate layers from bottom to top
layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120])         # layer thickness of each layer
substrate_size      = 40e3      # size/width of the substarte
dot_depth           = -134.5    # the depth of the dot 
etch_depth          = -134.5    # the depth of the etch 
numOfdots           = 2         # number of dots
dots_sep            = 1         # inter dot seperation
metal_size          = 1         # size of the metal island
trench_width        = 0
trench_chamfer      = trench_width/(2*abs(etch_depth)+trench_width)       # the width of the trench for the actual device
qpc_depth           = 1         # the depth of qpcs
plunger_depth       = 1         # the depth of plunger gates
mph_addr            = 0         # filenames
mesh                = 3

etch_params = [
    {"etch_name": "mesa_etch", "gdslayerID": 0}
]

deposit_params = [
    {"thickness": 4,   "deposit_name": 'dot'        , "gdslayerID": 5},
    {"thickness": 100, "deposit_name": 'metal'      , "gdslayerID": 2},
    {"thickness": 35,  "deposit_name": 'Al2O3'      , "gdslayerID": 4},
    {"thickness": 35,  "deposit_name": 'metal_Al2O3', "gdslayerID": 2}
]

gate_params = [
    {"gate_depth": qpc_depth,     "gate_name": 'QPC',     "gdslayerID": 1},
    {"gate_depth": plunger_depth, "gate_name": 'Plunger', "gdslayerID": 3}
]

# program placeholder / identifier
mesa  = [] # mesa / etched mesa
metal = [] # metallic island / metallic dot
dot   = [] # semiconductor quantum dot 
al2o3 = [] # al2o3 
gates = [] # qpc + plunger gates

# program output
cmatrix = [] # maxwell's capactiance matrix