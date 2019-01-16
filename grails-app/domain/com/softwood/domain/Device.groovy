package com.softwood.domain

/**
 * logical construct to represent hardware or software that is performing a recognised role
 * and has name and management identity
 */
class Device extends ManagedEntity {

    //OrgRoleInstance org
    Site site
    Location location
    Collection domains = []
    //simpler option than deviceRoles - not an entity in this case but  a join table
    Collection<Resource.ResourceRoleType> roles = [] // creates device_roles table no versioning */
    Collection<FlexAttribute> attributes = []
    Collection<Equipment> buildConfiguration

    boolean isFreeStanding = false
    ProductOffering productType  //ref to portfolio offering if exists
    String deviceStatus = "Operational"  //or Ceased or ...
    String licenceType  //e.g. for cisco 903 would be one of  "metro servcices", or "metro Ip services", "metro aggregation services"
    String licence = "none"
    String memory
    String storage
    String numberOfCpu
    Software runtimeOS

    static hasMany = [deviceRoles: Resource, domains: NetworkDomain, roles: Resource.ResourceRoleType, attributes:FlexAttribute, buildConfiguration: Equipment]

    static belongsTo = [org:OrgRoleInstance]

    static constraints = {
        org nullable:true
        site nullable:true
        location nullable:true
        roles nullable:true
        domains nullable:true
        productType nullable:true
        deviceStatus nullable:true
        licenceType nullable:true
        licence nullable:true
        memory nullable:true
        storage nullable:true
        numberOfCpu nullable:true
        runtimeOS nullable:true
        attributes nullable:true
        buildConfiguration nullable:true
    }


}
