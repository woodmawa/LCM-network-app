<%@ page import="org.grails.datastore.mapping.model.PersistentEntity; org.grails.datastore.mapping.model.types.Association; org.grails.datastore.mapping.model.types.ManyToOne;org.grails.datastore.mapping.model.types.OneToMany " %>

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
                    <g:set var="propLink" value="${bsf.getPropertyValuesController()}" />
                    <%--<a href="${propLink}" class="btn btn-icon-fixed-width btn-outline-secondary " type="button">
                    <i class="fas fa-link" />

                    </a> --%>
                    <button class="btn btn-icon-fixed-width btn-outline-secondary btn-link"
                            type="button" onclick="location.href='${propLink}';">
                        <i class="fas fa-link">

                        </i>
                    </button>
                </div>
            </div>
        </div>
    </div>
</g:elseif>
<g:elseif test="${pageScope.variables.persistentProperty && pageScope.variables.persistentProperty instanceof org.grails.datastore.mapping.model.types.OneToMany}" >
    <div class="container-fluid fieldcontain col-sm-12" >
        <div class="form-group form-inline">
            <div class="input-group text col-sm-8"  >
                <input type='text' readonly class="form-control" value ="${pageScope.variables.value}" placeholder="<empty>" />
                <div class="input-group-append dropdown dropright" >
                    <button class="btn btn-icon-fixed-width btn-outline-secondary  btn-link" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="badge badge-primary badge-pill">${pageScope.variables.value?.size() ?: 0 }</span>
                        <i class="fas fa-link"></i>
                    </button>

                    <%--<a href="#" data-toggle="popover" title="Popover Header" data-content="Some content inside the popover">Toggle popover</a>--%>
                    <div class="dropdown-menu" aria-labelledby="references" >
                        <table class='table table-bordered table-striped table-condensed ' >

                        <thead >
                        <tr>
                            <th class="bg-info text-white" scope="col">References</th>
                         </tr>
                        </thead>
                        <tbody>
                        <g:each in="${pageScope.variables.value}" var="p">
                            <tr>
                                <td scope="row"  >
                                    <a href="${bsf.getPropertyValuesController(property:p)}">${p?.toString()}</a>
                                </td>
                            </tr>
                            </g:each>
                        </tbody>
                        </table>
                     </div>
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