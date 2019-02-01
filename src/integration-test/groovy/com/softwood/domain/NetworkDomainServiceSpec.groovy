package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NetworkDomainServiceSpec extends Specification {

    NetworkDomainService networkDomainService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NetworkDomain(...).save(flush: true, failOnError: true)
        //new NetworkDomain(...).save(flush: true, failOnError: true)
        //NetworkDomain networkDomain = new NetworkDomain(...).save(flush: true, failOnError: true)
        //new NetworkDomain(...).save(flush: true, failOnError: true)
        //new NetworkDomain(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //networkDomain.id
    }

    void "test get"() {
        setupData()

        expect:
        networkDomainService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NetworkDomain> networkDomainList = networkDomainService.list(max: 2, offset: 2)

        then:
        networkDomainList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        networkDomainService.count() == 5
    }

    void "test delete"() {
        Long networkDomainId = setupData()

        expect:
        networkDomainService.count() == 5

        when:
        networkDomainService.delete(networkDomainId)
        sessionFactory.currentSession.flush()

        then:
        networkDomainService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NetworkDomain networkDomain = new NetworkDomain()
        networkDomainService.save(networkDomain)

        then:
        networkDomain.id != null
    }
}
