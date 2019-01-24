package com.softwood.controller

import com.softwood.domain.Device
import grails.rest.RestfulController

class Test2Controller extends RestfulController<Device> {
    static responseFormats = ['json', 'xml']

        Test2Controller() {
            super(Device)
        }

    //pick the show view
    def show(Device device) {
        respond ([view:"show"], device )
    }

    //test want this to call views/test2/thing.gson view
    //i.e default is for controller method name to map to <methodname>.gson view
    def thing (Device device) {

        if(device == null) {
            render status:404
        } else {
            //def json = jsonGenerator.toTmfJson(device).encodePrettily()
            //println json

            Device d = device
            respond d, model:[devCount:1, thing: "will"]
        }
    }

}
