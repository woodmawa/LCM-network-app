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

    M references  //fk to owning entity
    N referencedBy  //fk to other end

    static mappedBy = [references: "none", referencedBy: "none"]  //seems to need this here - but not in RootEntity

    static constraints = {
        relationshipType unique:true, nullable:true
        name nullable:true
        owningRole nullable:true
        references nullable:true
        referencedRole nullable:true
        referencedBy nullable:true
    }

    static EntityRelationship createRelationship(String name, M from, N to) {
        if (from == null || to == null)
            return null

        EntityRelationship rel = new EntityRelationship()
        rel.name = name
        rel.owningRole = from.name
        rel.referencedRole = to.name
        from.addToEntityReferences (from)
        to.addToEntityReferencedBy (to)
        rel.save(failOnError: true)
        rel

    }

    static Map<String, N> getReferencedEntitiesFrom (M from) {
        if (from == null)
            return []

        if (!from.isAttached())
            from.attach() //reattach to session

        Map results = [:]

        from.entityReferences.each {relationship -> results.putAll ( (relationship.name ?: "<unknown>") : relationship.referencedBy) }
        results
    }

    static Map<String, M> getReferencingEntitiesTo (N to) {
        if (to == null)
            return []

        if (!to.isAttached())
            to.attach() //reattach to session

        Map results = [:]

        to.entityReferencedBy.each {relationship -> results.putAll ( (relationship.name ?: "<unknown>") : relationship.references) }
        results
    }
}
