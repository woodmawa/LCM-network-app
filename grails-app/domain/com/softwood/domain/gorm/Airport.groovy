package com.softwood.domain.gorm

abstract class Airport<F extends Flight> {

    String name
    Collection<F> outboundFlights = []
    Collection<F> inboundFlights = []

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
