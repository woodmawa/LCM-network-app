package com.softwood.domain

import java.time.LocalDateTime

class ManagedEntity extends RootEntity {


    boolean isManaged = true
    boolean isVirtual = false
    String manHostName
    String manIpAddress
    String adminStatus
    String opStatus
    String usage
    String ownedBy
    LocalDateTime installedDate
    LocalDateTime commissionedDate

    static belongsTo = [org:OrgRoleInstance]

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
