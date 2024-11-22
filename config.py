import numpy as np 

# default unit is [nm]

model               = 0                     # mph model register
numOfGDSlayers      = 6                     # number of gds design layers
gds_addr            = "dot_3layers.GDS"     # gds address 
substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs'] # substrate layers from bottom to top
layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120])         # layer thickness of each layer
substrate_size      = 40e3      # size/width of the substarte
dot_depth           = -134.5    # the depth of the dot 
etch_depth          = -134.5    # the depth of the etch 
numOfdots           = 1         # number of dots
dots_sep            = 1         # inter dot seperation
filename            = 0 

mesa  = []
metal = []
dot   = []
al2o3 = []