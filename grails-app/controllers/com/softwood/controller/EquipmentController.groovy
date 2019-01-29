package com.softwood.controller

import com.softwood.domain.Equipment
import com.softwood.domain.Site
import org.springframework.web.util.ContentCachingRequestWrapper

class EquipmentController extends JsonApiRestfulController<Equipment> {
    static responseFormats = ['json', 'xml']

    EquipmentController() {
        super (Equipment)
    }

    /**
     * bacause Equipment is declared as a url mapping sub mapping for product we need to filter for product based on any
     * parent uri instance,  to get the subset of equipment as child of the parent
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
            String domainClassName = domainClassIdRef - "Id" //.substring(0, -2)
            Class<?> domainClass = jsonApiProcessorService.domainClassLookupByName (domainClassName)
        }

        /*here we are using knowledge that we cant detect readily at run time, that the the equipment entity
         * has a property  called product .  We use this to build the criteria search using this
         * property and checking for match with parentId
         */
        Equipment[] res = resource.createCriteria ().list (params) {
            if (parentId ) {
                product { idEq parentId }
            }

        }

        res
    }
}
