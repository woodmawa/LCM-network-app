package com.softwood.domain

class ProviderNetwork {

    String name
    OrgRoleInstance serviceProvider
    Collection<Device> devices

    static belongsTo = [serviceProvider: OrgRoleInstance]

    static hasMany = [devices:Device]

    static constraints = {
        name nullable:false
        serviceProvider nullable:false, validator :{csp -> csp?.role == OrgRoleInstance.OrgRoleType.ServiceProvider}
        devices nullable: true

    }

    static mappedBy = []
}
