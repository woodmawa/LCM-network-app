package lcm.network.app


import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.OrgRoleInstance
import com.softwood.domain.Site
import com.softwood.domain.Location

class BootStrap {

    def init = { servletContext ->

        //create VF as service provider
        OrgRoleInstance vf = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Service_Provider, name:"Vodafone" )
        vf.save ()//(failOnError:true)

        //create cisco as a maintainer
        OrgRoleInstance maintainer = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Maintainer, name:"Cisco" )
        maintainer.save(flush:true)

        //create Acme as a customer and a domain
        OrgRoleInstance acme = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Customer, name:"Acme" )
        acme.addToDomains(new NetworkDomain(name:"corporate WAN"))
        acme.save(failOnError:true)
        Site headOffice = new Site (name:"1 Barkley Square", status: "occupied ", org:acme)
        headOffice.save(failOnError:true)
        acme.addToSites(new Site (name:"10 South Close", status:"closed"))
        acme.save ()

        headOffice.addToLocations(new Location(name:"comms room"))
        headOffice.save (failOnError:true)

        MaintenanceAgreement mag = new MaintenanceAgreement()
        mag.level = "Gold"
        mag.category.putAll(p1:"24x7")
        mag.maintainer = maintainer
        mag.contractReference = "my cisco support contract"
        mag.status = "Active"
        mag.save ()

        mag = new MaintenanceAgreement()
        mag.level = "Silver"
        mag.category.putAll(p1:"24x5")
        mag.maintainer = maintainer
        mag.contractReference = "my cisco support contract"
        mag.status = "Active"
        mag.save (failOnError:true)
        assert MaintenanceAgreement.count() == 2
    }

    def destroy = {
    }
}
