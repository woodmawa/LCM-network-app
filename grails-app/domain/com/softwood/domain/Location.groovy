package com.softwood.domain

class Location {

    Site site
    String name
    String floor
    String room
    String group

    static constraints = {
        site nullable:true
        name nullable:true
        floor nullable:true
        room nullable:true
        group nullable:true
    }
}
