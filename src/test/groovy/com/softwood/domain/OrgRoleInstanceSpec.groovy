package com.softwood.domain

import grails.testing.gorm.DomainUnitTest
import org.hibernate.Criteria
import org.hibernate.FetchMode
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
        println "# of sites : " + Site.count()
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
        println "org b.sites : " + orgs[1].sites

        println "site #2  has org as : " + (Site.list())[1].org

        expect :
        Site.count() == 3
        orgs.size() == 3
        orgs[1].getName() == "B"
        orgs[1].sites.size() == 1

    }
    void "where query"() {
        given :

        def orgs = OrgRoleInstance.where {
            name =~ "%B%" &&
            sites{}
        }.list()

        expect :
        Site.count() == 3
        orgs[0].name == "B"
        orgs[0].sites.size() == 1

    }

    void "where query via many side"() {
        given :

        def orgs = OrgRoleInstance.where {
            sites{org.id == 2}
        }.list()

        expect :
        Site.count() == 3
        orgs[0].name == "B"
        orgs[0].sites.size() == 1

    }


    void "criteria query " () {
        given:

        OrgRoleInstance org

        org = OrgRoleInstance.withCriteria (uniqueResult: true) {
            //eq 'name', "B"
            and {
                idEq(2)
                eq ('name', "B")
            }
        }

        def orgs = OrgRoleInstance.withCriteria {
            //setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            eq 'name', "B"
            //fetchMode 'sites', FetchMode.SELECT
            sites{}
        }

        orgs

        expect:
        org.id == 2
        org.sites.size() == 1

    }



    //this one works
    void "where query from the many side "() {
        given :

        def site = Site.where {
            org.id == 2
        }.list()

        expect :
        Site.count() == 3
        site.org.name == ["B"]

    }

    void "detached criteria with eager fetch " () {
        given:

        def orgs = OrgRoleInstance.createCriteria().listDistinct {
            fetchMode 'sites', FetchMode.SELECT

            sites {
                org {
                    eq 'id', 2
                }
            }
        }

        orgs

        expect:
        orgs.size() == 3

    }
}
