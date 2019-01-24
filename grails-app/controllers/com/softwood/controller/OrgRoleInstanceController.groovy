package com.softwood.controller

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.Site
import com.softwood.domain.OrgRoleInstance
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController
import groovy.json.JsonSlurper
import org.apache.catalina.connector.RequestFacade
import org.hibernate.annotations.FetchMode
import org.hibernate.transform.DistinctRootEntityResultTransformer


import java.lang.invoke.MethodHandleImpl

class OrgRoleInstanceController extends RestfulController<OrgRoleInstance> {
    static responseFormats = ['json', 'xml']

    OrgRoleInstanceController() {
        super (OrgRoleInstance)
    }

    //provide version that does a eager fetch on sites
    @Override
    protected List<OrgRoleInstance> listAllResources (Map params) {
        //params.putAll([sort: 'id', fetch : [sites:'join']])
        resource.list (params )
    }

    /**
     * Creates a new instance of the resource.  If the request
     * contains a body the body will be parsed and used to
     * initialize the new instance, otherwise request parameters
     * will be used to initialized the new instance.
     *
     * @return The resource instance
     */

    //works - needs lots of improvement !! overwites, lookups etc
    @Override
    protected <T extends OrgRoleInstance> T createResource() {
        def instance = super.resource.newInstance()
        RequestFacade requestFacade = getObjectToBind()
        BufferedReader bodyReader = requestFacade.request.getReader()
        long bodyLength = requestFacade.request.getContentLengthLong()

        String jsonBody = bodyReader.text
        //String strippedBackJson = jsonBody.replaceAll("\\s+","")
        JsonSlurper slurper = new JsonSlurper()
        def body = slurper.parseText (jsonBody)
        def data = body.data
        String bodyDataType = body.data.type
        //json views  api seems to show the class starting in lower case - change to uppercase
        String dataType = convertFirstCharToUppercase (bodyDataType)
        Map attributes = body.data.attributes
        Map relationships = body.data.relationships

        //wrap in a try block
        //def jsonClassRef = Class.forName(toToBindString) - https://stackoverflow.com/questions/13215403/finding-a-class-reflectively-by-its-simple-name-alone
        //assume users know what they are doing ! - just sanity check simple name
        String resClassSimpleName = resource.getSimpleName()
        assert dataType == resClassSimpleName



        //needs a custom bindData
        bindData instance, attributes //getObjectToBind()
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

        //bindData instance, relationships //getObjectToBind()
        instance
    }

    @Override
    /**
    * Creates a new instance of the resource for the given parameters
    *
    * @param params The parameters
    * @return The resource instance
    */
    protected <T extends OrgRoleInstance> T createResource(Map params) {
        def instance = resource.newInstance(params)
        instance
    }

    //pretyy dumb to start
    protected static domainClassLookupByName (String name) {
        // do something cleverer later : https://stackoverflow.com/questions/1308961/how-do-i-get-a-list-of-packages-and-or-classes-on-the-classpath
        Map lookups = [MaintenanceAgreement : MaintenanceAgreement, Site: Site, NetworkDomain: NetworkDomain]

        lookups["$name"]
    }

    protected convertFirstCharToUppercase (String str) {
        if (str == null)
            return str
        else
            str.substring(0, 1).toUpperCase() + str.substring(1)
    }

}
