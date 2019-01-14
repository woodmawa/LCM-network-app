package com.softwood.domain

import java.time.LocalDateTime

abstract class Agreement {

    String contractReference
    String status
    LocalDateTime contractSignedDate
    LocalDateTime lastUpdatedDate

    static constraints = {
        contractReference nullable:false
        status nullable:false
        contractSignedDate nullable:true
    }
}
