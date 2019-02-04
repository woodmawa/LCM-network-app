<%@ page import="java.time.LocalDateTime; java.time.format.DateTimeFormatter" %>
<g:set var="localDateTimePattern" value="${message(code: 'default.localDateTime.format',default: 'yyyy-MM-dd HH:mm')}"/>
${value?.format(DateTimeFormatter.ofPattern(localDateTimePattern, request.getLocale()))}

<%--
<%
    def attrs = [name: propertyName, value: value, type:propertyType, con:controllerName]

    java.time.format.DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    String formattedDateTime //= attrs.value.format(formatter)

    Map pagemap = this.pageScope.getVariables()

    String entName = pagemap.entityName
    def bean = pagemap.bean
    def property = pagemap.property
    def beanProp = bean."$property"

    def theme = pagemap.theme
    def collection = pagemap.collection
    def label = pagemap.label
    def domainClass = pagemap.domainClass

    java.time.LocalDateTime ldt = attrs.value
    String format = attrs.format ?: "dd-MM-yyyy HH.mm"

    formattedDateTime = ldt?.format (formatter)

    //def resultStr = g.textField (name:"localDateTime",  value: "$formattedDateTime"  )


    //just return the basic formatted value as String to display
    out << formattedDateTime ?: "<null>"
    /*out << output
    out << "$formattedDateTime : "
    out << "params " + this.params + " DomainName: " + entName  + " inst.bean: " + bean + " propertyName: " + property  + " value: " + beanProp + " "
    out << "used _displayWidget:localDateTime "*/
%>
--%>