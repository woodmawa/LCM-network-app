package com.softwood.domain

class Alias {

    String name
    String ipAddress
    String netMask
    Device device

    static belongsTo = [device:Device]

    static constraints = {
        name nullable:false
        ipAddress nullable:true
        netMask nullable:true
        device nullable:false
    }
}
