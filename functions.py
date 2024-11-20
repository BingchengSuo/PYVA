import jpype
import time

def jstr(pystr):
        """
        argument: 
            pystr -> python string
        return:
            jpype converted java string
        """
        return jpype.JArray(jpype.JString)(pystr)

def jarr(pyarr):
    return jpype.JArray(jpype.JInt)(pyarr)

def jint(pynum):
     return jpype.JInt(pynum)

def write_log(string):
    file = open("log.txt", "w")
    file.write(string+'\n')
    file.close()

def read_log():
    file = open("log.txt", "r")
    lines = file.readlines()
    if lines: 
        last_element = lines[-1].strip() 
    return last_element

class CallCounter:
    total_call_count = 0

    def __init__(self, func):
        self.func = func
        self.call_count = 0

    def __call__(self, *args, **kwargs):
        self.call_count += 1
        CallCounter.total_call_count += 1
        return self.func(*args, **kwargs)

@staticmethod
def get_total_calls():
    return CallCounter.total_call_count

def getLayerIndexDict(list):
    """
    argument:
        list    -> layer name string list; e.g. ['InGaAs','InAs','InGaAs']
    return:
        indices -> a dictionary showing all the indices of items in list
    """
    indices = {}
    for index, value in enumerate(list):
        if value not in indices:
            indices[value] = []
        indices[value].append(index+1)
    return indices