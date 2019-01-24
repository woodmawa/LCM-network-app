package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import lcm.network.app.Application
import spock.lang.Specification

@Integration (applicationClass = Application.class)  //appClass - overcomes problem with gorm not loading when running test
@Rollback
class DeviceIntegSpecSpec extends Specification {

    def setup() {
        //rely on bootstrap data
    }

    def cleanup() {
    }

    void "get full device query with eager loading "() {

        given: "use of static query method using with criteria "

        Device d = Device.getFullDevice(1)

        expect:
        d.domain.is (NetworkDomain.get(1))  //eager loaded belongsTo fk assoc
        d.attributes.size() == 3            //fetchMode.select to get attributes loaded
        d.aliasNames[0].name == "My 6509"
        d.isTestDevice()
        d.buildConfiguration.size() == 2 //has two parts a chasis (equip container ) and a card
        d.buildConfiguration[0].isEquipmentContainer()
        d.buildConfiguration[0].category == Equipment.EquipmentCategory.Chasis
        d.buildConfiguration[1].category == Equipment.EquipmentCategory.InterfaceCard
    }
}
