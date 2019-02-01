package com.softwood.domain

import grails.gorm.services.Service

@Service(Equipment)
interface EquipmentService {

    Equipment get(Serializable id)

    List<Equipment> list(Map args)

    Long count()

    void delete(Serializable id)

    Equipment save(Equipment equipment)

}