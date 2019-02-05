package com.softwood.taglib

import org.apache.commons.lang3.StringUtils
import org.grails.datastore.mapping.model.MappingContext
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class BootstrapFormsTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "bsf"
    static defaultEncodeAs = [taglib: 'raw']

    @Autowired(required = false)
    Collection<MappingContext> mappingContexts


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

        if (attrs.property in attrs.beanInst.getClass().transients) {
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

        def beanInst = attrs.beanInst
        def beanClassType //= attrs.beanInst.getClass().getDeclaredField(attrs.property)?.type
        def beanProperty
        if (beanInst.hasProperty("$attrs.property")) {
            beanProperty = beanInst.getProperty ("$attrs.property")
            beanClassType = beanInst.getProperty("$attrs.property").getClass()
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

        switch (beanProperty.getClass()) {
            case (java.lang.String):
                attrs.type = 'text'
                break
            case (java.lang.Integer):
                attrs.type = 'number'
                break
            case (java.lang.Float):
                attrs.type = 'number'
                break
            case (java.util.Date):
                if (attrs.property in ['dateCreated', 'lastUpdated']) attrs.type = 'datetime'
                else attrs.type = 'date'
                break
            case (LocalDateTime):
                if (attrs.property in ['dateCreated', 'lastUpdated'] || StringUtils.containsIgnoreCase (attrs.property, "DateTime") ) attrs.type = 'datetime'
                else attrs.type = 'date'
                break
            case (java.lang.Boolean):
                attrs.type = 'select'
                if (attrs.prefix == null) attrs.prefix = 'default.yesno'
                attrs.from = [true, false]
                attrs.noSelection = ['': message(code: 'default.noSelection')]
                break
            default:
                attrs.type = 'text'
        }
        return attrs
    }
}
