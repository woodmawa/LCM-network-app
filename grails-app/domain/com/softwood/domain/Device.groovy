package com.softwood.domain

/**
 * logical construct to represent hardware or software that is performing a recognised role
 * and has name and management identity
 */
class Device extends ManagedEntity {

    //OrgRoleInstance org
    //Site site
    //Location location
    Collection domains = []
    //simpler option than deviceRoles - not an entity in this case but  a join table
    Collection<Resource.ResourceRoleType> roles = [] // creates device_roles table no versioning */
    Collection<FlexAttribute> attributes = []

    boolean isFreeStanding = false
    String deviceStatus
    String licence = "none"
    String memory
    String storage
    String numberOfCpu

    static hasMany = [deviceRoles: Resource, domains: NetworkDomain, roles: Resource.ResourceRoleType, attributes:FlexAttribute]

    //static belongsTo = [org:OrgRoleInstance]

    static constraints = {
        //org nullable:false
        //ci nullable : false
        //site nullable:true
        //location nullable:true
        domains nullable:true
        deviceStatus nullable:true
        licence nullable:true
        memory nullable:true
        storage nullable:true
        numberOfCpu nullable:true
        roles nullable:true
        attributes nullable:true
    }


}
