package lcm.network.app

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.OrgRoleInstance

class BootStrap {

    def init = { servletContext ->

        //create VF as service provider
        OrgRoleInstance vf = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Service_Provider, name:"Vodafone" )
        vf.save ()//(failOnError:true)

        //create cisco as a maintainer
        OrgRoleInstance maintainer = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Maintainer, name:"Cisco" )
        maintainer.save(flush:true)

        MaintenanceAgreement mag = new MaintenanceAgreement()
        mag.level = "Gold"
        mag.category.putAll(p1:"24x7")
        mag.maintainer = maintainer
        mag.contractReference = "my cisco support contract"
        mag.status = "Active"
        mag.save (failOnError:true)
        assert mag.id
    }

    def destroy = {
    }
}
