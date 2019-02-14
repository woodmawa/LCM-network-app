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
                    <!-- Default dropright button -->
        <%--<div class="btn-group ">--%>
            <div class="btn-group input-group text col-sm-8 " >
                <input class="form-control" readonly type="text" value="${[hi:'there']}">  <!-- form-control links field with the span -->
                <div class="input-group-append dropdown dropright ">
                    <button class="btn btn-icon-fixed-width btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" />
                    <div class="dropdown-menu col-xs-12">
                        <table class="table-sm table-condensed table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Tag</th>
                                    <th scope="col">Value</th>
                                </tr>
                            </thead>
                            <tbody class="tbody-condensed">
                                <tr>
                                    <td scope="row">hello</td>
                                    <td scope="row">william</td>
                                </tr>
                            </tbody>
                        </table>
                        <%--<a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                        <div role="separator" class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Separated link</a>-->
                    </div>
                </div>
                <%--<div class="input-group-append  dropdown-toggle border rounded w-20 p-2 shadow-sm" data-toggle="dropdown">
                    <i class="fa fa-map" />
                </div>--%>

                <%--<span class="input-group-append dropdown-toggle " data-toggle="dropdown">
                    <i class="fa fa-map" />
                </span>--%>


                             <!-- Dropdown menu links -->
                <%
                    def map = this.pageScope.getVariable("value")
                    out << """
<table class="table table-bordered table-striped dropdown-menu">
    <thead>
        <tr>
            <th scope="col">Tag</th>
            <th scope="col">Value</th>
        </tr>
    </thead>
    <tbody>
"""

                    // then for each map entry

                    map.each {t, v ->
                        out << """
<tr>
    <td scope="row">$t</td>
    <td scope="row">${v.toString()}</td>
</tr>"""
                    }
                    //then finalise the table
                    out << "</tbody>" << "</table>"
                %>
            </div>
        </div>
    </div>
    </div>
</div>
</g:else>
