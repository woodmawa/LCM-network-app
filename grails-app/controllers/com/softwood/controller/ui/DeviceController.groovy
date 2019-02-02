package com.softwood.controller.ui

import com.softwood.domain.Device
import com.softwood.domain.DeviceService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DeviceController {

    DeviceService deviceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond deviceService.list(params), model:[deviceCount: deviceService.count()]
    }

    def show(Long id) {
        respond deviceService.get(id)
    }

    def create() {
        respond new Device(params)
    }

    def save(Device device) {
        if (device == null) {
            notFound()
            return
        }

        try {
            deviceService.save(device)
        } catch (ValidationException e) {
            respond device.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'device.label', default: 'Device'), device.id])
                redirect device
            }
            '*' { respond device, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond deviceService.get(id)
    }

    def update(Device device) {
        if (device == null) {
            notFound()
            return
        }

        try {
            deviceService.save(device)
        } catch (ValidationException e) {
            respond device.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'device.label', default: 'Device'), device.id])
                redirect device
            }
            '*'{ respond device, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        deviceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'device.label', default: 'Device'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'device.label', default: 'Device'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
