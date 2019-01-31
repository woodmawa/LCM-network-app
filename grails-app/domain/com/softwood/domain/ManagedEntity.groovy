package com.softwood.domain

import java.time.LocalDateTime

class ManagedEntity extends RootEntity {

    /* static {
        //globally enable left shift for usageType[]
        UsageType[].metaClass.leftShift  {
            delegate.add (it)
        }
    }*/

    static enum AdministrativeState {
        Locked,
        Unlocked
    }

    static enum OperationalState {
        Active,
        shut,
        open,
    }

    static enum UsageType {
        Voice,
        Data,
        Security,
        Lan,
        Wan,
        Wireless,
        Mobilility,
        AccessPoint,
        Unknown
    }

    boolean isManaged = true
    boolean isVirtual = false
    String manHostName
    String manIpAddress
    AdministrativeState adminStatus  = AdministrativeState.Locked
    OperationalState opStatus = OperationalState.Active
    Collection<UsageType> usage = []
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
