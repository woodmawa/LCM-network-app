package com.softwood.controller.ui

import com.softwood.domain.NetworkDomain
import com.softwood.service.NetworkDomainService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class NetworkDomainController {

    NetworkDomainService networkDomainService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond networkDomainService.list(params), model:[networkDomainCount: networkDomainService.count()]
    }

    def show(Long id) {
        respond networkDomainService.get(id)
    }

    def create() {
        respond new NetworkDomain(params)
    }

    def save(NetworkDomain networkDomain) {
        if (networkDomain == null) {
            notFound()
            return
        }

        try {
            networkDomainService.save(networkDomain)
        } catch (ValidationException e) {
            respond networkDomain.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'networkDomain.label', default: 'NetworkDomain'), networkDomain.id])
                redirect networkDomain
            }
            '*' { respond networkDomain, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond networkDomainService.get(id)
    }

    def update(NetworkDomain networkDomain) {
        if (networkDomain == null) {
            notFound()
            return
        }

        try {
            networkDomainService.save(networkDomain)
        } catch (ValidationException e) {
            respond networkDomain.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'networkDomain.label', default: 'NetworkDomain'), networkDomain.id])
                redirect networkDomain
            }
            '*'{ respond networkDomain, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        networkDomainService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'networkDomain.label', default: 'NetworkDomain'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'networkDomain.label', default: 'NetworkDomain'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
