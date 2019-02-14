<%--
this gsp - controls the default output for each property in a domainObject properties list
it gets invoked by f:display when bean with no property is aksed to be rendered (i.e all props
it renders an ordered list of labels and div for the parameter which displays
the result of invoking the body of the tag parameterised by each param p in turn

if your body is <g:render template="_fields.xxx/templ.gsp" then that template would return the
rendered form of the parameter p

you call for example call <f:widget just to render format for the field itself

added card - centred content mx-auto and enabled scroll on card body
--%>
<div class="card mx-auto" style="width: 80rem;">
    <h5 class="card-header">DomainClass: ${domainClass.decapitalizedName}</h5>
    <div class="card-body " style="height: 300px; overflow-y: auto;">
        <ol class="property-list ${domainClass.decapitalizedName}" >
            <g:each in="${domainProperties}" var="p">
                <li class="fieldcontain">
                    <span id="${p.name}-label" class="property-label"><g:message code="${domainClass.decapitalizedName}.${p.name}.label" default="${p.defaultLabel}" /></span>
                    <div class="property-value" aria-labelledby="${p.name}-label">${body(p)}</div>
                </li>
            </g:each>
        </ol>
    </div>
</div>


