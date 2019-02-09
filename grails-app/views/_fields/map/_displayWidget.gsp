<%-- default just render the value of the string --%>
<%--${value} --%>

<g:if test="${actionName == 'index'}">
    <bsf:displayMap context="${this}" />
</g:if>
<g:elseif test="${actionName == 'list'}">
    <bsf:displayMap context="${this}" />
</g:elseif>
<g:else>
<div class="container-fluid fieldcontain col-sm-6" >
    <div class="form-group form-inline">
                    <!-- Default dropright button -->
        <div class="btn-group dropright">
            <div class="input-group text dropright " >
                <input class="form-control" readonly type="text" placeholder="<category map>">  <!-- form-control links field with the span -->
                <div class="input-group-append  dropdown-toggle border rounded w-20 p-2 shadow-sm" data-toggle="dropdown">
                    <%--<i class="fa fa-map" /> --%>
                </div>

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
</g:else>
