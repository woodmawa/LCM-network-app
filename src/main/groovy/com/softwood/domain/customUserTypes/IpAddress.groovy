package com.softwood.domain.customUserTypes

import groovy.transform.Immutable

@Immutable
class IpAddress {

    private final InetAddress ipAddress

    public getAddress () {
        return ipAddress.getAddress()
    }
}
