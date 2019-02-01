package com.softwood.controller

import com.softwood.controller.ui.MaintenanceAgreementController
import com.softwood.domain.MaintenanceAgreement
import com.softwood.service.MaintenanceAgreementService
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class MaintenanceAgreementControllerSpec extends Specification implements ControllerUnitTest<MaintenanceAgreementController>, DomainUnitTest<MaintenanceAgreement> {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.maintenanceAgreementList
        model.maintenanceAgreementCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.maintenanceAgreement!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/maintenanceAgreement/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * save(_ as MaintenanceAgreement)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def maintenanceAgreement = new MaintenanceAgreement(params)
        maintenanceAgreement.id = 1

        controller.save(maintenanceAgreement)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/maintenanceAgreement/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * save(_ as MaintenanceAgreement) >> { MaintenanceAgreement maintenanceAgreement ->
                throw new ValidationException("Invalid instance", maintenanceAgreement.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def maintenanceAgreement = new MaintenanceAgreement()
        controller.save(maintenanceAgreement)

        then:"The create view is rendered again with the correct model"
        model.maintenanceAgreement != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * get(2) >> new MaintenanceAgreement()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.maintenanceAgreement instanceof MaintenanceAgreement
    }

    void "Test the edit action with a null id"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * get(2) >> new MaintenanceAgreement()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.maintenanceAgreement instanceof MaintenanceAgreement
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/maintenanceAgreement/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * save(_ as MaintenanceAgreement)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def maintenanceAgreement = new MaintenanceAgreement(params)
        maintenanceAgreement.id = 1

        controller.update(maintenanceAgreement)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/maintenanceAgreement/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * save(_ as MaintenanceAgreement) >> { MaintenanceAgreement maintenanceAgreement ->
                throw new ValidationException("Invalid instance", maintenanceAgreement.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new MaintenanceAgreement())

        then:"The edit view is rendered again with the correct model"
        model.maintenanceAgreement != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/maintenanceAgreement/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.maintenanceAgreementService = Mock(MaintenanceAgreementService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/maintenanceAgreement/index'
        flash.message != null
    }
}






