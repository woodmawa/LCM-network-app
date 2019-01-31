package com.softwood.domain.gorm

abstract class Airport{

    String name
    Collection<? extends Flight> outboundFlights = []
    Collection<? extends Flight>  inboundFlights = []

    static hasMany = [outboundFlights:Flight, inboundFlights:Flight]

    static mappedBy = [outboundFlights: 'departureAirport', inboundFlights: 'arrivalAirport']

    static constraints = {
        outboundFlights nullable:true
        inboundFlights nullable:true
    }

    static mapping = {
        tablePerHierarchy false  //multiple tables+joins
    }
}
