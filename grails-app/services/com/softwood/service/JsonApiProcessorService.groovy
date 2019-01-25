package com.softwood.service

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.Site
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class JsonApiProcessorService {

    def processBody(String jsonBody, Class<?> resource, Map params) {

        def instance = resource.newInstance()

        //String strippedBackJson = jsonBody.replaceAll("\\s+","")
        JsonSlurper slurper = new JsonSlurper()
        def body = slurper.parseText (jsonBody)
        def data = body.data
        String bodyDataType = body.data.type

        //json views  api seems to show the class starting in lower case - change to uppercase as normal for a class
        String dataType = convertFirstCharToUppercase (bodyDataType)
        Map attributes = body.data.attributes
        Map relationships = body.data.relationships

        //wrap in a try block
        //def jsonClassRef = Class.forName(toToBindString) - https://stackoverflow.com/questions/13215403/finding-a-class-reflectively-by-its-simple-name-alone
        //assume users know what they are doing ! - just sanity check simple name
        String resClassSimpleName = resource.getSimpleName()
        assert dataType == resClassSimpleName



        //needs a custom bindData
        //TODO build and return an expando commandObject proxy - shared validations
        //bindData instance, attributes //getObjectToBind()
        //process any relationships and bind these
        relationships.each {tag, value ->
            def dataArray = value.data
            for (item in dataArray) {
                def jsonType = item['type']
                //convert first char to uppercase
                String type = convertFirstCharToUppercase (jsonType)
                def id = Long.parseLong (item['id'])

                //with type and id try and find in existing domain model
                def refEntity
                Class<?> domainClass = domainClassLookupByName (type)
                if (domainClass) {
                    refEntity = domainClass.get (id)
                }
                //help cant overwrite foreign key - clone the mag?
                if (refEntity) {
                    def prop = convertFirstCharToUppercase(tag)
                    instance."addTo$prop" (refEntity)
                    //if (refEntity.validate())
                    //refEntity.save (failOnError:true)
                }
                instance
            }
        }
    }

    //pretty dumb to start
    protected static domainClassLookupByName (String name) {
        // do something cleverer later : https://stackoverflow.com/questions/1308961/how-do-i-get-a-list-of-packages-and-or-classes-on-the-classpath
        Map lookups = [MaintenanceAgreement: MaintenanceAgreement, Site: Site, NetworkDomain: NetworkDomain]

        lookups["$name"]
    }

    protected convertFirstCharToUppercase (String str) {
        if (str == null)
            return str
        else
            str.substring(0, 1).toUpperCase() + str.substring(1)
    }

}
