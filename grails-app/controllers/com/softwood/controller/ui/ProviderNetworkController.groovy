package com.softwood.controller.ui

import com.softwood.domain.ProviderNetwork
import com.softwood.domain.ProviderNetworkService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProviderNetworkController {

    ProviderNetworkService providerNetworkService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond providerNetworkService.list(params), model:[providerNetworkCount: providerNetworkService.count()]
    }

    def show(Long id) {
        respond providerNetworkService.get(id)
    }

    def create() {
        respond new ProviderNetwork(params)
    }

    def save(ProviderNetwork providerNetwork) {
        if (providerNetwork == null) {
            notFound()
            return
        }

        try {
            providerNetworkService.save(providerNetwork)
        } catch (ValidationException e) {
            respond providerNetwork.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'providerNetwork.label', default: 'ProviderNetwork'), providerNetwork.id])
                redirect providerNetwork
            }
            '*' { respond providerNetwork, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond providerNetworkService.get(id)
    }

    def update(ProviderNetwork providerNetwork) {
        if (providerNetwork == null) {
            notFound()
            return
        }

        try {
            providerNetworkService.save(providerNetwork)
        } catch (ValidationException e) {
            respond providerNetwork.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'providerNetwork.label', default: 'ProviderNetwork'), providerNetwork.id])
                redirect providerNetwork
            }
            '*'{ respond providerNetwork, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        providerNetworkService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'providerNetwork.label', default: 'ProviderNetwork'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'providerNetwork.label', default: 'ProviderNetwork'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
