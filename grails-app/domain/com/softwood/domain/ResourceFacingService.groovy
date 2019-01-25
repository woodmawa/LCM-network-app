package com.softwood.domain

class ResourceFacingService extends RootEntity {

    Collection<? extends Resource> requiredResources = []

    static constraints = {
        requiredResources nullable:true
    }
}
