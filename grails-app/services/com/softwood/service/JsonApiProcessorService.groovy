package com.softwood.service

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.Site
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
        String dataType = convertFirstCharToUppercase (bodyDataType)
        Map attributes = body.data.attributes
        Map relationships = body.data.relationships

        //wrap in a try block
        //def jsonClassRef = Class.forName(toToBindString) - https://stackoverflow.com/questions/13215403/finding-a-class-reflectively-by-its-simple-name-alone
        //assume users know what they are doing ! - just sanity check simple name
        String resClassSimpleName = resource.getSimpleName()
        assert dataType == resClassSimpleName  //might to review thia assert but ok for now

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
                String type = convertFirstCharToUppercase (jsonType)
                Class<?> domainClass = domainClassLookupByName (type)
                def id = Long.parseLong (item['id'])

                //with type and id try and find in existing domain model
                def refEntity
                if (domainClass) {
                    if (item['id'])
                        refEntity = domainClass.get (id)  //if id present try for lookup by (id)L
                    else {
                        //create new ref'd instance
                        refEntity = domainClass.newInstance()
                        // todo  find in cludes = pul;l data and recurse
                        //now find any details from include tag and build in to new object
                    }
                }
                //help cant overwrite foreign key - clone the mag?
                if (refEntity) {
                    MetaProperty mprop = target?.metaClass.getMetaProperty("$tag")
                    if (mprop?.type.isAssignableFrom(Collection))
                        if (instance["$tag"] == null)
                            instance["$tag"] = []
                    def prop = convertFirstCharToUppercase(tag)
                    target."addTo$prop" (refEntity)
                    //if (refEntity.validate())
                    //refEntity.save (failOnError:true)
                }
                target
            }
        }
        instance.validate()
        if (instance.hasErrors()) {
            println "jsonApiProcessorService: generated domain object has errors"
        }
        instance
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
