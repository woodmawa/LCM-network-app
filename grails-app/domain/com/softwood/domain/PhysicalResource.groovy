package com.softwood.domain

abstract class PhysicalResource {

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

    ResourceUnitOfMeasure dimUom = ResourceUnitOfMeasure.Millimeters
    float depth
    float width
    float height

    ResourceUnitOfMeasure weightUom = ResourceUnitOfMeasure.Kilogrammes
    float weight

    static constraints = {
        dimUom nullable:false
        weightUom nullable:false
        depth ()
        width ()
        height()
        weight ()
    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }
}
