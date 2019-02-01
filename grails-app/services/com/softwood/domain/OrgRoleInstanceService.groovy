package com.softwood.domain

import grails.gorm.services.Service

@Service(OrgRoleInstance)
interface OrgRoleInstanceService {

    OrgRoleInstance get(Serializable id)

    List<OrgRoleInstance> list(Map args)

    Long count()

    void delete(Serializable id)

    OrgRoleInstance save(OrgRoleInstance orgRoleInstance)

}