package com.softwood.controller

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.NetworkDomain
import com.softwood.domain.Site
import com.softwood.domain.OrgRoleInstance
import com.softwood.service.JsonApiProcessorService
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

    //inject service
    JsonApiProcessorService jsonApiProcessorService

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
        def instance
        RequestFacade requestFacade = getObjectToBind()
        BufferedReader bodyReader = requestFacade.request.getReader()
        long bodyLength = requestFacade.request.getContentLengthLong()
        Map params = request.getParameterMap()

        String jsonBody = bodyReader.text

        def request = getObjectToBind()

        def processed = jsonApiProcessorService.processBody (jsonBody, resource, params)

        bindData instance, processed //getObjectToBind()
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


}
