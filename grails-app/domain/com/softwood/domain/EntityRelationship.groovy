package com.softwood.domain


/**
 * expressed M:N join for subclasses of RootEntity
 *
 *
 */
class EntityRelationship<M extends RootEntity, N extends RootEntity> {

    String relationshipType
    String name
    String owningRole
    String referencedRole

    M references
    N referencedBy

    //static belongsTo = [references: RootEntity]

    static mappedBy = [references: "none", referencedBy: "none"]

    static constraints = {
        relationshipType unique:true, nullable:true
        name nullable:true
        owningRole nullable:true
        references nullable:true
        referencedRole nullable:true
        referencedBy nullable:true
    }
}
