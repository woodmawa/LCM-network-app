package com.softwood.domain

import java.time.LocalDateTime

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

    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    String name

    static constraints = {
        name nullable:false

    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }

    def beforeInsert() {
        if (dateCreated == null) {
            println "logical resource : injected date created  "
            dateCreated = LocalDateTime.now()
        }
        if (lastUpdated == null) {
            println "logical resource : injected last updated  "
            lastUpdated = LocalDateTime.now()
        }
    }
}
