package com.softwood.domain

class MaintenanceAgreement extends Agreement {

    OrgRoleInstance maintainer
    String level

    static constraints = {
        maintainer nullable:false
        level nullable:false
    }
}
