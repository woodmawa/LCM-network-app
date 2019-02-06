<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
<head>

    <meta name="layout" content="main" />
    <title>Bootstrap try out</title>

--%>
</head>
<body>



<jdt:jdtScaffoldField label="fred" ldt="${java.time.LocalDateTime.now()}"/>

<jdt:jdtScaffoldField />
<hr />

<div class="container">
    <h1>standalone page Bootstrap datepicker</h1>
    <div class="input-group date" id="datepicker2">
        <input type="text" class="form-control" value="${java.time.LocalDateTime.now().toString()}">
        <span class="input-group-addon">
            <i class="glyphicon glyphicon-calendar"></i>
        </span>
    </div>

</div>

<div class="container-fluid fieldcontain col-sm-6" >
    <div class="form-group form-inline">
        <!-- Default dropright button -->
        <div class="btn-group dropright">
            <button type="button" id="dropdownMunuButton" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                hi
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
</body>
</html>

