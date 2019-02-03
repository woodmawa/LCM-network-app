package com.softwood.controller.ui

import com.softwood.domain.MaintenanceAgreement
import com.softwood.domain.MaintenanceAgreementService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MaintenanceAgreementController {

    MaintenanceAgreementService maintenanceAgreementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    //test rendfering of the date Time
    def ldt (Long id) {
        respond maintenanceAgreementService.get(id)  // put  this to view
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond maintenanceAgreementService.list(params), model:[maintenanceAgreementCount: maintenanceAgreementService.count()]
    }

    def show(Long id) {
        respond maintenanceAgreementService.get(id)
    }

    def create() {
        respond new MaintenanceAgreement(params)
    }

    def save(MaintenanceAgreement maintenanceAgreement) {
        if (maintenanceAgreement == null) {
            notFound()
            return
        }

        try {
            maintenanceAgreementService.save(maintenanceAgreement)
        } catch (ValidationException e) {
            respond maintenanceAgreement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'maintenanceAgreement.label', default: 'MaintenanceAgreement'), maintenanceAgreement.id])
                redirect maintenanceAgreement
            }
            '*' { respond maintenanceAgreement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond maintenanceAgreementService.get(id)
    }

    def update(MaintenanceAgreement maintenanceAgreement) {
        if (maintenanceAgreement == null) {
            notFound()
            return
        }

        try {
            maintenanceAgreementService.save(maintenanceAgreement)
        } catch (ValidationException e) {
            respond maintenanceAgreement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'maintenanceAgreement.label', default: 'MaintenanceAgreement'), maintenanceAgreement.id])
                redirect maintenanceAgreement
            }
            '*'{ respond maintenanceAgreement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        maintenanceAgreementService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'maintenanceAgreement.label', default: 'MaintenanceAgreement'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'maintenanceAgreement.label', default: 'MaintenanceAgreement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
