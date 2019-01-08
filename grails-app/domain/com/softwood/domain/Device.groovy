package com.softwood.domain

class Device extends ManagedEntity {

    boolean isFreeStanding = false
    String licence = "none"
    String memory
    String storage

    static constraints = {
        memory nullable:true
        storage nullable:true
    }


}
