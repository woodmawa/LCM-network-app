package com.softwood.controller

import com.softwood.domain.OrgRoleInstance
import com.softwood.service.JsonApiProcessorService
import grails.artefact.Artefact
import grails.gorm.transactions.ReadOnly
import grails.rest.RestfulController
import org.apache.catalina.connector.RequestFacade
import org.springframework.http.HttpRequest
import org.springframework.web.util.ContentCachingRequestWrapper

/**
 * extending base class to process JsonApi body content messages
 *
 * request body can only be read once
 *
 *
 * @param <T>  provided by extending controller subclasses
 */
@Artefact("Controller")
@ReadOnly
class JsonApiRestfulController<T> extends RestfulController<T> {


    ContentCachingRequestWrapper cachedRequest

    JsonApiRestfulController(Class<T> resource) {

        super (resource, false)
    }


     //inject service
    JsonApiProcessorService jsonApiProcessorService

    //provide version that does a eager fetch on sites
    @Override
    protected List<T> listAllResources (Map params) {
        //params.putAll([sort: 'id', fetch : [sites:'join']])
        resource.list (params )
    }


    /**
     *  TODO Needs some work if we want to ovveride this, if the body has json api content
     */
    @Override
    protected getObjectToBind () {
        //RequestFacade requestFacade = this.request
        cachedRequest = new ContentCachingRequestWrapper(request)
        BufferedReader bodyReader = cachedRequest.getReader()
        def bodyLength = cachedRequest.getContentLengthLong()
        Map params = cachedRequest.getParameterMap()

        //DataBindingSource source =new DataBindingSource()


        String jsonBody = bodyReader.text

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
    protected  T createResource() {
        def instance = resource.newInstance()
        RequestFacade requestFacade = getObjectToBind()
        long bodyLength = cachedRequest.getContentLengthLong()
        Map params = request.getParameterMap()

        //already read stream in getObjectToBind() - used cached copy
        String jsonBody = new String (cachedRequest.getContentAsByteArray())

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
    protected  T createResource(Map params) {
        def instance = resource.newInstance(params)
        instance
    }

    @Override
    protected  T updateResource (T resource) {

    }


}
