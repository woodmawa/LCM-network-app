package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProviderNetworkServiceSpec extends Specification {

    ProviderNetworkService providerNetworkService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProviderNetwork(...).save(flush: true, failOnError: true)
        //new ProviderNetwork(...).save(flush: true, failOnError: true)
        //ProviderNetwork providerNetwork = new ProviderNetwork(...).save(flush: true, failOnError: true)
        //new ProviderNetwork(...).save(flush: true, failOnError: true)
        //new ProviderNetwork(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //providerNetwork.id
    }

    void "test get"() {
        setupData()

        expect:
        providerNetworkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProviderNetwork> providerNetworkList = providerNetworkService.list(max: 2, offset: 2)

        then:
        providerNetworkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        providerNetworkService.count() == 5
    }

    void "test delete"() {
        Long providerNetworkId = setupData()

        expect:
        providerNetworkService.count() == 5

        when:
        providerNetworkService.delete(providerNetworkId)
        sessionFactory.currentSession.flush()

        then:
        providerNetworkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProviderNetwork providerNetwork = new ProviderNetwork()
        providerNetworkService.save(providerNetwork)

        then:
        providerNetwork.id != null
    }
}
