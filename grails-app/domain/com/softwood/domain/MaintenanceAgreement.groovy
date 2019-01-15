package com.softwood.domain

class MaintenanceAgreement extends Agreement {

    String level
    Map category = [:]

    //static belongsTo = [serviceProvider : OrgRoleInstance, maintainer: OrgRoleInstance]

    // implemented as unidirectional many to one !  mag point to org
    static belongsTo = [maintainer: OrgRoleInstance]

    static constraints = {
        level nullable:false
        //serviceProvider nullable:true
        maintainer nullable:false   //ref to maintainer party

        category nullable:false
    }
}
