package com.softwood.service

import com.softwood.domain.Device
import com.softwood.domain.Equipment
import com.softwood.domain.FlexAttribute
import com.softwood.domain.Interface
import com.softwood.domain.Location
import com.softwood.domain.NetworkDomain
import com.softwood.domain.OrgRoleInstance
import com.softwood.domain.Product
import com.softwood.domain.Resource
import com.softwood.domain.Site
import com.softwood.domain.Software
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * needs DataTest to get the mockingDomain
 *
 */
class DeviceServiceSpec extends Specification implements ServiceUnitTest<DeviceService>, DataTest{

    def setup() {
        mockDomain Device
        mockDomain Interface
        mockDomain NetworkDomain
        mockDomain Equipment
        mockDomain FlexAttribute
        mockDomain Site
        mockDomain Location
        mockDomain Software
        mockDomain Product
        mockDomain OrgRoleInstance


        Product ethCard = new Product (name:"8-port 10 Gigabit Ethernet Fiber Module", partNumber: "WS-X6908-10G-2T (with DFC4)")
        Product chasis6509 = new Product (name:"Cisco Catalyst 6509 Enhanced Chassis", partNumber: "WS-C6509-E")
        Product sw6509 = new Product (name:"Cisco Switch/Router 6509-E bundle", model:"6509-E", partNumber: "6509-B)")
        Product.saveAll([sw6509, chasis6509, ethCard])

        OrgRoleInstance ciscoSupplier = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Supplier, name:"Cisco" )
        ciscoSupplier.save(flush:true)

        //create Acme as a customer and a domain
        OrgRoleInstance acme = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Customer, name:"Acme" )
        acme.addToDomains(new NetworkDomain(name:"corporate WAN"))
        acme.save(failOnError:true)
        Site headOffice = new Site (name:"1 Barkley Square", status: "occupied ", org:acme)
        headOffice.save(failOnError:true)
        acme.addToSites(new Site (name:"10 South Close", status:"closed"))
        acme.save ()

        Location commsRoom
        headOffice.addToLocations(commsRoom = new Location(name:"comms room", site:headOffice))
        headOffice.save (failOnError:true)

        //build master software instance 'singleton'
        Software ios = new Software (name: "IOS 13.4", version: "13.4", type: Software.SoftwareType.InternetworkOperatingSystem, supplier :ciscoSupplier )
        ios.save (failonError:true)


        Device router = new Device ()
        router.product = sw6509
        router.name = "ACME-HO-WAN1"
        router.installedDate = LocalDateTime.now()
        router.isVirtual = false
        router.manHostName = "VF-ACME-HO-WAN1"
        router.manIpAddress = "192.57.3.28"
        router.ownedBy = "Customer Owned"
        router.usage = "HO wan router"
        router.deviceStatus = "Operational"
        router.org = acme
        router.runtimeOS = ios
        router.site = headOffice
        router.location = commsRoom
        router.addToRoles(Resource.ResourceRoleType.CustomerEdge)
        router.addToAttributes(new FlexAttribute(type: FlexAttribute.AttributeType.Single, name:"Bandwidth", value: "28Mbits"))
        router.save (failOnError:true)

        assert Device.count() == 1

    }

    def cleanup() {
    }


    void "test something"() {

        given: "a new service"
        DeviceService deviceService = new DeviceService()

        when:"invoke service "
        List<Device> results = deviceService.findAllDevice()

        then: "validate result"
        results.size() == 1
        results[0].location.name == "comms room"
    }
}
