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
    <%--<div class="container">
        <div class="row">
            <div class='col-sm-6'>  --%>
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
                    <%-- doesnt work wit this version of javascript needs ecma script 2015!
                    label output by _list <label class='control-label' > ${label} </label> --%>
                    <%--<template>
                        <b-table striped hover :items="items" :fields="fields"></b-table>
                    </template>
                    <script>
                        export default {
                            data() {
                                return {
                                    fields: ['tag', 'value'],
                                    items: [
                                        {tag: 'abc', value:"hello"}
                                    ]
                                }
                            }
                        }
                    </script>
                    <div class="input-group text"  >
                        <input type='text' readonly class="form-control" value="${
                            value?.toString() }"/>
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-hand-left"></i>
                        </span>
                    </div>
                </div>  --%>
            <%--</div>
        </div>
    </div>--%>
</div>
</g:else>
