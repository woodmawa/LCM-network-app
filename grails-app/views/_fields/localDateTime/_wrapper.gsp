<%@ page import="java.time.LocalDateTime; java.time.format.DateTimeFormatter" %>
<g:set var="localDateTimePattern" value="${message(code: 'default.localDateTime.format',default: 'yyyy-MM-dd HH:mm')}"/>
<g:set var="propertyName" value="${pageScope.propertyName} " />
<g:set var="label" value="${pageScope.label} " />

<%-- <jdt:jdtScaffoldField propertyName="$propertyName" label="${label}" value="${propValue}"></jdt:jdtScaffoldField>  --%>

<g:set var="localDateTimePattern" value="${message(code: 'default.localDateTime.format',default: 'yyyy-MM-dd HH:mm')}"/>
<div class="fieldcontain" >
    <div class="container">
        <div class="row">
            <div class='col-sm-6'>
                <div class="form-group form-inline">
                    <label class='control-label' > ${label} </label>
                    <div class="input-group date"  >
                        <%
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("${localDateTimePattern}")
                        %>
                        <input type='text' class="form-control" value="${value?.format(formatter) }"/>
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-calendar"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%--
<%


    <div class="input-group" id="datetimepicker1">
    <label for=${this.pageScope.property}> ${this.pageScope.getVariable("label")} </label>
        <input type='text' class="form-control" onclick="datePicker()"/>
        <span class="input-group-addon">
            <span class="glyphicon glyphicon-calendar"></span>
        </span>

    </div>


    <g:textField name="${this.pageScope.property}" value="${value?.format(DateTimeFormatter.ofPattern(localDateTimePattern, request.getLocale()))}" />

    //<f:field property="contractedDateTime">
    <g:textField name="contractDateTime" value="${value?.format(DateTimeFormatter.ofPattern(localDateTimePattern, request.getLocale()))}" />
</f:field> //
<f:field property="contractSignedDate" />

    def attrs = [name: propertyName, value: value, type:propertyType, con:controllerName]

    // just put some text
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    String formattedDateTime = attrs.value?.format(formatter)

    def resultStr = g.textField (name:"localDateTime",  value: "$formattedDateTime"  )
    out << resultStr
    out << " used _wrapper localDateTime"
%>
--%>