package com.softwood.domain

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * abstract entity record type - essentially a named, and manageable CI
 *
 * known subclases :
 * CustomerFacingService
 * ResourceFacingService
 * Device
 *
 */
abstract class RootEntity {
    //id provided default by grails implicit in infrastructure - shown explicitly here
    Long id
    String name
    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    Collection<? extends EntityRelationship> entityReferences
    Collection<? extends EntityRelationship> entityReferencedBy

    static hasMany = [entityReferences: EntityRelationship, entityReferencedBy: EntityRelationship]

    static mappedBy = [
        entityReferences : "references",
        entityReferencedBy : "referencedBy"
    ]

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }

    static constraints = {
        entityReferences nullable:true
        entityReferencedBy nullable:true
    }

    def beforeInsert() {
        println "before insert called "
        if (dateCreated == null) {
            dateCreated = LocalDateTime.now()
        }
        if (lastUpdated == null) {
            lastUpdated = LocalDateTime.now()
        }
    }
}
