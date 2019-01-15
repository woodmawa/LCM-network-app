package com.softwood.domain

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MaintenanceAgreementSpec extends Specification implements DomainUnitTest<MaintenanceAgreement> {

    def setup() {
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

        mag = new MaintenanceAgreement()
        mag.level = "Silver"
        mag.category.putAll(p1:"24x5")
        mag.maintainer = maintainer
        mag.contractReference = "my cisco support contract"
        mag.status = "Active"
        mag.save (failOnError:true)
        assert MaintenanceAgreement.count() == 2
    }

    def cleanup() {
    }

    void "create a new maintainceAgreement "() {
        given :""


        MaintenanceAgreement mag = new MaintenanceAgreement()
        mag.level = "bronze"
        mag.category.putAll(p1:"24x5", p2:"9 till 5")
        mag.maintainer = OrgRoleInstance.get(2)
        mag.contractReference = "my cisco support contract"
        mag.status = "Active"

        when : "we save new mag total count should go up by 1 "
        mag.save (failOnError:true)

        then : ""
        MaintenanceAgreement.count() == 3
        mag.maintainer.name == "Cisco"


    }

    void "where query " () {
        given: " "

        //create HP as a maintainer
        OrgRoleInstance maintainer = new OrgRoleInstance(role: OrgRoleInstance.OrgRoleType.Maintainer, name:"HP" )
        maintainer.save(flush:true)

        MaintenanceAgreement mag = new MaintenanceAgreement()
        mag.level = "Gold"
        mag.category.putAll(p1:"24x7", p2:"9 till 5")
        mag.maintainer = maintainer
        mag.contractReference = "my HP support contract"
        mag.status = "Active"
        mag.save (failOneError:true)

        when: " query "

        def query = MaintenanceAgreement.where {level == "Gold" && maintainer { name == "HP"}}
        def match = query.list()

        then :" should have 1"
        match.size() == 1
    }

}
