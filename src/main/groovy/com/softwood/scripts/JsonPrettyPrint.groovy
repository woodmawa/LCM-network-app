package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.vertx.core.json.JsonObject

def jsonString = """[{"orgRoleInstance":{"data":{"type":"orgRoleInstance","id":"1","attributes":{"role":"Service_Provider","name":"Vodafone"},"relationships":{"sites":{"data":[{"type":"site","id":"1"}]},"domains":{"data":[]},"mags":{"data":[{"type":"maintenanceAgreement","id":"2"}]}}},"links":{"self":"/orgRoleInstance/show/1"},"included":[{"type":"site","id":"1","attributes":{"name":"Vodafone House, Newbury","status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/1"},"data":{"type":"orgRoleInstance","id":"1"}},"locations":{"data":[]}},"links":{"self":"/api/sites/1"}}]}},{"orgRoleInstance":{"data":{"type":"orgRoleInstance","id":"2","attributes":{"role":"Maintainer","name":"Cisco"},"relationships":{"sites":{"data":[]},"domains":{"data":[]},"mags":{"data":[{"type":"maintenanceAgreement","id":"1"}]}}},"links":{"self":"/orgRoleInstance/show/2"},"included":[]}},{"orgRoleInstance":{"data":{"type":"orgRoleInstance","id":"3","attributes":{"role":"Supplier","name":"Cisco"},"relationships":{"sites":{"data":[]},"domains":{"data":[]},"mags":{"data":[]}}},"links":{"self":"/orgRoleInstance/show/3"},"included":[]}},{"orgRoleInstance":{"data":{"type":"orgRoleInstance","id":"4","attributes":{"role":"Customer","name":"Acme"},"relationships":{"sites":{"data":[{"type":"site","id":"2"},{"type":"site","id":"3"}]},"domains":{"data":[{"type":"networkDomain","id":"1"}]},"mags":{"data":[]}}},"links":{"self":"/orgRoleInstance/show/4"},"included":[{"type":"site","id":"2","attributes":{"name":"1 Barkley Square","status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}},"locations":{"data":[{"type":"location","id":"1"}]}},"links":{"self":"/api/sites/2"}},{"type":"site","id":"3","attributes":{"name":"10 South Close","status":"closed"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}},"locations":{"data":[]}},"links":{"self":"/api/sites/3"}}]}}]"""

/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


//String subs = rjson.substring (2235,2245)

//println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()