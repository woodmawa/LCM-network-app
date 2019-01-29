package com.softwood.domain

import static com.softwood.domain.PhysicalResource.*

class Product {

    String offerName
    String name
    String description
    String model
    String sku
    String partNumber
    OrgRoleInstance manufacturer    //uni directional many to one

    ResourceUnitOfMeasure dimUom = ResourceUnitOfMeasure.Millimeters  //default
    float depth
    float width
    float height
    PhysicalResource.RackUnit uSize

    ResourceUnitOfMeasure weightUom = ResourceUnitOfMeasure.Kilogrammes //default
    float weight


    static constraints = {
        offerName nullable:true
        name nullable:false
        description nullable:true  //full description
        model nullable:true
        sku nullable:true
        partNumber nullable:true
        manufacturer nullable:true, validator: {manu-> manu?.role == OrgRoleInstance.OrgRoleType.Manufacturer}

        //physicals
        dimUom nullable:false
        weightUom nullable:false
        depth ()
        width ()
        height()
        weight ()
        uSize nullable:true
    }
}
