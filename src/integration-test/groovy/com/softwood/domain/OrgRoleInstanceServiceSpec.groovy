package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OrgRoleInstanceServiceSpec extends Specification {

    OrgRoleInstanceService orgRoleInstanceService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new OrgRoleInstance(...).save(flush: true, failOnError: true)
        //new OrgRoleInstance(...).save(flush: true, failOnError: true)
        //OrgRoleInstance orgRoleInstance = new OrgRoleInstance(...).save(flush: true, failOnError: true)
        //new OrgRoleInstance(...).save(flush: true, failOnError: true)
        //new OrgRoleInstance(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //orgRoleInstance.id
    }

    void "test get"() {
        setupData()

        expect:
        orgRoleInstanceService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<OrgRoleInstance> orgRoleInstanceList = orgRoleInstanceService.list(max: 2, offset: 2)

        then:
        orgRoleInstanceList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        orgRoleInstanceService.count() == 5
    }

    void "test delete"() {
        Long orgRoleInstanceId = setupData()

        expect:
        orgRoleInstanceService.count() == 5

        when:
        orgRoleInstanceService.delete(orgRoleInstanceId)
        sessionFactory.currentSession.flush()

        then:
        orgRoleInstanceService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        OrgRoleInstance orgRoleInstance = new OrgRoleInstance()
        orgRoleInstanceService.save(orgRoleInstance)

        then:
        orgRoleInstance.id != null
    }
}
