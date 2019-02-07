package com.softwood.scripts

def b = Map.isAssignableFrom(HashMap)
println Map.isAssignableFrom(HashMap)
assert b

Class var = HashMap
def mclass = var.getMetaClass()
println "$var" + " meta class " + "${mclass}"
switch (var) {
    case Map:
        println "assingable true "
        break

    default:
        println "false"
        break

}