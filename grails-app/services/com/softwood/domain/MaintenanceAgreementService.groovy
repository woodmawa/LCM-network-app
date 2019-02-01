package com.softwood.domain

import grails.gorm.services.Service

@Service(MaintenanceAgreement)
interface MaintenanceAgreementService {

    MaintenanceAgreement get(Serializable id)

    List<MaintenanceAgreement> list(Map args)

    Long count()

    void delete(Serializable id)

    MaintenanceAgreement save(MaintenanceAgreement maintenanceAgreement)

}