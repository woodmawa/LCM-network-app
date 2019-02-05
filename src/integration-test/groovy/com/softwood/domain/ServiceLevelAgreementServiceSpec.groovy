package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ServiceLevelAgreementServiceSpec extends Specification {

    ServiceLevelAgreementService serviceLevelAgreementService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ServiceLevelAgreement(...).save(flush: true, failOnError: true)
        //new ServiceLevelAgreement(...).save(flush: true, failOnError: true)
        //ServiceLevelAgreement serviceLevelAgreement = new ServiceLevelAgreement(...).save(flush: true, failOnError: true)
        //new ServiceLevelAgreement(...).save(flush: true, failOnError: true)
        //new ServiceLevelAgreement(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //serviceLevelAgreement.id
    }

    void "test get"() {
        setupData()

        expect:
        serviceLevelAgreementService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ServiceLevelAgreement> serviceLevelAgreementList = serviceLevelAgreementService.list(max: 2, offset: 2)

        then:
        serviceLevelAgreementList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        serviceLevelAgreementService.count() == 5
    }

    void "test delete"() {
        Long serviceLevelAgreementId = setupData()

        expect:
        serviceLevelAgreementService.count() == 5

        when:
        serviceLevelAgreementService.delete(serviceLevelAgreementId)
        sessionFactory.currentSession.flush()

        then:
        serviceLevelAgreementService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ServiceLevelAgreement serviceLevelAgreement = new ServiceLevelAgreement()
        serviceLevelAgreementService.save(serviceLevelAgreement)

        then:
        serviceLevelAgreement.id != null
    }
}
