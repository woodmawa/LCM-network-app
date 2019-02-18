<%@ page import="org.grails.datastore.mapping.model.types.Association; org.grails.datastore.mapping.model.types.ManyToOne;org.grails.datastore.mapping.model.types.OneToMany " %>

<%-- default just render the value of the string --%>
<%--${value} --%>


<g:if test="${actionName == 'index'}">
    ${value}
</g:if>
<g:elseif test="${actionName == 'list'}">
    ${value}
</g:elseif>
<g:elseif test="${pageScope.variables.persistentProperty && pageScope.variables.persistentProperty instanceof org.grails.datastore.mapping.model.types.ManyToOne}" >
    <div class="container fieldcontain col-sm-12" >
        <div class="form-group form-inline">
            <div class="input-group text col-sm-8"  >
                <input type='text' readonly class="form-control" value ="${pageScope.variables.value}" placeholder="<empty>" />
                <div class="input-group-append" >
                    <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" >
                        <i class="fas fa-link"></i>

                    </button>
                </div>
            </div>
        </div>
    </div>
</g:elseif>
<g:elseif test="${pageScope.variables.persistentProperty && pageScope.variables.persistentProperty instanceof org.grails.datastore.mapping.model.types.OneToMany}" >
    <div class="container fieldcontain col-sm-12" >
        <div class="form-group form-inline">
            <div class="input-group text col-sm-8"  >
                <input type='text' readonly class="form-control" value ="${pageScope.variables.value}" placeholder="<empty>" />
                <div class="input-group-append" >
                    <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" >
                        <i class="fas fa-link">[]</i>

                    </button>
                </div>
            </div>
        </div>
    </div>
</g:elseif>
<g:else>
    <div class="container fieldcontain col-sm-12 " >
        <div class="form-group form-inline">
            <%--  label output by _list <label class='control-label' > ${label} </label> --%>
            <div class="input-group text col-sm-8"  >
                <input type='text' readonly class="form-control" value="${value?.toString()}" placeholder="<empty>"/>
                <div class="input-group-append" >
                    <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" >
                        <i class="fab fa-readme"></i>

                    </button>
                </div>
            </div>
        </div>
    </div>
</g:else>