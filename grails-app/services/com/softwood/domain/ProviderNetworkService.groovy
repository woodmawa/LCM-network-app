package com.softwood.domain

import grails.gorm.services.Service

@Service(ProviderNetwork)
interface ProviderNetworkService {

    ProviderNetwork get(Serializable id)

    List<ProviderNetwork> list(Map args)

    Long count()

    void delete(Serializable id)

    ProviderNetwork save(ProviderNetwork providerNetwork)

}