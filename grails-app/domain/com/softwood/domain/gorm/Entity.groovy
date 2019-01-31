package com.softwood.domain.gorm

import com.softwood.domain.EntityRelationship

import java.time.LocalDateTime


abstract class Entity {
    String name

    Collection<? extends Relationship> entityReferences = []
    Collection<? extends Relationship> entityReferencedBy = []

    static hasMany = [entityReferences: Relationship, entityReferencedBy: Relationship]

    static mappedBy = [
        entityReferences : "references",
        entityReferencedBy : "referencedBy"
    ]

    static constraints = {
        entityReferences nullable:true
        entityReferencedBy nullable:true
    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }

}
