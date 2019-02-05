package com.softwood.taglib

import grails.util.GrailsNameUtils
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JavaDateTimeTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static encodeAsForTags = [testCall: [taglib:'none'], jdtRenderField:'none', jdtScaffoldField:'none']

    static namespace = "jdt"        //java8 date time name space for tags

    GrailsNameUtils grailsNameUtils = new GrailsNameUtils()


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

    //put input date picker form
    def jdtRenderField = {attrs, body ->

        String dpId = "datepicker"
        String page = """
<div class="container">
    <h1>taglib Bootstrap datepicker</h1>
    <div class="input-group date" id="${dpId}">
        <input type="text" class="form-control" value="${java.time.LocalDateTime.now().toString()}">
        <span class="input-group-addon">
            <i class="glyphicon glyphicon-calendar"></i>
        </span>
    </div>
</div>

"""

        out << page
    }

    def jdtScaffoldField = {attrs, body ->

        assert grailsNameUtils

        String propertyName = attrs.propertyName ?: "unknown"
        String property = attrs.property ?: "unknown"
        String value = attrs.value
        String naturalName = grailsNameUtils.getNaturalName(propertyName)
        String label = attrs.label ?: 'unknown'

        String page = """
<g:set var="localDateTimePattern" value="${message(code: 'default.localDateTime.format',default: 'yyyy-MM-dd HH:mm')}"/>
<div class="fieldcontain" >
    <div class="container">
        <div class="row">
            <div class='col-sm-6'>
                <div class="form-group form-inline">
                    <label class='control-label' for="$propertyName"> ${label} </label>
                    <div class="input-group date"  >
                            <input type='text' class="form-control" value="${value?.toString()}"/>
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
"""

        out << page
    }


}
