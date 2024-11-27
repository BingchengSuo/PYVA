import klayout.db as db
import config

class gdsBuilder:
    def __init__(self):
        self.layout, self.topcell = self.create_layout()
        self.layout.dbu   = 0.001 # unit in nanometers

    def create_layout(self):
        layout = db.Layout()
        topcell = layout.create_cell("TOP")
        return layout, topcell

    def trench(self, layer_index):
        width  = config.substrate_size
        height = config.trench_width + 2*abs(config.etch_depth)
        trench_layer = self.layout.layer(layer_index, 0)
        rectangle = db.Box(- width / 2, - height / 2, width / 2, height / 2)
        self.topcell.shapes(trench_layer).insert(rectangle)
        
    def qpc(self, layer_index):
        numofgates  = config.numOfdots+1
        width       = config.substrate_size
        gatesLayer = self.layout.layer(layer_index,0)
        
        r1 = db.Box(-config.dots_sep/2+50, (config.numOfdots*config.dots_sep)/2, -config.dots_sep/2-50, -(config.numOfdots*config.dots_sep)/2)
        r2 = db.Box(+config.dots_sep/2+50, (config.numOfdots*config.dots_sep)/2, +config.dots_sep/2-50, -(config.numOfdots*config.dots_sep)/2)
        region = db.Region(r1) + db.Region(r2)
        
        for i in range(numofgates):
            gate_y    = config.dots_sep/2 + (config.numOfdots-1)*config.dots_sep/2 - i*config.dots_sep
            rectangle = db.Box(-width/2, gate_y+50, width/2, gate_y-50)
            region    += db.Region(rectangle)
            
        rsub = db.Box(-225, (config.numOfdots*config.dots_sep), 225, -(config.numOfdots*config.dots_sep))
        regionSub = db.Region(rsub)
        for i in range(config.numOfdots):
            gate_y    = (config.numOfdots-1)*config.dots_sep/2 - config.dots_sep*i
            rsub = db.Box(-width/2, gate_y+225, width/2, gate_y-225)
            regionSub += db.Region(rsub)
        
        region.merge()
        region = region-regionSub
        region = region.round_corners(200, 2000, 64)
        self.topcell.shapes(gatesLayer).insert(region)
        
    def metal(self, layer_index):
        width = config.metal_size
        metalLayer = self.layout.layer(layer_index,0)
        metal = db.Box(-width/2, -width/2, width/2, width/2)
        region = db.Region(metal)
        region = region.round_corners(0, 100, 64)
        self.topcell.shapes(metalLayer).insert(region)
        
    def plunger(self, layer_index):
        plungerLayer = self.layout.layer(layer_index,0)
        width       = config.substrate_size
        region = []
        for i in range(config.numOfdots):
            gate_y    = (config.numOfdots-1)*config.dots_sep/2 - config.dots_sep*i
            r = db.Box(-width/2, gate_y+50, 0, gate_y-50)
            region = db.Region(r) + region
        rsub = db.Region(db.Box(-1500/2, (config.numOfdots*config.dots_sep), 1500/2, -(config.numOfdots*config.dots_sep)))
        region = region - rsub
        region = region.round_corners(200, 2000, 64)
        self.topcell.shapes(plungerLayer).insert(region)
        
    def al2o3(self, layer_index):
        al2o3Layer = self.layout.layer(layer_index,0)
        width       = config.substrate_size
        r = db.Box(-width/2, -width/2, width/2, width/2)
        self.topcell.shapes(al2o3Layer).insert(r)
        
    def dot(self, layer_index):
        radius = config.dots_sep/2 - 10
        dotLayer = self.layout.layer(layer_index,0)
        region = db.Region(db.Box(-radius, -radius, radius, radius))
        region = region.round_corners(radius, 2000, 128)
        rsub   = db.Region(db.Box(-4000/2, config.trench_width/2 +5, 4000/2, -config.trench_width/2 -5))
        region = region - rsub
        self.topcell.shapes(dotLayer).insert(region)
        
    def save_layout(self, filename):
        self.layout.write(filename)
        
    def build(self):
        self.trench(0)
        self.qpc(1)
        self.metal(2)
        self.plunger(3)
        self.al2o3(4)
        self.dot(5)
        self.save_layout(config.gds_addr)