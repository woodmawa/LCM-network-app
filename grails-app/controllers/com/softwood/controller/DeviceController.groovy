package com.softwood.controller

import com.softwood.domain.Device
import grails.rest.RestfulController

//@Transactional(readOnly = true)
class DeviceController extends RestfulController<Device> {
    static responseFormats = ['json', 'xml']

    //static scaffold = Device

    DeviceController(Class<Device> device) {
        this(device, false)
    }

    DeviceController(Class<Device> device, boolean readOnly) {
        super(device, readOnly)
    }

    def index (Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Collection<Device> results = Device.list(sort:"name")
        respond results, deviceCount: Device.count()

    }

    def show (Device device) {
        if(device == null) {
            render status:404
        } else {respond device}
    }
}
