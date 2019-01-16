package com.softwood.domain

/**
 * root for resources types logical and physical
 */

abstract class Resource {

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

    String name

    static constraints = {
        name nullable:false

    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }
}
