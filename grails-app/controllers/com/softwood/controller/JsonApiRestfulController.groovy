package com.softwood.controller

import com.softwood.service.JsonApiProcessorService
import grails.artefact.Artefact
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController
import grails.web.http.HttpHeaders
import org.apache.catalina.connector.RequestFacade
import org.springframework.http.HttpRequest
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.http.HttpStatus

import java.util.Map.Entry

import static org.springframework.http.HttpStatus.*

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
    static allowedMethods = [save: "POST", update: ["PUT", "POST"], patch: "PATCH", delete: "DELETE"]

    ContentCachingRequestWrapper cachedRequest

    JsonApiRestfulController(Class<T> resource) {

        super (resource, false)
    }


     //inject grails service here
    JsonApiProcessorService jsonApiProcessorService

    //provide version that does a eager fetch on sites
    @Override
    protected List<T> listAllResources (Map params) {
        cachedRequest = new ContentCachingRequestWrapper(request)

        T[] result = resource.list(params)

        result


        /*
        no easy way tot detect whether this is child rest uri, and if so what the parents
        property that needs to be filtered for in criteria

        pushed this knowledge back to developer who is extending JsonApiRestfulController as
        they know what the filter property name is

        Map domainClassIdRefsMap = params.findAll { it.getKey().endsWith ("Id") }

        def parentInstance
        Long parentId
        for (domainClassIdRef in domainClassIdRefsMap.keySet()) {
            parentId = Long.parseLong(params[domainClassIdRef])
            String domainClassName = domainClassIdRef - "Id"
            Class<?> domainClass = jsonApiProcessorService.domainClassLookupByName (domainClassName)
        }

        T[] res = resource.createCriteria (){
            org { idEq parentId}

        }.list (params)

        res */

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
        Map params = cachedRequest.getParameterMap()

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
    @Transactional
    def update () {

        if (handleReadOnly())
            return

        T instance =  queryForResource (params.id)
        if (instance == null){
            //wont compile
            transactionStatus.setRollbackOnly() //- cant resolve  should work for @Transactional methods
        }

        RequestFacade requestFacade = getObjectToBind()
        long bodyLength = cachedRequest.getContentLengthLong()
        Map params = cachedRequest.getParameterMap()

        //already read stream in getObjectToBind() - used cached copy
        String jsonBody = new String (cachedRequest.getContentAsByteArray())

        def processed = jsonApiProcessorService.processBody (jsonBody, resource, params)

        bindData instance, processed //save() method will do the instance.validate() call

        instance.validate()
        if (instance.hasErrors()) {
            transactionStatus.setRollbackOnly() //- cant resolve, but shows in debugger - must be injected on tx methods
            respond instance.errors, view:'edit'  //status code 422
            return
        }

        updateResource (instance)
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [classMessageArg, instance.id])
                redirect instance
            }
            '*'{
                response.addHeader(HttpHeaders.LOCATION,
                        grailsLinkGenerator.link( resource: this.controllerName, action: 'show',id: instance.id, absolute: true,
                                namespace: hasProperty('namespace') ? this.namespace : null ))
                respond instance, [status: OK]
            }
        }
    }


}
