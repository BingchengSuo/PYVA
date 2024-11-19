import numpy as np 

model               = 0
numOfGDSlayers      = 6
gds_addr            = "dot_3layers.GDS"
substrate_layers    = ['InAlAs','InGaAs','InAs','InGaAs','InAlAs'] 
layer_thickness     = np.array([1500, 10.5, 4, 10.5, 120]) 
substrate_size      = 40e3
dot_depth           = -134.5
etch_depth          = -134.5

mesa = 0 