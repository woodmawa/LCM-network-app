package com.softwood.domain.gorm

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class AirportIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "two airports and one flight "() {

        given :

        Airport gatwick = new UkAirport (name:"Gatwick", shortCode:"LGW")
        Airport stansted = new UkAirport (name:"Stansted", shortCode:"STN")

        gatwick.save ()
        stansted.save ()

        when:
        Flight flight = new Flight<UkAirport>(flightCode:"F123-LGW-STN")
        gatwick.addToOutboundFlights(flight)
        stansted.addToInboundFlights(flight)
        //gatwick.save(failOnError:true)
        //stansted.save(failOnError:true)
        flight.save (failOnError:true)

        then :""
        flight.id == 1
        gatwick.inboundFlights.size() == 0
        gatwick.outboundFlights.size() == 1
        stansted.outboundFlights.size() == 0
        stansted.inboundFlights.size() == 1
        flight.departureAirport.name == "Gatwick"
        flight.arrivalAirport.name == "Stansted"

    }
}
