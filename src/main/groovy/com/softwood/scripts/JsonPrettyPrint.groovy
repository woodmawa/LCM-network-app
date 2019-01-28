package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.vertx.core.json.JsonObject

def jsonString = """{"jsonapi":{"version":"1.0"},"data":{"type":"device","id":"2","attributes":{"adminStatus":"Locked","licenceType":null,"storage":null,"opStatus":"Active","dateCreated":"2019-01-28T19:14:20.701","manHostName":"VF-PE-Docklands-1","ownedBy":"Vodafone Owned","lastUpdated":"2019-01-28T19:14:20.701","manIpAddress":"192.28.10.2","roles":["ProviderEdge","Router"],"freeStanding":false,"isManaged":true,"testDevice":false,"commissionedDate":null,"isVirtual":false,"usage":"PE ASR wan router","name":"VF-PE-Docklands","memory":null,"numberOfCpu":null,"licence":"none","installedDate":"2019-01-28T19:14:20.685","deviceStatus":"Operational"},"relationships":{"product":{"links":{"self":"/product/show/4"},"data":{"type":"product","id":"4"}},"org":{"links":{"self":"/orgRoleInstance/show/1"},"data":{"type":"orgRoleInstance","id":"1"}},"location":{"links":{"self":"/location/show/1"},"data":{"type":"location","id":"1"}},"entityReferencedBy":{"data":[]},"aliasNames":{"data":[]},"runtimeOS":{"links":{"self":"/software/show/2"},"data":{"type":"software","id":"2"}},"deviceRoles":{"data":[]},"entityReferences":{"data":[]},"buildConfiguration":{"data":[]},"attributes":{"data":[{"type":"flexAttribute","id":"3"},{"type":"flexAttribute","id":"4"},{"type":"flexAttribute","id":"5"}]},"interfaces":{"data":[]},"site":{"links":{"self":"/api/sites/2"},"data":{"type":"site","id":"2"}},"domain":{"data":null}}},"links":{"self":"/api/devices/2"},"included":[{"type":"site","id":"2","attributes":{"siteType":"ProviderEdgePopSite","name":"Canary wharf, Docklands","status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/1"},"data":{"type":"orgRoleInstance","id":"1"}},"locations":{"data":[{"type":"location","id":"1"}]}},"links":{"self":"/api/sites/2"}},{"type":"location","id":"1","attributes":{"room":null,"name":"exchange room #6","floor":null},"relationships":{"site":{"links":{"self":"/api/sites/2"},"data":{"type":"site","id":"2"}}},"links":{"self":"/location/show/1"}},{"type":"software","id":"2","attributes":{"dateCreated":"2019-01-28T19:14:20.616","lastUpdated":"2019-01-28T19:14:20.616","name":"IOS XR","type":"InternetworkOperatingSystem","imageLibraryUri":null},"relationships":{"supplier":{"links":{"self":"/orgRoleInstance/show/3"},"data":{"type":"orgRoleInstance","id":"3"}}},"links":{"self":"/software/show/2"}},,{"type":"flexAttribute","id":"3","attributes":{"dateCreated":"2019-01-28T19:14:20.701","mapValue":{},"lastUpdated":"2019-01-28T19:14:20.701","name":"Bandwidth","value":"10Gbit","type":"Single","listValue":[],"attributeGroup":"<default>"},"relationships":{"device":{"links":{"self":"/api/devices/2"},"data":{"type":"device","id":"2"}}},"links":{"self":"/flexAttribute/show/3"}},{"type":"flexAttribute","id":"4","attributes":{"dateCreated":"2019-01-28T19:14:20.701","mapValue":{},"lastUpdated":"2019-01-28T19:14:20.701","name":"DSCP enabled","value":"6 QoS","type":"Single","listValue":[],"attributeGroup":"<default>"},"relationships":{"device":{"links":{"self":"/api/devices/2"},"data":{"type":"device","id":"2"}}},"links":{"self":"/flexAttribute/show/4"}},{"type":"flexAttribute","id":"5","attributes":{"dateCreated":"2019-01-28T19:14:20.701","mapValue":{},"lastUpdated":"2019-01-28T19:14:20.701","name":"BGP enabled","value":"false","type":"Single","listValue":[],"attributeGroup":"<default>"},"relationships":{"device":{"links":{"self":"/api/devices/2"},"data":{"type":"device","id":"2"}}},"links":{"self":"/flexAttribute/show/5"}}]}"""


/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


//String subs = rjson.substring (2235,2245)

//println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()