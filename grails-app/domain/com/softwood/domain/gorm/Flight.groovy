package com.softwood.domain.gorm

class Flight<A extends Airport> {

    String flightCode = "unassigned"

    A departureAirport
    A arrivalAirport

    //setup belongs to
    static belongsTo = [departureAirport:Airport, arrivalAirport:Airport]

    static constraints = {
    }
}
