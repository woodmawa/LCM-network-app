<%-- _table.gsp invokes <f:display bean: property:
so we want the plan rendered text from the _displayWidget.gsp
so just render the output from the _displayWidget
--%>

<g:if test="${actionName == 'index'}">
    ${value}
</g:if>
<g:elseif test="${actionName == 'list'}">
    ${value}
</g:elseif>
<g:else>

<%--<div class="container fieldcontain col-sm-12" > --%>
    <%--<div class="container">
        <div class="row">
            <div class='col-sm-6'>  --%>
    <div class="form-group form-inline">
        <label class='control-label' for=${pageScope.property}> ${label} </label>
        <g:if test="${required}">
            <span class="required-indicator">*</span>
        </g:if>
        <g:render template="/_fields/string/displayWidget" model="${pageScope.variables}"/>
        <%--<div class="input-group col-sm-8 text"  >
            <input type='text' readonly class="form-control" value="${value?.toString()}" placeholder="<empty>"/>
            <div class="input-group-append" >
                <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" >
                    <i class="fab fa-readme"></i>
                </button>
            </div>
        </div>--%>
    </div>

</g:else>