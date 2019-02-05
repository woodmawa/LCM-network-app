package com.softwood.domain

class ServiceLevelAgreement extends Agreement {

    static enum ServiceLevelAgreementType {
        Platinum,
        gold,
        Silver,
        Bronze,
        Custom
    }

    String name
    String level = ServiceLevelAgreementType.Bronze
    Map<String, String> category = [:]  //p1 to p5 and sla details
    OrgRoleInstance customer
    Collection<CustomerFacingService> services = []

    //static belongsTo = [serviceProvider : OrgRoleInstance, maintainer: OrgRoleInstance]

    // implemented as unidirectional many to one !  mag point to org
    static belongsTo = [customer:OrgRoleInstance]

    static hasMany = [services:CustomerFacingService]


    static constraints = {
        name nullable:false
        level nullable:false
        customer nullable:false
        services nullable:true  //might need extra login in controller/service when adding a cfs

        category nullable:true  //problem no field editor for this in scaffolding !
    }

    String toString () {
        "Sla (name:$name, status:$status)[id:$id]"
    }
}
