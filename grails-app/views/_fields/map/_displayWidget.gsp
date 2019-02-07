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
                <input class="form-control"  type="text" placeholder="fillme">  <!-- form-control links field with the span -->
                <span class="input-group-addon dropdown-toggle " data-toggle="dropdown" />


                             <!-- Dropdown menu links -->
                <%
                    def map = this.pageScope.getVariable("value")
                    out << """
<table class="table table-bordered dropdown-menu">
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
    <th scope="row">$t</th>
    <th scope="row">${v.toString()}</th>
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
