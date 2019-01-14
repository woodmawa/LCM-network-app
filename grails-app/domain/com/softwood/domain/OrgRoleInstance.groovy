package com.softwood.domain

class OrgRoleInstance {

    enum OrgRoleType {
        SUPPLER,
        CUSTOMER,
        MANUFACTURER,
        MAINTAINER,
        INDETERMINATE
    }

    String orgName
    OrgRoleType role

    static constraints = {
        orgName nullable:false
    }
}
