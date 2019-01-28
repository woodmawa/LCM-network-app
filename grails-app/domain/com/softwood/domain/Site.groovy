package com.softwood.domain

class Site {

    enum SiteRoleType {
        Datacentre,
        Branch,
        Headoffice,
        Client,
        ProviderEdgePopSite,
        AggregationPopSite,
        LocalExchange,
        Undefined

    }

    String name
    SiteRoleType siteType = SiteRoleType.Undefined
    OrgRoleInstance org
    String status
    Collection<Location> locations

    //has fk to org
    static belongsTo = [org: OrgRoleInstance]

    static hasMany = [locations: Location]

    static constraints = {
        org nullable: false
        name nullable: false
        siteType nullable:false
        status nullable: true
        locations nullable: true
    }

    //configure eager fetch strategies for org
    static mapping = {
        org eager:true
    }

    String toString () {
        "Site:(name : $name) belonging to org: $org"
    }
}