package com.softwood.domain

class OrgRoleInstance {

    static enum OrgRoleType {
        Supplier,
        Customer,
        ServiceProvider,
        Manufacturer,
        Maintainer,
        Indeterminate
    }

    String name
    OrgRoleType role
    Collection<NetworkDomain> domains = []
    Collection<ProviderNetwork> providerNetworks = []
    Collection<Site> sites = []
    Collection<MaintenanceAgreement> mags   = []    //optional only set if role is service provider
    Collection<ServiceLevelAgreement> slags = []
    Collection<CustomerFacingService> services = []

     static hasMany = [domains : NetworkDomain, sites: Site, mags:MaintenanceAgreement, slags:ServiceLevelAgreement, providerNetworks:ProviderNetwork, services:CustomerFacingService]

    static mappedBy  = [mags: "maintainer", providerNetworks: "serviceProvider"]  //disambiguate column in ProviderNetwork


    static constraints = {
        name nullable:false
        role  nullable:true
        domains nullable:true
        providerNetworks nullable:true
        sites nullable:true
        mags nullable:true  //optional
        services nullable:true
    }

    /*  static mapping = {
        sites lazy:false
        domains lazy:false
    }*/

    String toString () {
        "Org (name:$name, in role:${role}})[id:$id]"
    }

    String displayName () {
        "Org (name:$name, in role:${role})[id:$id]"
    }
}
