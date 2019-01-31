package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import lcm.network.app.Application
import spock.lang.Specification

@Integration (applicationClass = Application.class)  //appClass - overcomes problem with gorm not loading when running test
@Rollback
class ManagedEntityIntegrationSpec extends Specification {

    def setup() {
        //rely on bootstrap data
    }

    def cleanup() {
    }


    //two of same type works
    void "build relationship between two ManagedEntity "() {

        given:

        ManagedEntity pe = new ManagedEntity (name:"ME-PE#1")
        ManagedEntity ce = new ManagedEntity (name:"ME-CE#1")

        ce.save (failOnError:true)
        pe.save (failOnError:true)


        when : "build a ce and relate the CE and PE  "

        EntityRelationship rel = new EntityRelationship<ManagedEntity, ManagedEntity>()
        rel.name = "i need a PE"
        rel.toRole = "i need this PE"
        rel.toRole = "i am supporting"

        ce.addToLinkedTo(rel)       //save of ce cascades here
        pe.addToLinkedFrom(rel)
        rel.save(failOnError:true)           //cascade? to linkedTo?
        rel




        assert ce.linkedTo.size() == 1
        assert ce.linkedFrom.size() == 0

        assert pe.linkedTo.size() == 0
        assert pe.linkedFrom.size() == 1
        assert EntityRelationship.count() == 1

        then:

        rel.id > 0
        rel.linkedFrom == ce
        rel.linkedTo == pe
        EntityRelationship.count() == 1
        ce.linkedTo.size () == 1
        ce.linkedFrom.size () == 0
        pe.linkedTo.size () == 0
        pe.linkedFrom.size () == 1

    }


}
