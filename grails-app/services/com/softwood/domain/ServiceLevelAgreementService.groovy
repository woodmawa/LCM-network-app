package com.softwood.domain

import grails.gorm.services.Service

@Service(ServiceLevelAgreement)
interface ServiceLevelAgreementService {

    ServiceLevelAgreement get(Serializable id)

    List<ServiceLevelAgreement> list(Map args)

    Long count()

    void delete(Serializable id)

    ServiceLevelAgreement save(ServiceLevelAgreement serviceLevelAgreement)

}