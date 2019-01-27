package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.vertx.core.json.JsonObject

def jsonString = """{"data":{"type":"orgRoleInstance","id":"4","attributes":{"role":"Customer","name":"Acme"},"relationships":{"sites":{"data":[{"type":"site","id":"1"},{"type":"site","id":"2"}]},"domains":{"data":[{"type":"networkDomain","id":"1"}]},"mags":{"data":[]}}},"links":{"self":"/orgRoleInstance/show/4"},"included":[{"type":"site","id":"1","attributes":{"name":"1 Barkley Square","status":"occupied"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}},"locations":{"data":[{"type":"location","id":"1"}]}},"links":{"self":"/api/site/1"}},{"type":"site","id":"2","attributes":{"name":"10 South Close","status":"closed"},"relationships":{"org":{"links":{"self":"/orgRoleInstance/show/4"},"data":{"type":"orgRoleInstance","id":"4"}},"locations":{"data":[]}},"links":{"self":"/api/site/2"}}]}"""
/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


//String subs = rjson.substring (2235,2245)

//println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()