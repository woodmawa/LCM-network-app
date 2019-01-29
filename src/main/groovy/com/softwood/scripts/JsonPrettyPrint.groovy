package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.vertx.core.json.JsonObject

def jsonString = """{"jsonapi":{"version":"1.0"},"data":{"type":"orgRoleInstance","id":"1","attributes":{"role":"Service_Provider","name":"Vodafone"},"relationships":{"sites":{"data":[{"type":"site","id":"1"},{"type":"site","id":"2"}]},"domains":{"data":[]},"mags":{"data":[{"type":"maintenanceAgreement","id":"2"}]}}},"links":{"self":"/orgRoleInstance/show/1"},"included":[{"type":"site","id":"1","attributes":{"zone":null,"siteType":"Headoffice","name":"Vodafone House, Newbury","siteCode":null,"status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/1"},"data":{"type":"orgRoleInstance","id":"1"}},"address":{"data":null},"locations":{"data":[]}},"links":{"self":"/api/sites/1"}},{"type":"site","id":"2","attributes":{"zone":null,"siteType":"ProviderEdgePopSite","name":"Canary wharf, Docklands","siteCode":null,"status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/1"},"data":{"type":"orgRoleInstance","id":"1"}},"address":{"links":{"self":"/geographicAddress/show/1"},"data":{"type":"geographicAddress","id":"1"}},"locations":{"data":[{"type":"location","id":"1"}]}},"links":{"self":"/api/sites/2"}}]}"""


/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


//String subs = rjson.substring (2235,2245)

//println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()