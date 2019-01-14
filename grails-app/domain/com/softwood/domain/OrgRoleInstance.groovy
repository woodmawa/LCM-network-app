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
    Collection<MaintenanceAgreement> mags       //optional only set if role is service provider
    //Collection<Device> ci

    static hasMany = [/*ci:Device,*/ mags : MaintenanceAgreement]

    static constraints = {
        name nullable:false
        role  nullable:true
        mags nullable:true
    }
}
