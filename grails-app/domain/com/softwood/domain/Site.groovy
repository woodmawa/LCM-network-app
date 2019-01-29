package com.softwood.domain

class Site {

    static enum SiteRoleType {
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
    String siteCode
    String zone
    SiteRoleType siteType = SiteRoleType.Undefined
    OrgRoleInstance org
    String status
    Collection<Location> locations
    GeographicAddress address

    //has fk to org
    static belongsTo = [org: OrgRoleInstance]

    static embedded = [address: GeographicAddress]

    static hasMany = [locations: Location]

    static constraints = {
        org nullable: false
        name nullable: false
        zone nullable: true
        siteCode nullable:true
        siteType nullable:false
        status nullable: true
        locations nullable: true
        address nullable:true, unique:true      //each site gets its own unique address record
    }

    //configure eager fetch strategies for org
    static mapping = {
        org eager:true
        address eager:true
    }

    String toString () {
        "Site:(name : $name) belonging to org: $org"
    }
}

//embedded class - only sites using this is this model
//address can be simple or full structured
class GeographicAddress {
    String line1
    String line2
    //structured address
    String road
    String district
    String townOrCity
    String countyOrState
    String postalCode
    String country = "UK"   //default
    String region

    String getTown () {
        townOrCity
    }

    String getState() {
        countyOrState
    }

    static constraints = {
        line1 nullable: true
        line2 nullable: true
        road nullable: true
        district nullable:true
        townOrCity nullable:false
        countyOrState nullable: true
        postalCode nullable:true
        country nullable:false
        region nullable: true
    }
    String toString () {
        "Address (town:$townOrCity), country:$country)"
    }

}