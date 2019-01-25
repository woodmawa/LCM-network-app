package com.softwood.domain

class Interface extends LogicalResource {

    String port
    String ipAddress
    String netMask
    String macAddress
    String interfaceStatus  //up or down
    String securityLevel
    Device device

    static belongsTo = [device:Device]


    static constraints = {
        port nullable:true
        ipAddress nullable:true
        netMask nullable:true
        macAddress nullable:true
        interfaceStatus nullable:true
        device nullable:true
    }
}
