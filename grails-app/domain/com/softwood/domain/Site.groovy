package com.softwood.domain

class Site {

    String name
    OrgRoleInstance org

    static constraints = {
        name nullable:false
    }
}
