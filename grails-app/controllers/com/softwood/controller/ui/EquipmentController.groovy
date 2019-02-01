package com.softwood.controller.ui

import com.softwood.domain.Equipment
import com.softwood.domain.EquipmentService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EquipmentController {

    EquipmentService equipmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond equipmentService.list(params), model:[equipmentCount: equipmentService.count()]
    }

    def show(Long id) {
        respond equipmentService.get(id)
    }

    def create() {
        respond new Equipment(params)
    }

    def save(Equipment equipment) {
        if (equipment == null) {
            notFound()
            return
        }

        try {
            equipmentService.save(equipment)
        } catch (ValidationException e) {
            respond equipment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'equipment.label', default: 'Equipment'), equipment.id])
                redirect equipment
            }
            '*' { respond equipment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond equipmentService.get(id)
    }

    def update(Equipment equipment) {
        if (equipment == null) {
            notFound()
            return
        }

        try {
            equipmentService.save(equipment)
        } catch (ValidationException e) {
            respond equipment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'equipment.label', default: 'Equipment'), equipment.id])
                redirect equipment
            }
            '*'{ respond equipment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        equipmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'equipment.label', default: 'Equipment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipment.label', default: 'Equipment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
