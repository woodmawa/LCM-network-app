package com.softwood.domain

import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import lcm.network.app.Application
import org.springframework.validation.FieldError
import spock.lang.Specification

import java.time.LocalDateTime

@Integration (applicationClass = Application.class)  //appClass - overcomes problem with gorm not loading when running test
@Rollback
class InterfaceIntegSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create an interface and link to pe "() {

        given: "existing pe  "

        Device pe = Device.get(2)
        assert pe.linkedTo.size() == 0

        when: "add new interface to pe"

        Interface mif = new Interface (name:"eth0", ipAddress: "10.5.25.34", port:"8080", interfaceStatus: Interface.InterfaceState.Up, bandwidth:"10 Gbit/s")
        mif.validate() == true
        if (mif.hasErrors()) {
            mif.errors.fieldErrors.each { FieldError fe -> println "field:"+  fe.field + " rejected : " + fe.rejectedValue}
        }
        pe.addToInterfaces(mif)

        //needs the flush:true to force the write to the db!
        //https://spring.io/blog/2010/07/02/gorm-gotchas-part-2/
        pe.save (flush:true)
        mif

        then:
        pe.interfaces.size() == 1
        pe.interfaces[0].name =="eth0"
        mif.id == 1
        Interface.count() == 1


    }

    void "create an interface with a subinterface  and link to pe "() {

        given: "existing pe  "

        Device pe = Device.get(2)
        assert pe.linkedTo.size() == 0

        when: "add new interface to pe"

        Interface mif = new Interface (name:"eth0", ipAddress: "10.5.25.34", port:"8080", interfaceStatus: Interface.InterfaceState.Up, bandwidth:"10 Gbit/s")
        mif.validate() == true
        if (mif.hasErrors()) {
            mif.errors.fieldErrors.each { FieldError fe -> println "field:"+  fe.field + " rejected : " + fe.rejectedValue}
        }
        pe.addToInterfaces(mif)

        //needs the flush:true to force the write to the db!
        //https://spring.io/blog/2010/07/02/gorm-gotchas-part-2/
        pe.save (flush:true)

        Interface subif = new Interface (name:"eth0/1", ipAddress: "10.5.25.25", port:"80", interfaceStatus: Interface.InterfaceState.Up, bandwidth:"1 Gbit/s")

        mif.addToSubInterfaces(subif)
        mif.save(flush:true)

        then:
        pe.interfaces.size() == 1
        pe.interfaces[0].name =="eth0"
        mif.id == 1
        Interface.count() == 2
        mif.subInterfaces.size() == 1
        subif.id == 2
        subif.parent == mif


    }


}
