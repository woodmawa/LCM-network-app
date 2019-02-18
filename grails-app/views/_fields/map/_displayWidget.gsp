<%-- default just render the value of the string --%>
<%--${value} --%>

<g:if test="${actionName == 'index'}">
    <bsf:displayMap context="${this}" />
</g:if>
<g:elseif test="${actionName == 'list'}">
    <bsf:displayMap context="${this}" />
</g:elseif>
<g:else>
<div class="container fieldcontain col-sm-12" >
    <div class="form-group form-inline">
        <div class="btn-group input-group text col-sm-8 " >
            <%
                def resultStr = bsf.displayMap(value:pageScope.variables.value ,
                        context:this,
                        bean:pageScope.variables.bean,
                        property:pageScope.variables.property)
           %>
            <input class="form-control" readonly type="text" value="${resultStr}">  <!-- form-control links field with the span -->
            <div class="input-group-append dropdown dropright ">
                <button class="btn btn-icon-fixed-width btn-outline-secondary dropdown-toggle btn-link" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" />
                <div class="dropdown-menu col-xs-12">
                    <table class="table-sm table-condensed table-bordered table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Tag</th>
                                <th scope="col">Value</th>
                            </tr>
                        </thead>
                        <tbody class="tbody-condensed">
                        <g:each in="${pageScope.variables.value}" var="entry">
                            <tr>
                                <td scope="row">${entry.key}</td>
                                <td scope="row">${entry.value}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</g:else>
