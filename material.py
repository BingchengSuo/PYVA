from functions import *

@CallCounter
def assign(model, **kwargs):
    identifier        = CallCounter.total_call_count
    material          = kwargs.get('material', None)    # selection list
    selList           = kwargs.get('selList', None)    # selection list

    sel_name = 'sel' + str(identifier) # name of the selection

    model.component("comp1").geom("geom1").create(sel_name, "ExplicitSelection")
    model.component("comp1").geom("geom1").feature(sel_name).label(material+'sel')
    model.component("comp1").geom("geom1").feature(sel_name).selection("selection").init()
    model.component("comp1").geom("geom1").feature(sel_name).selection("selection").set(jstr(selList))
    model.component("comp1").geom("geom1").run(sel_name)

    model.component("comp1").material(material).selection().named("geom1_"+sel_name+"_dom")

