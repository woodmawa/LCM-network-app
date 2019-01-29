package com.softwood.domain

import org.hibernate.FetchMode

/**
 * logical construct to represent hardware or software that is performing a recognised role
 * and has name and management identity
 */
class Device extends ManagedEntity {

    OrgRoleInstance org
    Site site
    Location location
    NetworkDomain domain //can only be in one domain or zero
    ProviderNetwork vfNetwork  //can be part of one CSP network domain
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

    static belongsTo = [org:OrgRoleInstance]  //dont at providerNetwork as belongs to as we dont want cascade delete

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
        domain nullable:true  , validator : {NetworkDomain domain, Device dev ->
            //assumes org has been set
            if (domain == null)
                return true
            if (dev.org == null)
                log.debug "org was null, trying to validate domain is in orgs.domains list - so org must be set first"
            NetworkDomain[] validDomains = dev?.org?.domains ?: []
            boolean test = validDomains.contains(domain)
            test
        }
        vfNetwork nullable:true , validator : {ProviderNetwork vfNetwork, Device dev ->
            if (vfNetwork == null)  return true
            OrgRoleInstance vf = OrgRoleInstance.findByNameAndRole ("Vodafone", OrgRoleInstance.OrgRoleType.ServiceProvider)
            ProviderNetwork[] networks = vf?.providerNetworks ?: []
            boolean test = networks.contains (vfNetwork)
            if (test == false)
                log.debug "Vodafone provider does not yet have any ProviderNetworks to validate to, please create and save any provider networks before assigning to device, then save "
            test
        }//ensure its in vf's list of provider networks }*/
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
    static Device getFullDeviceById (Serializable id) {
        Device.withCriteria (uniqueResult:true) {
            join 'domain'
            join 'providerNetwork'
            join 'site'
            join 'location'
            join 'runtimeOS'
            fetchMode 'product', FetchMode.SELECT
            fetchMode 'interfaces', FetchMode.SELECT
            fetchMode 'attributes', FetchMode.SELECT
            fetchMode 'aliasNames', FetchMode.SELECT
            fetchMode 'buildConfiguration', FetchMode.SELECT

            idEq (id as Long)
        }
    }

    //Queries
    static List<Device> getFullDeviceBySite (Serializable sid) {
        Device.withCriteria (uniqueResult:true) {
            join 'domain'
            join 'providerNetwork'
            join 'site'
            join 'location'
            join 'runtimeOS'
            fetchMode 'product', FetchMode.SELECT
            fetchMode 'interfaces', FetchMode.SELECT
            fetchMode 'attributes', FetchMode.SELECT
            fetchMode 'aliasNames', FetchMode.SELECT
            fetchMode 'buildConfiguration', FetchMode.SELECT

            site {idEq (sid as Long)}
        }
    }
}
