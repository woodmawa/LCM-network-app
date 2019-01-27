package com.softwood.controller

import com.softwood.domain.Site
import grails.rest.RestfulController

class SiteController extends JsonApiRestfulController<Site> {
    static responseFormats = ['json', 'xml']

    SiteController() {
        super (Site)
    }

    /**
     * Lists all resources up to the given maximum
     *
     * @param max The maximum
     * @return A list of resources
     */
    /*@Override
    def indexInvestigation(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        List<Site> sites = listAllResources(params)
        println " calling respond for indexInvestigation action with list of sites $sites"
        respond sites, model: [("${resourceName}Count".toString()): countResources()]
    }*/
}
