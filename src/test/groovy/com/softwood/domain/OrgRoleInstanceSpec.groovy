package com.softwood.domain

import grails.testing.gorm.DomainUnitTest
import org.grails.orm.hibernate.HibernateDatastore
import org.hibernate.Criteria
import org.hibernate.FetchMode
//import org.hibernate.criterion.CriteriaSpecification
import org.springframework.transaction.PlatformTransactionManager
import spock.lang.AutoCleanup
import spock.lang.Shared
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
        orgs[2].addToSites (new Site( name: "${orgs[2].name}'s Branch Office", status:"open", org:orgs[2]))
        OrgRoleInstance.saveAll(orgs)
        assert OrgRoleInstance.count() == 3
        println "# of sites : " + Site.count()
        assert Site.count() == 4
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
        Site.count() == 4
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
        Site.count() == 4
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
            fetchMode ("sites", FetchMode.SELECT)
            idEq(3)
            //eq 'name', "B"
            /*and {
                idEq(2)
                eq ('name', "B")
            }*/
            sites{}
        }

        /*def orgs = OrgRoleInstance.withCriteria {
            //setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            eq 'name', "B"
            //fetchMode 'sites', FetchMode.SELECT
            sites{}
        }*/

        orgs

        expect:
        org.id == 3
        org.sites.size() == 2

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
            //fetchMode 'sites', FetchMode.SELECT
            join 'sites'
            sites {
                org {
                    eq 'id', 2
                }
            }
        }

        def site = orgs.sites[0]
        site

        expect:
        orgs.size() == 1
        orgs.site.size() == 1
        site.name == "B's Head Office"

    }

    void "where query and individual get " () {
        given :

        def orgs = OrgRoleInstance.where {
            id == 3
        }.list (fetch:[sites:"eager"])

        /*def orgs = OrgRoleInstance.where {
            sites.size() == 2
        }.list ()*/ /*(fetch:[sites:"eager"])*/

             def org = OrgRoleInstance.get(2)
            List orgSites = org.sites

            def branch = Site.get(4)

            assert branch.org.is (orgs[0])

            //println orgs[0].sites[1].name  //orgs[0].sites is null !


        expect:
        orgs.size() == 1
        orgs[0].sites[1].name

    }

}
