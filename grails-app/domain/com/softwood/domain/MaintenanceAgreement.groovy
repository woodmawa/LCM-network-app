package com.softwood.domain

class MaintenanceAgreement extends Agreement {

    //OrgRoleInstance maintainer
    String level

    static belongsTo = [serviceProvider : OrgRoleInstance]
    static constraints = {
        serviceProvider nullable:true
        level nullable:false
    }
}
