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
import org.springframework.web.util.ContentCachingRequestWrapper

import java.lang.invoke.MethodHandleImpl


/**
 * Extend JsonApiRestfulController to process posts/patch etc with jsonApi body content
 *
 *
 */

class OrgRoleInstanceController extends JsonApiRestfulController<OrgRoleInstance> {
    static responseFormats = ['json', 'xml']

    OrgRoleInstanceController() {
        super (OrgRoleInstance)
    }



}
