package com.softwood.domain

class NetworkDomain {

    OrgRoleInstance customer

    static constraints = {
        customer nullable:false
    }
}
