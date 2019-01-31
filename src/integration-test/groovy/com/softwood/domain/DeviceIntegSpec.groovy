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

    void "build relationship between PE from DB and manual built CE "() {

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

        EntityRelationship /*<Device, Device>*/ rel = new EntityRelationship<Device, Device>()
        rel.name = "i need a PE"
        rel.toRole = "i need this PE "
        rel.toRole = "i am supporting "

        //work around for defect - have to evaluate the record from DB - before you use it
        //see https://stackoverflow.com/questions/54466290/grails-v3-3-9-gorm-defect-integration-and-dev-one-to-many-with-mappedby-upd
        assert pe.linkedFrom.size() == 0

        ce.addToLinkedTo(rel)       //save of ce cascades here
        pe.addToLinkedFrom(rel)
        rel.save(failOnError:true)           //cascade? to linkedTo?

        assert ce.linkedTo.size() == 1
        assert ce.linkedFrom.size() == 0

        assert pe.linkedTo.size() == 0
        assert pe.linkedFrom.size() == 1
        assert EntityRelationship.count() == 1

        then:

        rel.id > 0
        rel.linkedFrom == ce
        rel.linkedTo == pe
        ce.owner.name == "Acme"
        ce.name == "ACME-HO-WAN1"
        EntityRelationship.count() == 1
        ce.linkedTo.size () == 1
        ce.linkedFrom.size () == 0
        ce.linkedTo[0].name == "i need a PE"

        pe.linkedTo.size () == 0
        pe.linkedFrom.size () == 1

        Map result = EntityRelationship.getEntitiesLinkedFromThis(ce)
        result.size() == 1
        result["i need a PE"] == pe

    }

    //passes
    void "build relationship between manual PE device  and manual CE Device build  "() {

        given:

        Device pe = new Device()
        pe.testDevice = true
        pe.name = "Test PE"
        pe.installedDate = LocalDateTime.now()
        pe.isVirtual = false
        pe.manHostName = "TEST PE"
        pe.manIpAddress = "192.60.3.90"
        pe.ownedBy = "Service Provider Owned"
        pe.usage << ManagedEntity.UsageType.Wan
        pe.usage << ManagedEntity.UsageType.Data
        pe.deviceStatus = "Operational"
        pe.owner = OrgRoleInstance.findByNameIlike("Vodafone") //set to vf
        pe.runtimeOS = Software.get(2)
        pe.site = Site.get(2)  //10 south close belonging to acme
        pe.location = Location.get(2)  //home office

        //add two roles
        pe.addToRoles(Resource.ResourceRoleType.ProviderEdge)
        pe.addToRoles(Resource.ResourceRoleType.Router)
        pe.save(failOnError:true)

        pe.save (failOnError:true)

        Device ce = new Device()
        ce.testDevice = true
        ce.name = "ACME-HO-WAN1"
        ce.installedDate = LocalDateTime.now()
        ce.isVirtual = false
        ce.manHostName = "VF-ACME-HO-WAN1"
        ce.manIpAddress = "192.57.3.28"
        ce.ownedBy = "Customer Owned"
        ce.usage = ManagedEntity.UsageType.Wan
        ce.usage = ManagedEntity.UsageType.Lan
        ce.usage = ManagedEntity.UsageType.Security
        ce.deviceStatus = "Operational"
        ce.owner = OrgRoleInstance.findByNameIlike("Acme") //set to acme
        ce.runtimeOS = Software.get(1)
        ce.site = Site.get(4)  //10 south close belonging to acme
        ce.location = Location.get(1)  //home office

        //add two roles
        ce.addToRoles(Resource.ResourceRoleType.CustomerEdge)
        ce.addToRoles(Resource.ResourceRoleType.Router)
        ce.save(failOnError:true)

        ce.save (failOnError:true)


        when : "build a ce and relate the CE and PE  "

        EntityRelationship rel = new EntityRelationship<Device, Device>()
        rel.name = "i need a PE"
        rel.toRole = "i need this PE"
        rel.toRole = "i am supporting"

        ce.addToLinkedTo(rel)       //save of ce cascades here
        pe.addToLinkedFrom(rel)
        rel.save(failOnError:true)           //cascade? to linkedTo?
        rel




        assert ce.linkedTo.size() == 1
        assert ce.linkedFrom.size() == 0

        assert pe.linkedTo.size() == 0
        assert pe.linkedFrom.size() == 1
        assert EntityRelationship.count() == 1

        then:

        rel.id > 0
        rel.linkedFrom == ce
        rel.linkedTo == pe
        EntityRelationship.count() == 1
        ce.linkedTo.size () == 1
        ce.linkedFrom.size () == 0
        pe.linkedTo.size () == 0
        pe.linkedFrom.size () == 1

    }

    //fails
    void "build relationship between PE and CE from db  "() {

        given:

        Device ce = Device.findByManHostNameLike("VF-ACME-HO-WAN1")
        Device pe = Device.getFullDeviceById(2L)


        when:

        assert ce.isAttached()
        assert pe.isAttached()

        EntityRelationship rel = new EntityRelationship<Device, Device>()
        rel.name = "i need a PE"
        rel.toRole = "i need this PE"
        rel.toRole = "i am supporting"

        ce.addToLinkedTo(rel)       //save of ce cascades here
        pe.addToLinkedFrom(rel)
        rel.save(failOnError:true)           //cascade? to linkedTo?
        rel


        assert ce.linkedTo.size() == 1
        assert ce.linkedFrom.size() == 0

        assert pe.linkedTo.size() == 0
        assert pe.linkedFrom.size() == 1
        assert EntityRelationship.count() == 1


        then:
        rel.id > 0
        rel.linkedFrom == ce
        rel.linkedTo == pe
        EntityRelationship.count() == 1
        ce.linkedTo.size () == 1
        ce.linkedFrom.size () == 0
        pe.linkedTo.size () == 0
        pe.linkedFrom.size () == 1

    }
}
