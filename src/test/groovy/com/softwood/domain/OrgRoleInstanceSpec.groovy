package com.softwood.domain

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class OrgRoleInstanceSpec extends Specification implements DomainUnitTest<OrgRoleInstance> {

    def setup() {
    }

    def cleanup() {
    }

    void "create new org role instance"() {
        given:"new instance"
            OrgRoleInstance org = new OrgRoleInstance()
            org.name = "test customer"
            org.role = OrgRoleInstance.OrgRoleType.Customer

        when : "we save it "
            org.save(failOnError:true)

        then :
            org.id == 1
            org.name == "test customer"
            org.role == OrgRoleInstance.OrgRoleType.Customer
    }
}
