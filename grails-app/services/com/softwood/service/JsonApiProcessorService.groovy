package com.softwood.service

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.Site
import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.transactions.Transactional
import grails.web.databinding.DataBinder
import grails.web.databinding.DataBindingUtils
import groovy.json.JsonSlurper
import org.springframework.validation.BindingResult


/**
 * this service imeplements DataBinder trait from grails.web
 * to get bindData methods - or use BindDataUtils
 *
 *
 */

@Transactional
class JsonApiProcessorService implements DataBinder{

    //get injected one
    static GrailsApplication grailsApplication

    /**
     * expects to see jsonBody text from a post or patch etc
     * @param jsonBody in josnApi format
     * @param resource
     * @param params
     * @return
     */
    def processBody(String jsonBody, Class<?> resource, Map params) {

        def instance = resource.newInstance(), target

        JsonSlurper slurper = new JsonSlurper()  //String strippedBackJson = jsonBody.replaceAll("\\s+","") - slurper will handle that
        def body = slurper.parseText (jsonBody)
        def data = body.data

        if (data == null) {
            log.debug ("invalid json body to be parsed, must contain a  tope level 'data: {...}' tag")
            return null
        }

        String bodyDataType = body.data.type

        //json views  api seems to show the class starting in lower case - change to uppercase as normal for a class
        //json api is not clear about expression for type - appears to lean towards the resource name rather than
        //an actual type in the jvm.
        //String dataType = convertFirstCharToUppercase (bodyDataType)
        Map attributes = body.data.'attributes'
        Map relationships = body.data.'relationships'
        List included = body.'included'

        //wrap in a try block
        //def jsonClassRef = Class.forName(toToBindString) - https://stackoverflow.com/questions/13215403/finding-a-class-reflectively-by-its-simple-name-alone
        //assume users know what they are doing ! - just sanity check simple name
        String resClassSimpleName = resource.getSimpleName()

        // bind the simple attributes first
        bindData(instance, attributes)
        target = instance

        /*  - alternative is to BindingDataUtils rather than impelement web trait
         *  ssee https://hprog99.wordpress.com/2014/09/02/how-to-bind-properties-onto-a-grails-domain-object/
         */
        /**
        * Binds the given source object to the given target object performing type conversion if necessary
        *
         * @param object The object to bind to
         * @param source The source object
         * @param include The list of properties to include
         * @param exclude The list of properties to exclud
         * @param filter The prefix to filter by
         *
         * @return A BindingResult or null if it wasn't successful
         *
        BindingResult res = DataBindingUtils.bindObjectToInstance(instance, attributes)

        other option - direct bind to instance props maps
               instance.properties = attributes
         */

        //needs a custom bindData
        //TODO build and return an expando commandObject proxy - shared validations

        //now process any relationships and bind these
        relationships.each {tag, value ->
            def dataArray = value.'data'
            for (item in dataArray) {
                def jsonType = item['type']
                //convert first char to uppercase
                def logicalPropertyName = jsonType
                Class<?> refDomainClass = domainClassLookupByName (logicalPropertyName)
                def id = Long.parseLong (item['id'] ?: "0")

                //with type and id try and find in existing domain model
                def refEntities = []
                if (refDomainClass) {
                    refEntities  = findMatchingIncludeDetail (refDomainClass, included, logicalPropertyName, id)
                }
                //help cant overwrite foreign key - clone the mag?
                for (refEntity in refEntities) {
                    MetaProperty mprop = target?.metaClass.getMetaProperty("$tag")
                    if (mprop?.type.isAssignableFrom(Collection)) {
                        //if collection type in domain class instance - invoke addTo method
                        if (instance["$tag"] == null)
                            instance["$tag"] = []
                        def prop = convertFirstCharToUppercase(tag)
                        target."addTo$prop"(refEntity)
                    } else {
                        //else set entity into single ref association
                        instance["tag"] = refEntity
                    }
                }
                target
            }
        }
        instance.validate()
        if (instance.hasErrors()) {
            log.debug "jsonApiProcessorService: generated domain object has errors"
            println "jsonApiProcessorService: generated domain object has errors"
        }
        instance
    }

    /**
     * using type name returned from jsonApi type: {} treat this as match for the
     * Artefact.logicalPropertyName or Artefact.fullName
     * *
     * @param domainClazzName (either full canonical format or lower case propertyName format
     * @return
     */
    public static domainClassLookupByName (String domainClazzName) {


        assert grailsApplication

        boolean fullClassNameFormat = false

        String[] parts = domainClazzName.split (".")
        if (parts.size() > 1)
            fullClassNameFormat = true
        else {
            //ensure class is in logicalproperty expected format
            domainClazzName = domainClazzName[0].toLowerCase() + domainClazzName.substring(1)
        }

        GrailsClass[] matchedDomainClasses = [], allDomainClasses = []
        allDomainClasses = grailsApplication.getArtefacts ("Domain")

        matchedDomainClasses = allDomainClasses?.findAll {
            if (fullClassNameFormat)
                it.fullName == domainClazzName
            else
                it.logicalPropertyName == domainClazzName
        }

        //todo Assumes only one match - need to decide what to do with multiple matches, at mo returns null
        if (matchedDomainClasses?.size() == 1)
            return matchedDomainClasses[0].getClazz()
        else
            null

        // do something cleverer later : https://stackoverflow.com/questions/1308961/how-do-i-get-a-list-of-packages-and-or-classes-on-the-classpath
        //Map lookups = [MaintenanceAgreement: MaintenanceAgreement, Site: Site, NetworkDomain: NetworkDomain]

        //lookups["$name"]
    }

    protected convertFirstCharToUppercase (String str) {
        if (str == null)
            return str
        else
            str.substring(0, 1).toUpperCase() + str.substring(1)
    }

    /**
     * from the json api includes list of entries find the entry that matches on the type name /id
     * attempts to return a valid instance of domain class referenced by logicalPropertyName
     *
     * @param included - Map of included records for json item
     * @param logicalPropertyName - lower case class type name from 'type : <string>' entry
     * @param id - id of record if exists or 0L if not
     * @return matchedInstance - array of matched dataBound instances of domain class relationship entries  class
     */
    protected def findMatchingIncludeDetail (Class<?> refDomainClass, List included, String logicalPropertyName, Long id) {

        def refInstance
        def matchedInstances = []

        def includedEntryAttributes = []
        def includedEntryRelationships = [:]
        def entries = included.findAll {
            it
            it?.'type' == logicalPropertyName && Long.parseLong(it?.'id' ?: "0") == id
        }
        for (entry in  entries) {
            includedEntryAttributes = entries[0].'attributes'
            includedEntryRelationships = entries[0].'relationships'

            if (id == 0)
                refInstance = refDomainClass?.newInstance()
            else
                refInstance = refDomainClass.get (id) //do lookup via db

            //bind attributes from jsonAPi
            if (refInstance)
                bindData (refInstance, includedEntryAttributes)

            matchedInstances << refInstance

        }

        //todo need to figure out what to do with relationships - some recursion
        matchedInstances
    }
}
