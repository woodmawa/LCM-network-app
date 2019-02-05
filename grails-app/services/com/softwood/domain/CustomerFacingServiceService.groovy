package com.softwood.domain

import grails.gorm.services.Service

@Service(CustomerFacingService)
interface CustomerFacingServiceService {

    CustomerFacingService get(Serializable id)

    List<CustomerFacingService> list(Map args)

    Long count()

    void delete(Serializable id)

    CustomerFacingService save(CustomerFacingService customerFacingService)

}