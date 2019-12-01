<%--
index.gsp  defaults to  invokes <f:table on collection of objects

so this template renders a table and calls <f:display bean: property: for each column

--%>

<div class="card">
    <div class="card-body scroll">
    <table>
    <thead>
    <tr>
        <g:each in="${domainProperties}" var="p" status="i">
            <g:sortableColumn property="${p.property}" title="${p.label}" />
        </g:each>
    </tr>
    </thead>
    <tbody>
    <g:each in="${collection}" var="bean" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <g:each in="${domainProperties}" var="p" status="j">
                <g:if test="${j==0}">  <%-- ie its the headers row --%>
                    <td><g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link></td>
                </g:if>
                <g:else>
                    <td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                    <%--<td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td> --%>
                </g:else>
            </g:each>
        </tr>
    </g:each>
    </tbody>
    </table>
    </div>
</div>