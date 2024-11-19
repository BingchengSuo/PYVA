from functions import *

@CallCounter
def assign(model, **kwargs):
    identifier        = CallCounter.total_call_count
    es_name           = kwargs.get('es', None)
    selList           = kwargs.get('selList', None)    # selection list

    sel_name = 'sel' + str(identifier) # name of the selection

    model.component("comp1").geom("geom1").create(sel_name, "ExplicitSelection")
    model.component("comp1").geom("geom1").feature(sel_name).label(es_name+'sel')
    model.component("comp1").geom("geom1").feature(sel_name).selection("selection").init()
    model.component("comp1").geom("geom1").feature(sel_name).selection("selection").set(jstr(selList))
    model.component("comp1").geom("geom1").run(sel_name)

    esDict = { 
        "Ground": ['gnd',2,'_bnd'],
        "DomainTerminal": ['DomainTerminal',3, '_dom']
    }

    module_name = esDict[es_name][0] + str(identifier)

    model.component("comp1").physics("es").create(module_name, es_name, esDict[es_name][1])
     
    model.component("comp1").physics("es").feature(module_name).selection().named("geom1_"+sel_name+esDict[es_name][2])

    