package com.softwood.domain

class NetworkDomain {

    OrgRoleInstance customer
    String name

    static belongsTo = [customer:OrgRoleInstance]

    static constraints = {
        customer nullable:false
        name nullable:true
        customer validator :{customer -> customer?.role == OrgRoleInstance.OrgRoleType.Customer}
    }

    String toString () {
        "Cust.Domain (name:$name)[id:$id]"
    }
}
