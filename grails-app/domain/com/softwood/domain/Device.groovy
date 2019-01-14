package com.softwood.domain

/**
 * logical construct to represent hardware or software that is performing a recognised role
 * and has name and management identity
 */
class Device extends ManagedEntity {

    ManagedEntity ci
    Site site
    Location location
    NetworkDomain domain
    Collection deviceRoles

    boolean isFreeStanding = false
    String licence = "none"
    String memory
    String storage

    static hasMany = [deviceRoles: ResourceRoles]

    static constraints = {
        ci nullable : false
        site nullable:true
        location nullable:true
        domain nullable:true
        memory nullable:true
        storage nullable:true
    }


}
