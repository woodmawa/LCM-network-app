<%-- _widget template is used to create an editable input field
variable

--%>

<div class="container fieldcontain col-sm-12" >
    <div class="form-group form-inline">
        <%--  label output by _list <label class='control-label' > ${label} </label> --%>
        <div class="input-group col-sm-8 text"  >
            <input type='text' class="form-control" value="${value?.toString()}" placeholder="<empty>"/>
            <div class="input-group-append" >
                <button class="btn btn-icon-fixed-width btn-outline-secondary " type="button" >
                    <i class="far fa-comment"></i>
                </button>
            </div>
        </div>
    </div>

</div>