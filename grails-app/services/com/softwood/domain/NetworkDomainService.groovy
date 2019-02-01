package com.softwood.domain

import grails.gorm.services.Service

@Service(NetworkDomain)
interface NetworkDomainService {

    NetworkDomain get(Serializable id)

    List<NetworkDomain> list(Map args)

    Long count()

    void delete(Serializable id)

    NetworkDomain save(NetworkDomain networkDomain)

}