package com.softwood.domain

import java.time.LocalDate
import java.time.LocalDateTime

abstract class RootEntity {
    //id provided default by grails implicit in infrastructure - shown explicitly here
    Long id
    String name
    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
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
