package com.softwood.domain

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDateTime

class RootEntitySpec extends Specification implements DomainUnitTest<RootEntity> {

    def setup() {
    }

    def cleanup() {
    }

    void "test create new rootEntity handles auto dates "() {
        setup: "create a new entity"
        RootEntity entity = new Device()

        when: "we save object"
        entity.name = "William"
        entity.dateCreated = LocalDateTime.now()
        entity.save ()//(failOnError:true)
        println "date stamp is " + entity.dateCreated.toString()

        then:
        entity.id != null
        assert entity.dateCreated.toString() !=null
    }
}
