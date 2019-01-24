package com.softwood.domain

class OrgRoleInstance {

    enum OrgRoleType {
        Supplier,
        Customer,
        Service_Provider,
        Manufacturer,
        Maintainer,
        Indeterminate
    }

    String name
    OrgRoleType role
    Collection<NetworkDomain> domains = []
    Collection<Site> sites = []
    Collection<MaintenanceAgreement> mags       //optional only set if role is service provider


     static hasMany = [domains : NetworkDomain, sites: Site, mags:MaintenanceAgreement]

    static mappedBy  = [mags: "maintainer"]  //disambiguate column in mags


    static constraints = {
        name nullable:false
        role  nullable:true
        domains nullable:true
        sites nullable:true
        mags nullable:true  //optional
    }

    /*  static mapping = {
        sites lazy:false
        domains lazy:false
    }*/

    String toString () {
        "Org (name:$name)[id:$id]"
    }
}
