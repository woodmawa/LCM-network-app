<%-- default just render the value of the string
non editable read only format

widget is the field and not the label - this is added by the displayWrapper.gsp
--%>
<%--${value} --%>

<g:if test="${actionName == 'index'}">
    ${value}
</g:if>
<g:elseif test="${actionName == 'list'}">
    ${value}
</g:elseif>
<g:else>
<div class="container fieldcontain col-sm-12" >
    <%--<div class="container">
        <div class="row">
            <div class='col-sm-6'>  --%>
                <div class="form-group form-inline">
                    <%--  label output by _list <label class='control-label' > ${label} </label> --%>
                    <div class="input-group col-sm-8 text"  >
                        <input type='text' readonly class="form-control" value="${value?.toString()}" placeholder="<empty>"/>
                        <div class="input-group-append" >
                            <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" >
                                <i class="fab fa-readme"></i>
                            </button>
                       </div>
                    </div>
                </div>
            <%--</div>
        </div>
    </div>--%>
</div>
</g:else>
