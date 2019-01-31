package com.softwood.domain

class Interface extends LogicalResource {

    static enum InterfaceState {
        Up,
        Down,
        AdministrativelyDown,
        DownErrorDisabled,
        Indeterminate
    }

    String port
    String bandwidth
    String ipAddress
    String netMask
    String macAddress
    InterfaceState interfaceStatus  = InterfaceState.Indeterminate
    String securityLevel
    Device device
    Collection<Interface> subInterfaces
    Interface parent

    static belongsTo = [device:Device, parent:Interface]

    static hasMany = [subInterfaces:Interface]

    static constraints = {
        port nullable:true
        bandwidth nullable:true
        ipAddress nullable:true
        netMask nullable:true
        macAddress nullable:true
        interfaceStatus nullable:true
        securityLevel nullable:true
        device nullable:true
        subInterfaces nullable:true
        parent nullable:true
    }
}
