package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

def jsonString = """[{"site":{"jsonapi":{"version":"1.0"},"data":{"type":"site","id":"1","attributes":{"zone":null,"siteType":"Headoffice","name":"Vodafone House","siteCode":null,"status":"occupied"}},"links":{"self":"/api/sites/1"}}},{"site":{"jsonapi":{"version":"1.0"},"data":{"type":"site","id":"2","attributes":{"zone":null,"siteType":"ProviderEdgePopSite","name":"Canary wharf, Docklands","siteCode":null,"status":"occupied"}},"links":{"self":"/api/sites/2"}}},{"site":{"jsonapi":{"version":"1.0"},"data":{"type":"site","id":"3","attributes":{"zone":null,"siteType":"Undefined","name":"1 Barkley Square","siteCode":null,"status":"occupied"}},"links":{"self":"/api/sites/3"}}},{"site":{"jsonapi":{"version":"1.0"},"data":{"type":"site","id":"4","attributes":{"zone":null,"siteType":"Undefined","name":"10 South Close","siteCode":null,"status":"open"}},"links":{"self":"/api/sites/4"}}}]"""


/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


//String subs = rjson.substring (2235,2245)

//println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()