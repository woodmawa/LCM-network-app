<%-- default just render the value of the string --%>
<%--${value} --%>

<g:if test="${actionName == 'index'}">
    ${value}
</g:if>
<g:elseif test="${actionName == 'list'}">
    ${value}
</g:elseif>
<g:else>
<div class="container fieldcontain col-sm-6" >
    <%--<div class="container">
        <div class="row">
            <div class='col-sm-6'>  --%>
                <div class="form-group form-inline">
                    <%--  label output by _list <label class='control-label' > ${label} </label> --%>
                    <div class="input-group text"  >
                        <input type='text' readonly class="form-control" value="${value?.toString() }"/>
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-hand-left"></i>
                        </span>
                    </div>
                </div>
            <%--</div>
        </div>
    </div>--%>
</div>
</g:else>
