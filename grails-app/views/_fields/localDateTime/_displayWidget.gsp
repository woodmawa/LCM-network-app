<%@ page import="java.time.LocalDateTime; java.time.format.DateTimeFormatter" %>
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

    formattedDateTime = ldt.format (formatter)

    def res = g.textField (name:"xxx",  value: "$formattedDateTime") ?: " g.textField -> gave null"
    out << "hello william $res (from ${this.maintenanceAgreement.contractSignedDate })"
    def output =  g.textField (name:"xxx",  value: "$formattedDateTime")
    assert jdt
    //def jdtOutput = jdt.displayDateTime(ldt:"${this.maintenanceAgreement.contractSignedDate }",  format:"dd-MM-yyyy HH.mm") ?: "got null"
    out << jdtOutput
    /*out << output
    out << "$formattedDateTime : "
    out << "params " + this.params + " DomainName: " + entName  + " inst.bean: " + bean + " propertyName: " + property  + " value: " + beanProp + " "
    out << "used _displayWidget:localDateTime "*/
%>