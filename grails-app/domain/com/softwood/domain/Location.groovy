package com.softwood.domain

class Location {

    Site site
    String name
    String floor
    String room
    String geoCode

    static belongsTo = [site:Site]

    static constraints = {
        site nullable: false
        name nullable:false
        floor nullable:true
        room nullable:true
        geoCode nullable:true

    }
}
