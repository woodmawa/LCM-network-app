package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import lcm.network.app.Application
import spock.lang.Specification

import java.time.LocalDateTime

@Integration (applicationClass = Application.class)  //appClass - overcomes problem with gorm not loading when running test
@Rollback
class DeviceIntegSpec extends Specification {

    def setup() {
        //rely on bootstrap data
    }

    def cleanup() {
    }

    void "get full device query with eager loading "() {

        given: "use of static query method using with criteria "

        Device d = Device.getFullDeviceById(1)

        expect:
        d.domain.is (NetworkDomain.get(1))  //eager loaded belongsTo fk assoc
        d.attributes.size() == 3            //fetchMode.select to get attributes loaded
        d.aliasNames[0].name == "My 6509"
        d.isTestDevice()
        d.buildConfiguration.size() == 4 //has 4 parts, a chasis (equip container ) and two cards and psu
        d.buildConfiguration[0].isEquipmentContainer()
        d.buildConfiguration[0].category == Equipment.EquipmentCategory.Chasis
        d.buildConfiguration[1].category == Equipment.EquipmentCategory.InterfaceCard
    }

    void "build relationship between two CI "() {

        given:

        Device pe = Device.getFullDeviceById(2L)

        assert pe

        when : "build a ce and relate the CE and PE  "

        Device ce = new Device()
        ce.testDevice = true
        ce.name = "ACME-HO-WAN1"
        ce.installedDate = LocalDateTime.now()
        ce.isVirtual = false
        ce.manHostName = "VF-ACME-HO-WAN1"
        ce.manIpAddress = "192.57.3.28"
        ce.ownedBy = "Customer Owned"
        ce.usage = "HO wan router"
        ce.deviceStatus = "Operational"
        ce.owner = OrgRoleInstance.findByNameIlike("Acme") //set to acme
        ce.runtimeOS = Software.get(1)
        ce.site = Site.get(4)  //10 south close belonging to acme
        ce.location = Location.get(1)  //home office

        //add two roles
        ce.addToRoles(Resource.ResourceRoleType.CustomerEdge)
        ce.addToRoles(Resource.ResourceRoleType.Router)
        ce.save(failOnError:true)

        EntityRelationship <Device, Device> rel = new EntityRelationship()
        rel.name = "i need a PE"
        rel.owningRole = "i need this PE "
        rel.referencedRole = "i am supporting "

        ce.addToEntityReferences(rel)
        pe.addToEntityReferencedBy(rel)
       rel.save(failOnError:true)

        assert ce.entityReferences.size() == 1
        assert ce.entityReferencedBy.size() == 0

        assert pe.entityReferences.size() == 0
        assert pe.entityReferencedBy.size() == 1

        then:

        ce.owner.name == "Acme"
        ce.name == "ACME-HO-WAN1"
        EntityRelationship.count() == 1
        ce.entityReferences.size () == 1
        ce.entityReferencedBy.size () == 0
        ce.entityReferences[0].name == "i need a PE"

        pe.entityReferences.size () == 0
        pe.entityReferencedBy.size () == 1

        Map result = EntityRelationship.getReferencedEntitiesFrom(ce)
        result.size() == 1
        result["i need a PE"] == pe

    }

}
