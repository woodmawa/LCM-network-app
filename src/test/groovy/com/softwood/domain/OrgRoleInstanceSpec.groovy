package com.softwood.domain

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class OrgRoleInstanceSpec extends Specification implements DomainUnitTest<OrgRoleInstance> {

    def setup() {
        OrgRoleInstance

        List<OrgRoleInstance> orgs = []
        ["A","B","C"].each {
            OrgRoleInstance org = new OrgRoleInstance(name:it, role:OrgRoleInstance.OrgRoleType.Customer)
            org.addToSites(new Site( name: "$it's Head Office", status:"open", org:org))
            orgs << org

        }
        OrgRoleInstance.saveAll(orgs)
        assert OrgRoleInstance.count() == 3
        assert Site.count() == 3
        assert Site.get(2).org.id == orgs[1].id
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
            org.id == 4
            org.name == "test customer"
            org.role == OrgRoleInstance.OrgRoleType.Customer
    }

    void "query findAllByNameLike"() {
        given :

        def orgs = OrgRoleInstance.findAllByNameLike("%B%")

        expect:

        OrgRoleInstance.count() == 3
        orgs.size() == 1
        orgs[0].getName() == "B"
    }

    void "list with eager fetch query"() {
        given :

        def orgs = OrgRoleInstance.list(fetch:[sites:"eager"])

        expect :
        Site.count() == 3
        orgs.size() == 3
        orgs[1].getName() == "B"
        orgs[1].sites.size() == 1

    }
    void "where query"() {
        given :

        def orgs = OrgRoleInstance.where {name =~ "%B%"}.join('sites').list()

        expect :
        Site.count() == 3
        orgs[0].getName() == "B"
        orgs[0].sites.size() == 1

    }
}
