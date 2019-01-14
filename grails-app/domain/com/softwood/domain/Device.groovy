package com.softwood.domain

/**
 * logical construct to represent hardware or software that is performing a recognised role
 * and has name and management identity
 */
class Device extends ManagedEntity {

    //OrgRoleInstance org
    //Site site
    //Location location
    //NetworkDomain domain
    //Collection<ResourceRole> deviceRoles

    boolean isFreeStanding = false
    String licence = "none"
    String memory
    String storage

    //static hasMany = [deviceRoles: ResourceRole]

    //static belongsTo = [org:OrgRoleInstance]

    static constraints = {
        //org nullable:false
        //ci nullable : false
        //site nullable:true
        //location nullable:true
        //domain nullable:true
        memory nullable:true
        storage nullable:true
    }


}
