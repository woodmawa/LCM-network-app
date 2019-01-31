package com.softwood.domain.gorm

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class AirportIntegrationSpec extends Specification {

    def setup() {

        Airport manchester = new UkAirport (name:"Manchester", shortCode:"MAN")
        manchester.save(failOnError:true)

    }

    def cleanup() {
    }

    void "two airports and one flight "() {

        given :

        Airport gatwick = new UkAirport (name:"Gatwick", shortCode:"LGW")
        Airport stansted = new UkAirport (name:"Stansted", shortCode:"STN")
        Airport manchester = UkAirport.get(1L)
        assert manchester.shortCode == "MAN"

        gatwick.save ()
        stansted.save ()

        when:
        Flight flight = new Flight<UkAirport>(flightCode:"F123-LGW-STN")
        gatwick.addToOutboundFlights(flight)
        stansted.addToInboundFlights(flight)
        flight.save (failOnError:true)
        assert gatwick.outboundFlights.size() == 1
        assert gatwick.inboundFlights.size() == 0
        assert stansted.outboundFlights.size() == 0
        assert stansted.inboundFlights.size() == 1


        // -all ok until we get to here  - if you evaluate manchester before you use it - test works
        //if you comment out and use before you evaluate it fails !
        assert manchester.inboundFlights.size() == 0  //comment out makes test fail !
        Flight flight2 = new Flight<UkAirport>(flightCode:"F789-LGW-MAN")
        gatwick.addToOutboundFlights(flight2)
        manchester.addToInboundFlights(flight2)
        flight2.save (failOnError:true)
        assert gatwick.outboundFlights.size() == 2
        assert manchester.inboundFlights.size() == 1



        then :""
        flight.id == 1
        flight2.id == 2
        gatwick.inboundFlights.size() == 0
        gatwick.outboundFlights.size() == 2
        stansted.outboundFlights.size() == 0
        stansted.inboundFlights.size() == 1
        flight.departureAirport.name == "Gatwick"
        flight.arrivalAirport.name == "Stansted"

        flight2.departureAirport.name == "Gatwick"
        flight2.arrivalAirport.name == "Manchester"


    }
}
