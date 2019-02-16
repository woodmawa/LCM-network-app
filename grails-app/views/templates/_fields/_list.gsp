<%--
this gsp - controls the default output for each property in a domainObject properties list
it gets invoked by f:display when bean with no property is asked to be rendered (i.e all props
it renders an ordered list of labels (rather than a span) and div for the parameter which displays
the result of invoking the body of the tag parameterised by each named param p in turn

if the parameter is a required one in domain model, a * indicator is added to the label to show this 

if your body is <g:render template="_fields.xxx/templ.gsp" then that template would return the
rendered form of the parameter p

you call for example call <f:widget just to render format for the field itself

added card - centred content mx-auto and enabled scroll on card body
--%>
<div class="card mx-auto" style="width: 80rem;">
    <div class="card-body " style="height: 400px; overflow-y: auto;">
        <ol class="form-group property-list ${domainClass.decapitalizedName}" >
            <g:each in="${domainProperties}" var="p">
                <li class="fieldcontain  ">
                    <%-- replace with below <span id="${p.name}-label" class="property-label"><g:message code="${domainClass.decapitalizedName}.${p.name}.label" default="${p.defaultLabel}" /></span>  --%>
                    <label class="property-label col-sm-6" for="${p.name}-label" aria-labelledby="${p.name}-label"> <g:message code="${domainClass.decapitalizedName}.${p.name}.label" default="${p.defaultLabel}" /> <g:if test="${p.required}"><span class="required-indicator">*</span></g:if> </label>
                    <div class="property-value form-inline col-sm-8" aria-labelledby="${p.name}-label">${body(p)}</div>
                </li>
            </g:each>
        </ol>
    </div>
</div>


