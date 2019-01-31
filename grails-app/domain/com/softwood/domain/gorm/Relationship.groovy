package com.softwood.domain.gorm


import com.softwood.domain.RootEntity


class Relationship<E extends Entity, F extends Entity> {

    String name = "<new>"

    E references
    F referencedBy

    static belongsTo = [references:Entity, referencedBy:Entity]

    static constraints = {
    }
}
