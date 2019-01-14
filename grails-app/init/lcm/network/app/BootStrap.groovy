package lcm.network.app

import com.softwood.domain.OrgRoleInstance

class BootStrap {

    def init = { servletContext ->

        OrgRoleInstance vf = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Service_Provider, name:"Vodafone" )
        vf.save (failOnError:true)
    }

    def destroy = {
    }
}
