package com.softwood.domain

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class InterfaceSpec extends Specification implements DomainUnitTest<Interface> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
