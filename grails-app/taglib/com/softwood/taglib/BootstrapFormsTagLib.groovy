package com.softwood.taglib

import org.apache.commons.lang3.StringUtils
import org.grails.datastore.mapping.model.MappingContext
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDate
import java.time.LocalDateTime

class BootstrapFormsTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "bsf"
    static defaultEncodeAs = [taglib: 'raw']

    @Autowired(required = false)
    Collection<MappingContext> mappingContexts

    /**
     * called from -fields/maps/_displayWidget.gsp
     * inputs:
     * value - map variable to render
     * context - parent tags calling page context for key variables etc
     * bean - domain instance being rendered (should alos be in context
     * property - name of domain map property to be calculate the display format for
     */
    def displayMap = { attrs, body ->

        def context = attrs.context  //parent calling context
        Map ctxmap = context?.binding.variables
        Map mapValue = attrs.value
        def bean = attrs.bean ?: ctxmap?.bean
        def property = attrs.property ?: ctxmap?.property

        def domainClassName = attrs.entityName ?: ctxmap?.get("entityName")  //name of domain class

        if (!mapValue)
            mapValue = bean[property] != null ? bean[property] : '<i class="fas fa-minus" style="color: red;"></i>'

        def propertyClassifier = propertyClassToType(ctxmap)

        StringBuilder mapString = new StringBuilder("[")

        Set entries = mapValue.entrySet()

        def i= 0
        def len = entries.size()
        for (entry in entries) {
            mapString << "${entry.key} : ${entry.value.toString()}"
            if (len == 1) {
                    mapString << "]"
                    break
            }
            if (i >= 2) {
                    mapString << ", ...]"
                    break
            } else {
                    mapString << ", "
            }
        }

        //put calculated string to output stream
        out << mapString ?: ''

        if (context) {
            context.binding.displayMapStr = mapString
        }

        mapString

    }

    def showField = { attrs, body ->
        def property = attrs.property
        def beanStr = attrs.bean

        def beanInst = this.pageScope.getVariable("maintenanceAgreement")
        attrs.beanInst = beanInst
        attrs.domainCapitalized = beanInst.getClass().getSimpleName()//attrs.bean.getClass().getSimpleName()
        attrs.domain = attrs.domainCapitalized.uncapitalize()
        //if (!attrs.value) attrs.value = attrs.bean[attrs.property] != null ? attrs.bean[attrs.property] : '<i class="fas fa-minus" style="color: red;"></i>'
        if (!attrs.value) attrs.value = beanInst[property] != null ? beanInst[property] : '<i class="fas fa-minus" style="color: red;"></i>'

        attrs.offset = attrs.offset ? 'col-md-offset-' + attrs.offset : ''
        attrs.pre = attrs.pre ?: false
        if (attrs.width == null) attrs.width = 3

        attrs.show = true

        if (!attrs.type) attrs = propertyClassToType(attrs)

        if (attrs.prefix && attrs.bean[attrs.property] != null) attrs.value = g.message(code: attrs.prefix + '.' + attrs.value)
        out << g.render(template: "/templates/showField",
                model: [width: attrs.width, offset: attrs.offset, domainCapitalized: attrs.domainCapitalized, domain: attrs.domain, property: property,
                        cssClass: attrs.cssClass, value: attrs.value, addon: attrs.addon, type: attrs.type,
                        offset: attrs.offset, rawValue: attrs.rawValue, link: attrs.link])
    }


    /** private
     *
     * @param attrs
     * @return
     */
    private def propertyClassToType(attrs) {

        if (attrs.property in attrs?.bean.getClass().transients) {
            attrs.type = 'transient'
            return attrs
        }

        /*
        def enumList = grailsApplication?.config?.get('bootstrapForms.enumList')
        def enumClassesList = []
        if(enumList) {
                enumList.each { e ->
                    enumClassesList.add(Class.forName(e))
            }
        }
        */

        def bean = attrs.bean
        def beanClassType //= attrs.beanInst.getClass().getDeclaredField(attrs.property)?.type
        def beanProperty
        if (bean.hasProperty("$attrs.property")) {
            beanProperty = bean.getProperty ("$attrs.property")
            beanClassType = bean.getProperty("$attrs.property").getClass()
        }
        else
            return attrs
        /*
        if(enumClassesList?.contains(beanClassType)) {
            attrs.type = 'select'
            attrs.from = beanClassType.values()
            attrs.noSelection = ['': message(code: 'default.noSelection')]
            def tmp = beanClassType.toString().split('\\.')[-1]
            attrs.prefix = 'enum.' + tmp.uncapitalize() + '.value'
            return attrs
        }*/

        if(beanClassType.isEnum()) {
            attrs.type = 'select'
            attrs.from = beanClassType.values()
            attrs.noSelection = ['': message(code: 'default.noSelection')]
            def tmp = beanClassType.toString().split('\\.')[-1]
            attrs.prefix = attrs.prefix ?: 'enum.' + tmp.uncapitalize() + '.value'
            return attrs
        }

        if(grailsApplication.getArtefacts("Domain")*.clazz.contains(beanClassType)) {
            attrs.type = attrs.type ?: 'select'
            attrs.from = attrs.from ?: beanClassType.list()
            attrs.optionKey = attrs.optionKey ?: 'id'
            if(attrs.show) attrs.value = attrs.value ?: attrs.value?.id?.toString()
            else attrs.value = attrs.value?.id?.toString() ?: attrs.value
            attrs.noSelection = attrs.noSelection ?: ['': message(code: 'default.noSelection')]

            return attrs
        }

        Class<?> propClazzType = beanProperty.getClass()
        switch (propClazzType) {
            case Map:
                attrs.propertyClassifier  = 'map'
                break
            case (java.lang.String):
            case (GString):
                attrs.propertyClassifier = 'text'
                break
            case Number:
                attrs.propertyClassifier = 'number'
                break
            /*case (java.lang.Float):
                attrs.type = 'number'
                break */
            case (java.util.Date):
                if (attrs.property in ['dateCreated', 'lastUpdated'] || StringUtils.containsIgnoreCase (attrs.property, "DateTime")) attrs.propertyClassifier = 'datetime'
                else attrs.propertyClassifier = 'date'
                break
            case (LocalDateTime):
                if (attrs.property in ['dateCreated', 'lastUpdated'] || StringUtils.containsIgnoreCase (attrs.property, "DateTime") ) attrs.propertyClassifier = 'localdatetime'
                else attrs.propertyClassifier = 'date'
                break
            case (LocalDate):
                if (attrs.property in ['dateCreated', 'lastUpdated'] || StringUtils.containsIgnoreCase (attrs.property, "DateTime") ) attrs.propertyClassifier = 'localdate'
                else attrs.propertyClassifier = 'date'
                break
            case (java.lang.Boolean):
                attrs.propertyClassifier = 'select'
                if (attrs.prefix == null) attrs.prefix = 'default.yesno'
                attrs.from = [true, false]
                attrs.noSelection = ['': message(code: 'default.noSelection')]
                break
            default:
                attrs.propertyClassifier = 'text'
        }
        return attrs
    }
}
