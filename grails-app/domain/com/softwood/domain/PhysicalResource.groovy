package com.softwood.domain

abstract class PhysicalResource extends Resource {

    static enum ResourceUnitOfMeasure {
        Meters,
        Centimeters,
        Millimeters,
        Kilogrammes,
        Grammes,
        Inches,
        Pounds,
        Ounces
    }

    static enum RackUnit {
        notApplicable,
        oneU,
        twoU,
        threeU,
        fourU
    }

    String dimensions // in case you need to build manually with product link

    static constraints = {
        dimensions nullable:true
   }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }
}
