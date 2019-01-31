package com.softwood.domain


/**
 * expressed M:N join for subclasses of RootEntity
 *
 *
 */
class EntityRelationship<E extends RootEntity, F extends RootEntity> {

    String relationshipType
    String name
    String fromRole
    String toRole

    E linkedFrom  //fk to owning entity
    F linkedTo  //fk to other end

    static belongsTo = [linkedFrom:RootEntity, linkedTo:RootEntity]       //setup cascade save action ?

    //static mappedBy = [linkedFrom: "none", linkedTo: "none"]  //seems to need this here - but not in RootEntity

    static constraints = {
        relationshipType unique:true, nullable:true
        name nullable:true
        fromRole nullable:true
        linkedFrom nullable:true
        fromRole nullable:true
        linkedTo nullable:true
    }

    static EntityRelationship createRelationship(String name, E from, F to) {
        if (from == null || to == null)
            return null

        //overcome defect - evaluate  collection before use in entities
        assert from.linkedTo
        assert to.linkedFrom
        EntityRelationship rel = new EntityRelationship()
        rel.name = name
        from.addToLinkedTo (rel)
        to.addToLinkedFrom (rel)
        rel.save(failOnError: true)
        rel

    }

    static Map<String, F> getEntitiesLinkedFromThis (E owningEntity) {
        if (owningEntity == null)
            return []

        if (!owningEntity.isAttached())
            owningEntity.attach() //reattach to session

        Map results = [:]

        owningEntity.linkedTo.each { relationship -> results.putAll ( (relationship.name ?: "<unknown>") : relationship.linkedTo) }
        results
    }

    static Map<String, E> getEntitiesLinkedToThis(F remoteEntity) {
        if (remoteEntity == null)
            return []

        if (!remoteEntity.isAttached())
            remoteEntity.attach() //reattach to session

        Map results = [:]

        remoteEntity.linkedFrom.each { relationship -> results.putAll ( (relationship.name ?: "<unknown>") : relationship.linkedFrom) }
        results
    }
}
