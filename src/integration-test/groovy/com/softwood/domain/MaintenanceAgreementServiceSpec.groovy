package com.softwood.domain

import com.softwood.service.MaintenanceAgreementService
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MaintenanceAgreementServiceSpec extends Specification {

    MaintenanceAgreementService maintenanceAgreementService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MaintenanceAgreement(...).save(flush: true, failOnError: true)
        //new MaintenanceAgreement(...).save(flush: true, failOnError: true)
        //MaintenanceAgreement maintenanceAgreement = new MaintenanceAgreement(...).save(flush: true, failOnError: true)
        //new MaintenanceAgreement(...).save(flush: true, failOnError: true)
        //new MaintenanceAgreement(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //maintenanceAgreement.id
    }

    void "test get"() {
        setupData()

        expect:
        maintenanceAgreementService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MaintenanceAgreement> maintenanceAgreementList = maintenanceAgreementService.list(max: 2, offset: 2)

        then:
        maintenanceAgreementList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        maintenanceAgreementService.count() == 5
    }

    void "test delete"() {
        Long maintenanceAgreementId = setupData()

        expect:
        maintenanceAgreementService.count() == 5

        when:
        maintenanceAgreementService.delete(maintenanceAgreementId)
        sessionFactory.currentSession.flush()

        then:
        maintenanceAgreementService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MaintenanceAgreement maintenanceAgreement = new MaintenanceAgreement()
        maintenanceAgreementService.save(maintenanceAgreement)

        then:
        maintenanceAgreement.id != null
    }
}
