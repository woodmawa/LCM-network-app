package com.softwood.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
//import io.vertx.core.json.JsonObject

def jsonString = """[{"product":{"jsonapi":{"version":"1.0"},"data":{"type":"product","id":"1","attributes":{"width":0.0,"dimUom":"Millimeters","offerName":null,"depth":0.0,"uSize":null,"sku":null,"weight":0.0,"partNumber":"WS-X6908-10G-2T (with DFC4)","height":0.0,"weightUom":"Kilogrammes","name":"8-port 10 Gigabit Ethernet Fiber Module","description":null,"model":null}},"links":{"self":"/api/products/1"}}},{"product":{"jsonapi":{"version":"1.0"},"data":{"type":"product","id":"2","attributes":{"width":0.0,"dimUom":"Millimeters","offerName":null,"depth":0.0,"uSize":null,"sku":null,"weight":0.0,"partNumber":"WS-C6509-E","height":0.0,"weightUom":"Kilogrammes","name":"Cisco Catalyst 6509 Enhanced Chassis","description":null,"model":null}},"links":{"self":"/api/products/2"}}},{"product":{"jsonapi":{"version":"1.0"},"data":{"type":"product","id":"3","attributes":{"width":0.0,"dimUom":"Millimeters","offerName":null,"depth":0.0,"uSize":null,"sku":null,"weight":0.0,"partNumber":"6509-B)","height":0.0,"weightUom":"Kilogrammes","name":"Cisco Switch/Router 6509-E bundle","description":null,"model":"6509-E"}},"links":{"self":"/api/products/3"}}},{"product":{"jsonapi":{"version":"1.0"},"data":{"type":"product","id":"4","attributes":{"width":0.0,"dimUom":"Millimeters","offerName":null,"depth":0.0,"uSize":null,"sku":null,"weight":0.0,"partNumber":"ASR-9000-F","height":0.0,"weightUom":"Kilogrammes","name":"Cisco ASR 9001 router","description":null,"model":"ASR-9001"}},"links":{"self":"/api/products/4"}}}]"""


/*
        JsonObject json = new JsonObject (jsonString)

println json.encodePrettily() */


String rjson = jsonString.replaceAll (~/[,]+/, ",")


//String subs = rjson.substring (2235,2245)

//println "substring [$subs]"
def parser = new JsonSlurper()
def json = parser.parseText (rjson)

println new JsonBuilder(json).toPrettyString()