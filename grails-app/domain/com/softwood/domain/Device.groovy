package com.softwood.domain

import org.hibernate.FetchMode

/**
 * logical construct to represent hardware or software that is performing a recognised role
 * and has name and management identity
 */
class Device extends ManagedEntity {

    //OrgRoleInstance org
    Site site
    Location location
    NetworkDomain domain //can only be in one domain or zero
    //simpler option than deviceRoles - not an entity in this case but  a join table
    Collection<Resource.ResourceRoleType> roles = [] // creates device_roles table no versioning */
    Collection<FlexAttribute> attributes = []
    Collection<Equipment> buildConfiguration = []
    Collection<Interface> interfaces = []
    Collection<Alias> aliasNames = []


    boolean freeStanding = false
    boolean testDevice = false
    Product product  //ref to portfolio offering if exists
    String deviceStatus = "Operational"  //or Ceased or ...
    String licenceType  //e.g. for cisco 903 would be one of  "metro servcices", or "metro Ip services", "metro aggregation services"
    String licence = "none"
    String memory
    String storage
    String numberOfCpu
    Software runtimeOS

    boolean isTestEntity () {
        testDevice
    }

    boolean isFreeStanding () {
        freeStanding
    }

    static hasMany = [deviceRoles: Resource, roles: Resource.ResourceRoleType, attributes:FlexAttribute, buildConfiguration: Equipment, interfaces:Interface, aliasNames:Alias]

    static belongsTo = [org:OrgRoleInstance]

    //configure eager fetch strategies - may be better as a query
    /*static mapping = {
        attributes batchSize: 30
        domain eager: true
        interfaces lazy: false
        aliasNames lazy: false
        site eager:true
        location eager:true
        runtimeOS eager:true
    }*/


    static constraints = {
        org nullable:true
        site nullable:true
        location nullable:true
        roles nullable:true
        domain nullable:true
        product nullable:true
        deviceStatus nullable:true
        licenceType nullable:true
        licence nullable:true
        memory nullable:true
        storage nullable:true
        numberOfCpu nullable:true
        runtimeOS nullable:true
        attributes nullable:true
        buildConfiguration nullable:true
        interfaces nullable:true
        aliasNames nullable:true
    }

    String toString () {
        "Device (manHostname:$manHostName, opState:$opStatus)[id:$id]"
    }

    //Queries
    static getFullDevice (Serializable id) {
        Device.withCriteria (uniqueResult:true) {
            join 'domain'
            join 'site'
            join 'location'
            join 'runtimeOS'
            fetchMode 'interfaces', FetchMode.SELECT
            fetchMode 'attributes', FetchMode.SELECT
            fetchMode 'aliasNames', FetchMode.SELECT
            fetchMode 'buildConfiguration', FetchMode.SELECT

            idEq (id as Long)
        }
    }
}
