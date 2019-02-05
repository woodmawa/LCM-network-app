package com.softwood.controller.ui

import com.softwood.domain.CustomerFacingService
import com.softwood.domain.CustomerFacingServiceService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CustomerFacingServiceController {

    CustomerFacingServiceService customerFacingServiceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond customerFacingServiceService.list(params), model:[customerFacingServiceCount: customerFacingServiceService.count()]
    }

    def show(Long id) {
        respond customerFacingServiceService.get(id)
    }

    def create() {
        respond new CustomerFacingService(params)
    }

    def save(CustomerFacingService customerFacingService) {
        if (customerFacingService == null) {
            notFound()
            return
        }

        try {
            customerFacingServiceService.save(customerFacingService)
        } catch (ValidationException e) {
            respond customerFacingService.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'customerFacingService.label', default: 'CustomerFacingService'), customerFacingService.id])
                redirect customerFacingService
            }
            '*' { respond customerFacingService, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond customerFacingServiceService.get(id)
    }

    def update(CustomerFacingService customerFacingService) {
        if (customerFacingService == null) {
            notFound()
            return
        }

        try {
            customerFacingServiceService.save(customerFacingService)
        } catch (ValidationException e) {
            respond customerFacingService.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'customerFacingService.label', default: 'CustomerFacingService'), customerFacingService.id])
                redirect customerFacingService
            }
            '*'{ respond customerFacingService, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        customerFacingServiceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'customerFacingService.label', default: 'CustomerFacingService'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerFacingService.label', default: 'CustomerFacingService'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
