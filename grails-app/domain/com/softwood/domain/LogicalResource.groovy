package com.softwood.domain

abstract class LogicalResource {

    static constraints = {
    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }
}
