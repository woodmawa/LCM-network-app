package com.softwood.controller

import com.softwood.domain.Site
import grails.rest.RestfulController
import org.springframework.web.util.ContentCachingRequestWrapper

/**
 * note:  as sites resource can be a child of parent /api/orgs in the UrlMappings then you need to detect this URI
 * by looking at the params map - and setup the filter for the sites ListAllResources to
 * only find sites that are child of the parent id
 *
 */
class SiteController extends JsonApiRestfulController<Site> {
    static responseFormats = ['json', 'xml']

    SiteController() {
        super (Site)
    }


    /**
     * bacause Site is declared as a url mapping sub mapping we need to filter for sites based on any
     * parent uri instance,  to get the subset of sites as child of the parent
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

        /*here we are using knowledge that we cant detect readily at run time, that the the Site entity
         * has a property  OrgRoleInstance org.  We use this to build the criteria search using this
         * property and checking for match with parentId
         */
        Site[] res = resource.createCriteria ().list (params) {
            if (parentId ) {
                org { idEq parentId }
            }

        }

        res
    }
}
