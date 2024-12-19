## parameter configurations 
import numpy as np

## user defined parameters (default unit is [nm])
## z=0 corresponds to the top surface of the heterostructure
model               = 0                                             # mph java model register
modelpy             = 0                                             # mph python model register
numOfGDSlayers      = 0                                             # number of gds design layers
substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs']  # substrate layers from bottom to top
layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120])          # layer thickness of each layer
substrate_size      = 0                                             # size/width of the substarte
dot_depth           = 0                                             # the depth of the dot 
etch_depth          = 0                                             # the depth of the etch 
numOfdots           = 2                                             # number of dots
dots_sep            = 1                                             # inter dot seperation
metal_size          = 1                                             # size of the metal island
trench_width        = 0                                             # trench real width
trench_chamfer      = 0                                             # trench chamfer
qpc_depth           = 1                                             # the depth of qpcs
plunger_depth       = 1                                             # the depth of plunger gates

# file address
fileName            = 'name'                                        # filename / identifier
drive_addr          = 'addr'                                        # drive address
local_addr          = 'addr'                                        # local address
mph_addr            = local_addr + 'mph/' + fileName + '.mph'       # mph file save address
npz_addr            = drive_addr + 'npz/' + fileName + '.npz'       # npz file save address
gds_addr            = drive_addr + 'gds/' + fileName + '.gds'       # gds file save address
screen_gate_offset  = 0
mesh                = 3                                             # mesh mode

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