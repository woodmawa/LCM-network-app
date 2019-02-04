package com.softwood.taglib

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JavaDateTimeTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static encodeAsForTags = [testCall: [taglib:'none']]

    static namespace = "jdt"        //java8 date time name space for tags

    def testCall = { attrs ->

        def p1 = attrs.p1
        def p2 = attrs.p2
        out << "p1:'$p1' with class ${p1.getClass()}"
        out << "p2:'$p2' with class ${p2.getClass()}"
    }

    /**
     * <jdt:displayDateTime ldt="$domainObject.ldtProperty" format="dd-MM-yyyy HH.mm">
     *
     */
    def displayDateTime = {Map attrs ->

        java.time.format.DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm")
        String ldtStr = attrs.ldt
        LocalDateTime ldt = LocalDateTime.parse (ldtStr)
        //String format = attrs.format ?: "dd-MM-yyyy HH.mm"

        String formattedDateTime = ldt.format (formatter)

        def output =  g.textField (name:"xxx",  value: "$formattedDateTime")
        out << output

    }


}
