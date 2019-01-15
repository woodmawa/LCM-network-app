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
    //Collection<MaintenanceAgreement> mags       //optional only set if role is service provider
    //Collection<Device> ci

    //static hasMany = [/*ci:Device,*/ mags : MaintenanceAgreement]
    //static hasMany = [mags : MaintenanceAgreement]
    static hasMany = [domains : NetworkDomain, sites: Site]

    //static mappedBy  = [mags: "maintainer"]  //disambiguate column in mags


    static constraints = {
        name nullable:false
        role  nullable:true
        domains nullable:true
        //mags nullable:true  //optional
    }
}
