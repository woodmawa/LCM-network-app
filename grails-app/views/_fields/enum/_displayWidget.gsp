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
                <input class="form-control" readonly type="text" value="${pageScope.variables.value?.toString()}">  <!-- form-control links field with the span -->
                <div class="input-group-append dropdown dropright ">
                    <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-map-marker-alt"></i>
                    </button>
                </div>
        </div>
    </div>
</div>
</g:else>
