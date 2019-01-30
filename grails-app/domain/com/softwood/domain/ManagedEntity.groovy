package com.softwood.domain

import java.time.LocalDateTime

class ManagedEntity extends RootEntity {

    enum AdministrativeState {
        Locked,
        Unlocked
    }

    enum OperationalState {
        Active,
        shut,
        open,
    }

    boolean isManaged = true
    boolean isVirtual = false
    String manHostName
    String manIpAddress
    AdministrativeState adminStatus  = AdministrativeState.Locked
    OperationalState opStatus = OperationalState.Active
    String usage
    String ownedBy

    LocalDateTime installedDate
    LocalDateTime commissionedDate

    static constraints = {
        isManaged()
        isVirtual()
        manHostName nullable:true
        manIpAddress nullable:true
        adminStatus nullable:true
        opStatus nullable:true
        usage nullable:true
        ownedBy nullable:true
        installedDate nullable:true
        commissionedDate nullable:true
    }
}
