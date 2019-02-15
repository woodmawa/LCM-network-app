<%-- _table.gsp invokes <f:display bean: property:
so we want the plan rendered text from the _displayWidget.gsp
so just render the output from the _displayWidget
--%>

<%--<div class="container fieldcontain col-sm-12" > --%>
    <%--<div class="container">
        <div class="row">
            <div class='col-sm-6'>  --%>
    <%--<div class="form-group form-inline">--%>
        <%--  label output by _list <label class='control-label' > ${label} </label> --%>
        <g:render template="/_fields/string/displayWidget" model="${pageScope.variables}"/>
        <%--<div class="input-group col-sm-8 text"  >
            <input type='text' readonly class="form-control" value="${value?.toString()}" placeholder="<empty>"/>
            <div class="input-group-append" >
                <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" >
                    <i class="fab fa-readme"></i>
                </button>
            </div>
        </div>--%>
    <%--</div>--%>
    <%--</div>
</div>
</div>--%>
<%--</div> --%>
