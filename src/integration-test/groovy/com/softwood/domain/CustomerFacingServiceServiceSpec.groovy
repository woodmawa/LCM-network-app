package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CustomerFacingServiceServiceSpec extends Specification {

    CustomerFacingServiceService customerFacingServiceService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CustomerFacingService(...).save(flush: true, failOnError: true)
        //new CustomerFacingService(...).save(flush: true, failOnError: true)
        //CustomerFacingService customerFacingService = new CustomerFacingService(...).save(flush: true, failOnError: true)
        //new CustomerFacingService(...).save(flush: true, failOnError: true)
        //new CustomerFacingService(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //customerFacingService.id
    }

    void "test get"() {
        setupData()

        expect:
        customerFacingServiceService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CustomerFacingService> customerFacingServiceList = customerFacingServiceService.list(max: 2, offset: 2)

        then:
        customerFacingServiceList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        customerFacingServiceService.count() == 5
    }

    void "test delete"() {
        Long customerFacingServiceId = setupData()

        expect:
        customerFacingServiceService.count() == 5

        when:
        customerFacingServiceService.delete(customerFacingServiceId)
        sessionFactory.currentSession.flush()

        then:
        customerFacingServiceService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CustomerFacingService customerFacingService = new CustomerFacingService()
        customerFacingServiceService.save(customerFacingService)

        then:
        customerFacingService.id != null
    }
}
