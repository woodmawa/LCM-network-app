package com.softwood.domain

import java.time.LocalDateTime

/**
 * abstract parent for types of contracts and agreements
 *
 */
abstract class Agreement {

    String contractReference
    String status
    LocalDateTime contractSignedDate
    LocalDateTime lastUpdated   //updated by framework
    LocalDateTime dateCreated   //updated by framework

    static constraints = {
        contractReference nullable:false
        status nullable:false
        contractSignedDate nullable:true
    }


}
