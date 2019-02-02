<%-- edit input 'widget' for localDateTime --%>
<%
    def attrs = [name: property, value: value]
    /*if (required) attrs.required = '' */
    /* out << joda.datetimeLocalField(attrs) */
    out << "useing _widget template, $attrs.name with value $attrs.value"
%>