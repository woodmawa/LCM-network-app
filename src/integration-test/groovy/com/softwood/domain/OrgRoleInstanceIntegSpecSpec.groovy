package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import org.hibernate.FetchMode
import org.hibernate.LazyInitializationException
import spock.lang.Shared
import spock.lang.Specification

@Integration
@Rollback
class OrgRoleInstanceIntegSpecSpec extends Specification {

    //operates on separate transaction thats not rolled back
    @Shared
    List<OrgRoleInstance> orgs = []

    @Shared
    List<OrgRoleInstance> sites = []

    @Shared
    NetworkDomain netDomain

    @Shared
    def bootstrapPreExistingOrgsCount, bootstrapPreExistingSitesCount

    //runs in transaction thats not rolled back for each test
    def setup() {
        def site

        bootstrapPreExistingOrgsCount = OrgRoleInstance.count()
        bootstrapPreExistingSitesCount = Site.count()
        //println "pre exist orgs:$boostrapPreExistingOrgsCount + pre exist sites: $boostrapPreExistingSitesCount"
        assert bootstrapPreExistingOrgsCount == 4
        assert bootstrapPreExistingSitesCount == 2

        ["A","B","C"].each {
            OrgRoleInstance org = new OrgRoleInstance(name:"test$it", role:OrgRoleInstance.OrgRoleType.Customer)
            org.addToSites(site = new Site( name: "test$it's Head Office", status:"open", org:org))
            orgs << org
            sites << site

        }
        orgs[2].addToSites (site = new Site( name: "${orgs[2].name}'s Branch Office", status:"open", org:orgs[2]))
        orgs[2].addToDomains(netDomain = new NetworkDomain (name:"corporate WAN", customer:[orgs[2]]))
        sites << site

        OrgRoleInstance.saveAll(orgs)
        assert orgs.size() == 3
        assert sites.size() ==4
        assert OrgRoleInstance.count() == 3 + bootstrapPreExistingOrgsCount
        assert Site.count() == 4 + bootstrapPreExistingSitesCount
        assert Site.get(4).org.id == orgs[1].id
        println "setup integration test data"

    }

    //manual cleanup of integration test data
    def cleanup() {

        orgs.each {OrgRoleInstance org ->
            org.sites.each {it.delete()}
            org.delete(flush:true)
            assert OrgRoleInstance.exists(org.id) == false
        }
        println "deleted integration test data"
    }

    void "Orgs list with eager fetch query"() {
        given :

        def orgs = OrgRoleInstance.list(fetch:[sites:"eager"])
        println "org ${orgs[6].name} sites : " + orgs[5].sites

        println "test site #2  has org as : " + (Site.list())[3].org

        expect :
        Site.count() == 4 + bootstrapPreExistingSitesCount
        orgs.size() == 3 + bootstrapPreExistingOrgsCount
        orgs[5].getName() == "testB"
        orgs[5].sites.size() == 1

    }

    void "orgs where query triggering eager site get"() {
        given :

        //where clause returns instance of DetachedCriteria, so have to trigger with a list/get etc
        def orgs = OrgRoleInstance.where {
            name =~ "%testB%" &&
                    sites{}
        }.list()

        expect :
        orgs.size() == 1
        orgs[0].name == "testB"
        orgs[0].sites.size() == 1

    }

    void "withCriteria query with eager site fetch (two selects)  " () {
        given:

        OrgRoleInstance org

        //with criteria runs the query for you unlike createCriteria() which returns  a detachedCriteria
        org = OrgRoleInstance.withCriteria (uniqueResult: true) {
            fetchMode ("sites", FetchMode.SELECT)
            idEq(7L)  //internally wont cast Integer to long, so set it explicitly
            sites{}
        }

        /*def orgs = OrgRoleInstance.withCriteria {
            //setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            eq 'name', "B"
            //fetchMode 'sites', FetchMode.SELECT
            sites{}
        }*/

        expect:
        org.id == 7
        org.sites.size() == 2

    }

    void "detached criteria (with distinct) with eager fetch " () {
        given:


        def orgs = OrgRoleInstance.createCriteria().listDistinct {
            //fetchMode 'sites', FetchMode.SELECT
            join 'sites'
            sites {
                org {
                    eq 'id', 6L
                }
            }
        }

        def site = orgs[0].sites[0]

        expect:
        orgs.size() == 1
        orgs[0].sites.size() == 1
        site.name == "testB's Head Office"

    }

    void "where query on id only without list (fetch eager) " () {
        given :

        def orgs = OrgRoleInstance.where {
            id == 7L
        }.list ()

        def branch = orgs[0].sites[0]

        when:

        println "branch "+ branch.name  //try and access branch

        then:
        /*
        -- seems to do an eager fetch on sites+domains, even though i didnt ask it to
         not quite sure why - separate exploration round that i think
         */
        //LazyInitializationException ex = thrown()
        orgs.size() == 1
        orgs[0].sites.size() == 2
        orgs[0].domains.size() == 1

    }

}
