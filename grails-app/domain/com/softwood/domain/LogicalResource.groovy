package com.softwood.domain

abstract class LogicalResource {

    String name

    static constraints = {
        name nullable:true
    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }
}
