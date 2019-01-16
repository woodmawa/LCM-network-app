package com.softwood.domain

import java.time.LocalDateTime

/**
 * assumes values are encodes into string forms
 */
class FlexAttribute {

    enum AttributeType {
        Single,
        Map,
        List
    }

    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    String attributeGroup = "<default>"
    AttributeType type   //set  as required

    String name
    String value
    Map<String, Object> mapValue = [:]
    List<String> listValue = []

    static belongsTo = [device: Device]

    static constraints = {
        name nullable:false
        value nullable:true
        mapValue nullable:true
        listValue nullable:true
    }

    def beforeInsert() {
        if (dateCreated == null) {
            println "flexAttribute injected dateCreated "
            dateCreated = LocalDateTime.now()
        }
        if (lastUpdated == null) {
            println "flexAttribute injected lastUpdated "
            lastUpdated = LocalDateTime.now()
        }
    }
}
