package com.softwood.controller.rest


import com.softwood.domain.OrgRoleInstance

/**
 * Extend JsonApiRestfulController to process posts/patch etc with jsonApi body content
 *
 *
 */

class OrgRoleInstanceRestController extends JsonApiRestfulController<OrgRoleInstance> {
    static responseFormats = ['json', 'html', 'xml']

    OrgRoleInstanceRestController() {
        super (OrgRoleInstance)
    }



}
