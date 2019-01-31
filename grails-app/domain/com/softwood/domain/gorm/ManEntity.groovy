package com.softwood.domain.gorm

import com.softwood.domain.RootEntity

import java.time.LocalDateTime

class ManEntity extends Entity {

    String manHostName

    static constraints = {
        manHostName nullable:true
   }
}
