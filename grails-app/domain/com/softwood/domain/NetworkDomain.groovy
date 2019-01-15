package com.softwood.domain

class NetworkDomain {

    OrgRoleInstance customer
    String name

    static belongsTo = [customer:OrgRoleInstance]

    static constraints = {
        customer nullable:false
        name nullable:true
    }
}
