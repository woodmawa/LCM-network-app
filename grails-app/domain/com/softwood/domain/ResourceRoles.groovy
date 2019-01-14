package com.softwood.domain

class ResourceRoles {

    static enum ResourceRoleType {
        Switch,
        Router,
        Aggegation,
        ProviderEdge,
        Core,
        CustomerEdge,
        Repeater,
        Hub,
        DSLAM,
        Server,
        ProxyServer,
        Firewall,
        NetworkAddressTranslation,
        Bridge,
        Modem,
        WirelessAccessPoint,
        Gateway
    }

    ResourceRoleType roleType

    static belongsTo = [device: Device]

    static constraints = {
        roleType nullable:false
    }
}
