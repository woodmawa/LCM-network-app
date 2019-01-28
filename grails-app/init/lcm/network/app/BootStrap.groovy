package lcm.network.app

import com.softwood.domain.Alias
import com.softwood.domain.Device
import com.softwood.domain.Equipment
import com.softwood.domain.FlexAttribute
import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.OrgRoleInstance
import com.softwood.domain.Product
import com.softwood.domain.Resource
import com.softwood.domain.Site
import com.softwood.domain.Location
import com.softwood.domain.Software
import grails.util.Environment
import org.springframework.validation.FieldError

import java.time.LocalDateTime

class BootStrap {

    def init = { servletContext ->

        if (Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST) {
            if (OrgRoleInstance.count() == 0)
                initialiseBootstrapDb()
        }

    }

    def destroy = {
    }

    def initialiseBootstrapDb () {

        println " create database bootstrap records "

        //create VF as service provider
        OrgRoleInstance vf = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Service_Provider, name:"Vodafone" )
        Site vfheadOffice = new Site (name:"Vodafone House, Newbury", status: "occupied ", siteType: Site.SiteRoleType.Headoffice)
        Site vfPeSite = new Site (name:"Canary wharf, Docklands", status: "occupied ", siteType: Site.SiteRoleType.ProviderEdgePopSite)
        vf.addToSites(vfheadOffice)
        vf.addToSites (vfPeSite)

        vf.save ()//(failOnError:true)

        //create cisco as a maintainer
        OrgRoleInstance maintainer = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Maintainer, name:"Cisco" )
        maintainer.save(flush:true)

        OrgRoleInstance ciscoSupplier = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Supplier, name:"Cisco" )
        ciscoSupplier.save(flush:true)

        //create Acme as a customer and a domain
        OrgRoleInstance acme = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Customer, name:"Acme" )
        NetworkDomain custDomain
        acme.addToDomains(custDomain = new NetworkDomain(name:"corporate WAN"))
        acme.save(failOnError:true)
        Site headOffice = new Site (name:"1 Barkley Square", status: "occupied ", org:acme)
        headOffice.save(failOnError:true)
        acme.addToSites(new Site (name:"10 South Close", status:"closed"))
        acme.save ()

        Location commsRoom, peLocation
        headOffice.addToLocations(commsRoom = new Location(name:"comms room", site:headOffice))
        headOffice.save (failOnError:true)
        vfPeSite.addToLocations(peLocation = new Location(name:"exchange room #6" /*cascade save: ,site:vfPeSite)*/))
        vfPeSite.save (failOnError:true)

        MaintenanceAgreement mag = new MaintenanceAgreement()
        mag.level = "Gold"
        mag.category.putAll(p1:"24x7")
        mag.maintainer = maintainer
        mag.contractReference = "my cisco support contract"
        mag.status = "Active"
        //mag.save ()
        vf.addToMags(mag)

        mag = new MaintenanceAgreement()
        mag.level = "Silver"
        mag.category.putAll(p1:"24x5")
        mag.maintainer = maintainer
        mag.contractReference = "my cisco support contract"
        mag.status = "Active"
        mag.save (failOnError:true)

        vf.save (flush:true)
        println "number of mags : " + MaintenanceAgreement.count()

        //build master software instance 'singleton'
        Software ios = new Software (name: "IOS 13.4", version: "13.4", type: Software.SoftwareType.InternetworkOperatingSystem, supplier :ciscoSupplier )
        Software peIos = new Software (name: "IOS XR", version: "3.4", type: Software.SoftwareType.InternetworkOperatingSystem, supplier :ciscoSupplier )
        ios.save (failonError:true)
        peIos.save (failonError:true)

        assert Software.count() ==2

        Product ethCard = new Product (name:"8-port 10 Gigabit Ethernet Fiber Module", partNumber: "WS-X6908-10G-2T (with DFC4)")
        Product chasis6509 = new Product (name:"Cisco Catalyst 6509 Enhanced Chassis", partNumber: "WS-C6509-E")
        Product sw6509 = new Product (name:"Cisco Switch/Router 6509-E bundle", model:"6509-E", partNumber: "6509-B)")
        Product asr = new Product (name:"Cisco ASR 9001 router", model:"ASR-9001", partNumber:"ASR-9000-F")
        Product.saveAll([sw6509, chasis6509, ethCard, asr])
        assert Product.count() == 4

        Device router = new Device ()
        router.testDevice = true
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

        router.domain = custDomain
        //add two roles
        router.addToRoles(Resource.ResourceRoleType.CustomerEdge)
        router.addToRoles(Resource.ResourceRoleType.Router)
        //add some simple attributes
        router.addToAttributes(new FlexAttribute(type: FlexAttribute.AttributeType.Single, name:"Bandwidth", value: "28Mbits"))
        router.addToAttributes(new FlexAttribute(type: FlexAttribute.AttributeType.Single, name:"DSCP enabled", value: "6 QoS"))

        router.save (failOnError:true)

        def dataAttMap = new FlexAttribute(type: FlexAttribute.AttributeType.Map, attributeGroup: "standard data map", name:"data", mapValue: [use: "multi domain", quality: "high quality", support : "long term support"])
        router.addToAttributes(dataAttMap)
        router.save (failOnError:true)


        assert Device.count() == 1
        assert router.roles[0] == Resource.ResourceRoleType.CustomerEdge

        //equipment is BelongsTo [device:Device] - so save them first
        Equipment chasis = new Equipment ()
        chasis.name = "chasis#1"
        chasis.category =  Equipment.EquipmentCategory.Chasis
        chasis.serialNumber = "abc 123 s/n"
        chasis.equipmentContainer = true
        chasis.product = chasis6509
        chasis.validate()
        if (chasis.hasErrors()) {
            println "chasis errors"
             chasis.errors.fieldErrors.each { FieldError e -> println "field: '${e.field}', rejectedValue:'$e.rejectedValue' "}
        } else
            chasis.save()
        Equipment wanCard = new Equipment () //name:"wan card#1", category: Equipment.EquipmentCategory.InterfaceCard, serialNumber:"eth 58 s/n", equipmentContainer:false, productOffering:ethCard).validate()
        wanCard.name = "wancard#1"
        wanCard.category =  Equipment.EquipmentCategory.InterfaceCard
        wanCard.serialNumber = "abc 123 s/n"
        wanCard.equipmentContainer = true
        wanCard.product = ethCard
        if (wanCard.hasErrors()) {
            println "wan card errors"
            wanCard.errors.fieldErrors.each { FieldError e -> println "field: '{$e.field}', rejectedVale:'$e.rejectedValue' "}
        } else
            wanCard.save()

        assert Equipment.count() == 2

        router.addToBuildConfiguration(chasis)
        router.addToBuildConfiguration(wanCard)
        router.save(failOnError:true)
        assert router.buildConfiguration.size() == 2

        router.addToAliasNames(new Alias (name:"My 6509", ipAddress: "10.2.5.1", associatedOrg:acme))
        router.save(failOnError:true)
        assert router.aliasNames.size() == 1

        // add a VF PE router
        Device PeRouter = new Device ()
        PeRouter.testDevice = false
        PeRouter.product = asr
        PeRouter.name = "VF-PE-Docklands"
        PeRouter.installedDate = LocalDateTime.now()
        PeRouter.isVirtual = false
        PeRouter.manHostName = "VF-PE-Docklands-1"
        PeRouter.manIpAddress = "192.28.10.2"
        PeRouter.ownedBy = "Vodafone Owned"
        PeRouter.usage = "PE ASR wan router"
        PeRouter.deviceStatus = "Operational"
        PeRouter.org = vf
        PeRouter.runtimeOS = peIos
        PeRouter.site = vfPeSite
        PeRouter.location = peLocation

        //add two roles
        PeRouter.addToRoles(Resource.ResourceRoleType.ProviderEdge)
        PeRouter.addToRoles(Resource.ResourceRoleType.Router)
        //add some simple attributes
        PeRouter.addToAttributes(new FlexAttribute(type: FlexAttribute.AttributeType.Single, name:"Bandwidth", value: "10Gbit"))
        PeRouter.addToAttributes(new FlexAttribute(type: FlexAttribute.AttributeType.Single, name:"DSCP enabled", value: "6 QoS"))
        PeRouter.addToAttributes(new FlexAttribute(type: FlexAttribute.AttributeType.Single, name:"BGP enabled", value: "false"))

        PeRouter.save (failOnError:true)

    }
}

