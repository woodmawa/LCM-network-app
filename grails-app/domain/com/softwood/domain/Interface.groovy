package com.softwood.domain

class Interface extends LogicalResource {

    String ipAddress
    String port
    String netMask
    String macAddress
    String interfaceStatus  //up or down


    static constraints = {
        ipAddress nullable:true
        port nullable:true
        netMask nullable:true
        macAddress nullable:true
        interfaceStatus nullable:true
    }
}
