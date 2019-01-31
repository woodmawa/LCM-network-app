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
        rel.owningRole = "i need this PE"
        rel.referencedRole = "i am supporting"

        ce.addToEntityReferences(rel)       //save of ce cascades here
        pe.addToEntityReferencedBy(rel)
        rel.save(failOnError:true)           //cascade? to referencedBy?
        rel




        assert ce.entityReferences.size() == 1
        assert ce.entityReferencedBy.size() == 0

        assert pe.entityReferences.size() == 0
        assert pe.entityReferencedBy.size() == 1
        assert EntityRelationship.count() == 1

        then:

        rel.id > 0
        rel.references == ce
        rel.referencedBy == pe
        EntityRelationship.count() == 1
        ce.entityReferences.size () == 1
        ce.entityReferencedBy.size () == 0
        pe.entityReferences.size () == 0
        pe.entityReferencedBy.size () == 1

    }


}
