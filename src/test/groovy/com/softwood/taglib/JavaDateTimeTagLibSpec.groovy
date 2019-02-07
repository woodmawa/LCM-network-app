package com.softwood.taglib

import com.softwood.taglib.JavaDateTimeTagLib

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

import java.time.LocalDateTime

class JavaDateTimeTagLibSpec extends Specification implements TagLibUnitTest<JavaDateTimeTagLib> {

    def setup() {
    }

    def cleanup() {
    }

    /**
     * restriction params must be quoted values - esentially strings
     * taglib has to take that and do any conversions in the taglib
     * output by defaults is encoded html using std codec
     */
    void "call displayDateTime tag "() {
        given:

        LocalDateTime ldt  = LocalDateTime.now()
        //JavaDateTimeTagLib tag = new JavaDateTimeTagLib()
        //String result = tag.testCall(new HashMap(), null) //= applyTemp - format="dd-MM-yyyy HH:mm"
        //def result = applyTemplate('<jdt:displayDateTime ldt="$now"  />', [now:LocalDateTime.now()])
        //hah have to wrap the vra in braces to ge the actual value passed to the taglib
        String result = applyTemplate('<jdt:testCall p1="p1-string" p2="${now}"  />' , [now:LocalDateTime.now()])

        when :
        println "$result "

        then:
        result
    }
}
