<ol class="property-list ${domainClass.decapitalizedName}">
    <g:each in="${domainProperties}" var="p">
        <li class="fieldcontain">
            <span id="${p.name}-label" class="property-label"><g:message code="${domainClass.decapitalizedName}.${p.name}.label" default="${p.defaultLabel}" /></span>
            <div class="property-value" aria-labelledby="${p.name}-label">${body(p)}</div>
        </li>
    </g:each>
</ol>

<%--<div class="fieldcontain" >
    <div class="container">
<g:each in="${domainProperties}" var="p">

    <div class="row">
            <div class='col-sm-6'>
                <div class="form-group form-inline">
                    <label class='control-label' > ${p?.getDefaultLabel()} </label>
                    <div class="input-group text"  >
                        <input type='text' class="form-control" value="${p.name}-label }"/>
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-hand-left"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
</g:each>
    </div>
</div>  --%>
