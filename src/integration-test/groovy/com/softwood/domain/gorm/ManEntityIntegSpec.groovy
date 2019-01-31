package com.softwood.domain.gorm


import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import lcm.network.app.Application
import spock.lang.Specification

import java.time.LocalDateTime

@Integration (applicationClass = Application.class)  //appClass - overcomes problem with gorm not loading when running test
@Rollback
class ManEntityIntegSpec extends Specification {

    def setup() {
        //rely on bootstrap data
        ManEntity pe = new ManEntity (name:"PE-prebuild", manHostName: "Pre built test PE#1000")
        pe.save (failOnError:true)
    }

    def cleanup() {
    }

    //succeeds
    void "relate two manual built ManEntities  "() {

        given: "use of static query method using with criteria "

        ManEntity newPe = new ManEntity (name:"PE", manHostName: "PE#1")
        ManEntity ce = new ManEntity (name:"CE", manHostName: "CE#1")

        ce.save(failOnError:true)
        newPe.save(failOnError:true)

        when:

        Relationship  rel = new Relationship<ManEntity, ManEntity>(name:"ce-pe-link")
        ce.addToEntityReferences(rel)
        newPe.addToEntityReferencedBy(rel)
        rel.save(failOnError:true)

        ManEntity pe  = newPe


        then:
        rel.id == 1
        ce.entityReferences.size() == 1
        ce.entityReferencedBy.size() == 0
        pe.entityReferences.size() == 0
        pe.entityReferencedBy.size() == 1
        rel.references == ce
        rel.referencedBy == pe
    }

    //fails
    void "relate a manual built ManEntity, and one from DB   "() {

        given: "use of static query method using with criteria "

        ManEntity prePe = ManEntity.findByNameLike ("PE-prebuild")
        ManEntity ce = new ManEntity (name:"CE", manHostName: "CE#1")

        ce.save(failOnError:true)

        when:

        assert prePe.entityReferencedBy.size() == 0  //if commented out test will fail

        Relationship  rel = new Relationship<ManEntity, ManEntity>(name:"ce-pe-link")
        ce.addToEntityReferences(rel)
        //pe.addToEntityReferencedBy(rel)
        prePe.addToEntityReferencedBy (rel)
        rel.save(failOnError:true)

        ManEntity pe  = prePe


        then:
        rel.id == 1
        ce.entityReferences.size() == 1
        ce.entityReferencedBy.size() == 0
        pe.entityReferences.size() == 0
        pe.entityReferencedBy.size() == 1
        rel.references == ce
        rel.referencedBy == pe
    }

}
