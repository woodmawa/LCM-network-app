package com.softwood.domain

class MaintenanceAgreement extends Agreement {

    String level
    Map<String, String> category = [:]  //p1 to p5 and sla details

    //static belongsTo = [serviceProvider : OrgRoleInstance, maintainer: OrgRoleInstance]

    // implemented as unidirectional many to one !  mag point to org
    static belongsTo = [maintainer: OrgRoleInstance]

    static constraints = {
        level nullable:false
        //serviceProvider nullable:true
        maintainer nullable:false   //ref to maintainer party

        category nullable:true  //problem no field editor for this in scaffolding !
    }
}
