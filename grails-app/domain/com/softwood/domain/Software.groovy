package com.softwood.domain

import java.time.LocalDateTime

class Software {

    static enum SoftwareType {
        OperatingSystem,
        InternetworkOperatingSystem,
        Application,
        BusinessApplication,
        Patch,
        SecurityPatch
    }

    LocalDateTime dateCreated
    LocalDateTime lastUpdated
    SoftwareType type
    String name
    String version
    String imageLibraryUri
    OrgRoleInstance supplier  //optional

    static constraints = {
        type nullable:false
        name nullable:false
        version nullable: true
        imageLibraryUri nullable: true
        supplier nullable:true
    }

    def beforeInsert() {
        if (dateCreated == null) {
            println "software injected dateCreated "
            dateCreated = LocalDateTime.now()
        }
        if (lastUpdated == null) {
            println "software injected lastUpdated "
            lastUpdated = LocalDateTime.now()
        }
    }
}
