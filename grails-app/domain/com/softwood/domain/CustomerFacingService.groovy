package com.softwood.domain

class CustomerFacingService extends RootEntity {

    Product product

    static constraints = {
        product nullable:true
    }
}
