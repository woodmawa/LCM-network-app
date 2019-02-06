package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

def jsonString = """{"jsonapi":{"version":"1.0"},"data":{"type":"orgRoleInstance","id":"5","attributes":{"role":"Customer","name":"Acme"},"relationships":{"sites":{"data":[{"type":"site","id":"3"},{"type":"site","id":"4"}]},"domains":{"data":[{"type":"networkDomain","id":"1"}]},"slags":{"data":[{"type":"serviceLevelAgreement","id":"3"}]},"mags":{"data":[]},"providerNetworks":{"data":[]},"services":{"data":[{"type":"customerFacingService","id":"1"}]}}},"links":{"self":"/orgRoleInstance/show/5"},"included":[{"type":"site","id":"3","attributes":{"zone":null,"siteType":"Undefined","name":"1 Barkley Square","siteCode":null,"status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/5"},"data":{"type":"orgRoleInstance","id":"5"}},"address":{"data":null},"locations":{"data":[{"type":"location","id":"2"}]}},"links":{"self":"/site/show/3"}},{"type":"site","id":"4","attributes":{"zone":null,"siteType":"Undefined","name":"10 South Close","siteCode":null,"status":"open"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/5"},"data":{"type":"orgRoleInstance","id":"5"}},"address":{"data":null},"locations":{"data":[{"type":"location","id":"3"}]}},"links":{"self":"/site/show/4"}}]}"""


/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


//String subs = rjson.substring (2235,2245)

//println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()