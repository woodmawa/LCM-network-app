package com.softwood.controller

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.Site
import com.softwood.domain.OrgRoleInstance
import com.softwood.service.JsonApiProcessorService
import grails.databinding.DataBindingSource
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController
import groovy.json.JsonSlurper
import org.apache.catalina.connector.RequestFacade
import org.hibernate.annotations.FetchMode
import org.hibernate.transform.DistinctRootEntityResultTransformer


import java.lang.invoke.MethodHandleImpl

class OrgRoleInstanceController extends RestfulController<OrgRoleInstance> {
    static responseFormats = ['json', 'xml']

    long bodyLength
    String bodyText = ""


    OrgRoleInstanceController() {
        super (OrgRoleInstance)
    }

    //inject service
    JsonApiProcessorService jsonApiProcessorService

    //provide version that does a eager fetch on sites
    @Override
    protected List<OrgRoleInstance> listAllResources (Map params) {
        //params.putAll([sort: 'id', fetch : [sites:'join']])
        resource.list (params )
    }


    /**
     *  TODO Needs some work if we want to ovveride this, if the body has json api content
     */
    @Override
    protected getObjectToBind () {
        RequestFacade requestFacade = this.request
        BufferedReader bodyReader = requestFacade.request.getReader()
        bodyLength = requestFacade.request.getContentLengthLong()
        Map params = request.getParameterMap()

        //DataBindingSource source =new DataBindingSource()


        String jsonBody = bodyReader.text
        bodyText = jsonBody

        request  //default result

    }

    /**
     * Creates a new instance of the resource.  If the request
     * contains a body the body will be parsed and used to
     * initialize the new instance using the jsonApiProcessorService , otherwise request parameters
     * will be used to initialized the new instance.
     *
     * @return The resource instance
     */

    @Override
    protected <T extends OrgRoleInstance> T createResource() {
        def instance = resource.newInstance()
        RequestFacade requestFacade = getObjectToBind()
        BufferedReader bodyReader = requestFacade.request.getReader()
        long bodyLength = requestFacade.request.getContentLengthLong()
        Map params = request.getParameterMap()

        String jsonBody = bodyReader.text

        def processed = jsonApiProcessorService.processBody (jsonBody, resource, params)

        bindData instance, processed //save() method will do the instance.validate() call
        instance
    }

    @Override
    /**
     * creates a new instance based on given parameters only
     * @param params map from request
     * @return the resource instance
     */
    protected <T extends OrgRoleInstance> T createResource(Map params) {
        def instance = resource.newInstance(params)
        instance
    }

    @Override
    protected <T extends OrgRoleInstance> T updateResource (T resource) {

    }

}
