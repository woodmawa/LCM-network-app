package com.softwood.controller

import com.softwood.domain.Device
import grails.rest.RestfulController

//@Transactional(readOnly = true)
class DeviceController extends RestfulController<Device> {
    static responseFormats = ['json', 'xml']

    DeviceController() {
        super (Device)
    }


    /*def indexInvestigation (Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Collection<Device> results = Device.list(sort:"name")
        respond results, deviceCount: Device.count()

    }*/

    /*def show (Device device) {
        if(device == null) {
            render status:404
        } else {respond device}
    }*/
}
