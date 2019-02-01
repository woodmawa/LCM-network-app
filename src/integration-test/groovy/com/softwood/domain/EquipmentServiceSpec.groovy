package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EquipmentServiceSpec extends Specification {

    EquipmentService equipmentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Equipment(...).save(flush: true, failOnError: true)
        //new Equipment(...).save(flush: true, failOnError: true)
        //Equipment equipment = new Equipment(...).save(flush: true, failOnError: true)
        //new Equipment(...).save(flush: true, failOnError: true)
        //new Equipment(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //equipment.id
    }

    void "test get"() {
        setupData()

        expect:
        equipmentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Equipment> equipmentList = equipmentService.list(max: 2, offset: 2)

        then:
        equipmentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        equipmentService.count() == 5
    }

    void "test delete"() {
        Long equipmentId = setupData()

        expect:
        equipmentService.count() == 5

        when:
        equipmentService.delete(equipmentId)
        sessionFactory.currentSession.flush()

        then:
        equipmentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Equipment equipment = new Equipment()
        equipmentService.save(equipment)

        then:
        equipment.id != null
    }
}
