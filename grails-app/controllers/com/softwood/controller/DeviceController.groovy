package com.softwood.controller

import com.softwood.domain.Device
import com.softwood.domain.Site
import grails.rest.RestfulController
import org.springframework.web.util.ContentCachingRequestWrapper

//@Transactional(readOnly = true)
class DeviceController extends JsonApiRestfulController<Device> {
    static responseFormats = ['json', 'xml']

    DeviceController() {
        super (Device)
    }


    /**
     * bacause devices is declared as a url mapping sub mapping for sites, we need to filter for devices  based on any
     * parent uri site instance,  to get the subset of devices as child of the parent site
     *
     * @param params
     * @return
     */
    @Override
    protected List listAllResources (Map params) {
        cachedRequest = new ContentCachingRequestWrapper(request)

        def json = cachedRequest.JSON
        String uri = cachedRequest.getRequestURI()

        /*
         * if parent rest uri is present then the params map has <DomainClassName>Id:"x" in the params map
         * find this use as a filter key for /sites where this is child or parent
         */
        Map parentDomainClassIdRefsMap = params.findAll { it.getKey().endsWith ("Id") }
        def parentInstance
        Long parentId
        for (domainClassIdRef in parentDomainClassIdRefsMap.keySet()) {
            parentId = Long.parseLong(params[domainClassIdRef])
            //String domainClassName = domainClassIdRef - "Id" //.substring(0, -2)
            //Class<?> domainClass = jsonApiProcessorService.domainClassLookupByName (domainClassName)
        }

        /*here we are using knowledge that we cant detect the parent property to filter onreadily at run time, that is the the device entity
         * has a property  Site site.  We use this to build the criteria search using this
         * property and checking for match with parentId
         */
        Device[] res = resource.createCriteria ().list (params) {
            if (parentId ) {
                site { idEq parentId }
            }

        }

        res
    }
}
