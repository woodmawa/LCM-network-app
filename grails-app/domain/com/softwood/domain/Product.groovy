package com.softwood.domain

class Product {

    String offerName
    String name
    String model
    String sku
    String partNumber

    static constraints = {
        offerName nullable:true
        name nullable:false
        model nullable:true
        sku nullable:true
        partNumber nullable:true
    }
}
