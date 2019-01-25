package com.softwood.domain

import java.time.LocalDate
import java.time.LocalDateTime

class EndOfLife {

    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    LocalDate endOfLifeAnnouncement
    LocalDate endOfSaleDate
    LocalDate endOfNewServiceDate
    LocalDate endOfContractRenewal
    LocalDate lastDateOfSupport
    Product product


    static constraints = {
        endOfLifeAnnouncement   nullable:true
        endOfSaleDate           nullable:true
        endOfNewServiceDate     nullable:true
        endOfContractRenewal    nullable:true
        lastDateOfSupport       nullable:true
        product                 nullable:false

    }
}
