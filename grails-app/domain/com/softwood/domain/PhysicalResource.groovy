package com.softwood.domain

abstract class PhysicalResource extends Resource {

    enum ResourceUnitOfMeasure {
        Meters,
        Centimeters,
        Millimeters,
        Kilogrammes,
        Grammes,
        Inches,
        Pounds,
        Ounces
    }

    enum RackUnit {
        oneU,
        twoU,
        threeU,
        fourU
    }

    ResourceUnitOfMeasure dimUom = ResourceUnitOfMeasure.Millimeters
    float depth
    float width
    float height
    RackUnit uSize

    ResourceUnitOfMeasure weightUom = ResourceUnitOfMeasure.Kilogrammes
    float weight

    static constraints = {
        dimUom nullable:false
        weightUom nullable:false
        depth ()
        width ()
        height()
        weight ()
        uSize nullable:true
    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }
}
