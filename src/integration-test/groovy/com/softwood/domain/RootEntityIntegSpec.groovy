package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class RootEntityIntegSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create "() {
        given : "new object"
        RootEntity entity = new Device()
        entity.name = "william"
        entity.save(failOnError:true)
        println "created on" + entity.dateCreated

        expect: " dates are not null"
        entity.id
        entity.dateCreated

    }
}
