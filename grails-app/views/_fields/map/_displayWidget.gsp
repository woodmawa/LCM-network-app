<%-- default just render the value of the string --%>
<%--${value} --%>

<g:if test="${actionName == 'index'}">
    ${value}
</g:if>
<g:elseif test="${actionName == 'list'}">
    ${value}
</g:elseif>
<g:else>
<div class="container-fluid fieldcontain col-sm-6" >
    <div class="form-group form-inline">
                    <!-- Default dropright button -->
        <div class="btn-group dropright">
            <button type="button" id="dropdownMunuButton" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${value}
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <!-- Dropdown menu links -->
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </div>
    </div>

</div>
</g:else>
