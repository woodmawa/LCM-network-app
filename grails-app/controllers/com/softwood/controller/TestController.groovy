package com.softwood.controller

import com.softwood.domain.Device

class TestController {

    static responseFormats = ['json', 'xml']

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Collection<Device> results = Device.list(sort:"name")
        respond results, deviceCount: Device.count()

    }
}
