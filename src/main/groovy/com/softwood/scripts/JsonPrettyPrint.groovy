package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.vertx.core.json.JsonObject

def jsonString = """{"jsonapi":{"version":"1.0"},"data":{"type":"device","id":"1","attributes":{"adminStatus":"Locked","licenceType":null,"storage":null,"opStatus":"Active","dateCreated":"2019-01-18T09:41:20.95","manHostName":"VF-ACME-HO-WAN1","isFreeStanding":false,"ownedBy":"Customer Owned","lastUpdated":"2019-01-18T09:41:20.986","manIpAddress":"192.57.3.28","roles":["CustomerEdge","Router"],"isManaged":true,"commissionedDate":null,"isVirtual":false,"usage":"HO wan router","name":"ACME-HO-WAN1","memory":null,"numberOfCpu":null,"licence":"none","installedDate":"2019-01-18T09:41:20.934","deviceStatus":"Operational"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}},"location":{"links":{"self":"/location/show/1"},"data":{"type":"location","id":"1"}},"aliasNames":{"data":[{"type":"alias","id":"1"}]},"runtimeOS":{"links":{"self":"/software/show/1"},"data":{"type":"software","id":"1"}},"deviceRoles":{"data":[]},"buildConfiguration":{"data":[{"type":"equipment","id":"1"},{"type":"equipment","id":"2"}]},"productType":{"links":{"self":"/productOffering/show/1"},"data":{"type":"productOffering","id":"1"}},"attributes":{"data":[{"type":"flexAttribute","id":"1"},{"type":"flexAttribute","id":"2"}]},"interfaces":{"data":[]},"index":{"links":{"self":"/index/show/1"},"data":{"type":"index","id":"1"}},"domain":{"links":{"self":"/networkDomain/show/1"},"data":{"type":"networkDomain","id":"1"}}}},"links":{"self":"/api/device/1"},"included":[{"type":"index","id":"1","attributes":{"name":"1 Barkley Square","status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}},"locations":{"data":[{"type":"location","id":"1"}]}},"links":{"self":"/index/show/1"}},{"type":"location","id":"1","attributes":{"room":null,"name":"comms room","floor":null},"relationships":{"index":{"links":{"self":"/index/show/1"},"data":{"type":"index","id":"1"}}},"links":{"self":"/location/show/1"}},{"type":"networkDomain","id":"1","attributes":{"name":"corporate WAN"},"relationships":{"customer":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}}},"links":{"self":"/networkDomain/show/1"}},,{"type":"alias","id":"1","attributes":{"netMask":null,"name":"My 6509","ipAddress":"10.2.5.1"},"relationships":{"device":{"links":{"self":"/api/device/1"},"data":{"type":"device","id":"1"}},"associatedOrg":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}}},"links":{"self":"/alias/show/1"}},{"type":"software","id":"1","attributes":{"dateCreated":"2019-01-18T09:41:20.926","lastUpdated":"2019-01-18T09:41:20.926","name":"IOS 13.4","type":"InternetworkOperatingSystem","imageLibraryUri":null},"relationships":{"supplier":{"links":{"self":"/orgRoleInstance/show/3"},"data":{"type":"orgRoleInstance","id":"3"}}},"links":{"self":"/software/show/1"}},,,{"type":"flexAttribute","id":"1","attributes":{"dateCreated":"2019-01-18T09:41:20.95","mapValue":{},"lastUpdated":"2019-01-18T09:41:20.95","name":"Bandwidth","value":"28Mbits","type":"Single","listValue":[],"attributeGroup":"<default>"},"relationships":{"device":{"links":{"self":"/api/device/1"},"data":{"type":"device","id":"1"}}},"links":{"self":"/flexAttribute/show/1"}},{"type":"flexAttribute","id":"2","attributes":{"dateCreated":"2019-01-18T09:41:20.95","mapValue":{},"lastUpdated":"2019-01-18T09:41:20.95","name":"DSCP enabled","value":"6 QoS","type":"Single","listValue":[],"attributeGroup":"<default>"},"relationships":{"device":{"links":{"self":"/api/device/1"},"data":{"type":"device","id":"1"}}},"links":{"self":"/flexAttribute/show/2"}}]}"""

/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


String subs = rjson.substring (2200,2240)

println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()