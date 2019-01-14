package com.softwood.domain

class ProductOffering {

    String offerName
    String name
    String sku

    static constraints = {
        offerName nullable:true
        name nullable:false
        sku nullable:true
    }
}
