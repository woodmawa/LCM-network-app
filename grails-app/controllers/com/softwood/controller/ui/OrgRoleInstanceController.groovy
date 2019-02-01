package com.softwood.controller.ui

import com.softwood.domain.OrgRoleInstance
import com.softwood.domain.OrgRoleInstanceService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class OrgRoleInstanceController {

    OrgRoleInstanceService orgRoleInstanceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond orgRoleInstanceService.list(params), model:[orgRoleInstanceCount: orgRoleInstanceService.count()]
    }

    def show(Long id) {
        respond orgRoleInstanceService.get(id)
    }

    def create() {
        respond new OrgRoleInstance(params)
    }

    def save(OrgRoleInstance orgRoleInstance) {
        if (orgRoleInstance == null) {
            notFound()
            return
        }

        try {
            orgRoleInstanceService.save(orgRoleInstance)
        } catch (ValidationException e) {
            respond orgRoleInstance.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'orgRoleInstance.label', default: 'OrgRoleInstance'), orgRoleInstance.id])
                redirect orgRoleInstance
            }
            '*' { respond orgRoleInstance, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond orgRoleInstanceService.get(id)
    }

    def update(OrgRoleInstance orgRoleInstance) {
        if (orgRoleInstance == null) {
            notFound()
            return
        }

        try {
            orgRoleInstanceService.save(orgRoleInstance)
        } catch (ValidationException e) {
            respond orgRoleInstance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'orgRoleInstance.label', default: 'OrgRoleInstance'), orgRoleInstance.id])
                redirect orgRoleInstance
            }
            '*'{ respond orgRoleInstance, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        orgRoleInstanceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'orgRoleInstance.label', default: 'OrgRoleInstance'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'orgRoleInstance.label', default: 'OrgRoleInstance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
