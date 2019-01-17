package com.softwood.service

import com.softwood.domain.Device
import grails.gorm.transactions.Transactional

@Transactional
class DeviceService {

    def findAllDevice() {
        def query = Device.where {name =~ "%ACME%"}

        Collection<Device> result = query.list (sort:"name")
        result
    }
}
