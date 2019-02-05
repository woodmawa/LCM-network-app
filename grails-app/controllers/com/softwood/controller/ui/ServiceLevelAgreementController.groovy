package com.softwood.controller.ui

import com.softwood.domain.ServiceLevelAgreement
import com.softwood.domain.ServiceLevelAgreementService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ServiceLevelAgreementController {

    ServiceLevelAgreementService serviceLevelAgreementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond serviceLevelAgreementService.list(params), model:[serviceLevelAgreementCount: serviceLevelAgreementService.count()]
    }

    def show(Long id) {
        respond serviceLevelAgreementService.get(id)
    }

    def create() {
        respond new ServiceLevelAgreement(params)
    }

    def save(ServiceLevelAgreement serviceLevelAgreement) {
        if (serviceLevelAgreement == null) {
            notFound()
            return
        }

        try {
            serviceLevelAgreementService.save(serviceLevelAgreement)
        } catch (ValidationException e) {
            respond serviceLevelAgreement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'serviceLevelAgreement.label', default: 'ServiceLevelAgreement'), serviceLevelAgreement.id])
                redirect serviceLevelAgreement
            }
            '*' { respond serviceLevelAgreement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond serviceLevelAgreementService.get(id)
    }

    def update(ServiceLevelAgreement serviceLevelAgreement) {
        if (serviceLevelAgreement == null) {
            notFound()
            return
        }

        try {
            serviceLevelAgreementService.save(serviceLevelAgreement)
        } catch (ValidationException e) {
            respond serviceLevelAgreement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'serviceLevelAgreement.label', default: 'ServiceLevelAgreement'), serviceLevelAgreement.id])
                redirect serviceLevelAgreement
            }
            '*'{ respond serviceLevelAgreement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        serviceLevelAgreementService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'serviceLevelAgreement.label', default: 'ServiceLevelAgreement'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'serviceLevelAgreement.label', default: 'ServiceLevelAgreement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
