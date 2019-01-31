package com.softwood.domain


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

    Collection<? extends EntityRelationship> linkedTo = []
    Collection<? extends EntityRelationship> linkedFrom = []

    static hasMany = [linkedTo: EntityRelationship, linkedFrom: EntityRelationship]

    static mappedBy = [
            linkedTo  : 'linkedFrom',  //map toRole to EntityRelationship.linkfrom
            linkedFrom: 'linkedTo'     //map toRole to EntityRelationship.linkedTo
    ]

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }

    static constraints = {
        linkedTo nullable:true
        linkedFrom nullable:true
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
