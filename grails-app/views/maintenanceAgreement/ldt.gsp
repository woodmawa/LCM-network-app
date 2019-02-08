<!DOCTYPE html>
<html>
<head>

    <%--<meta name="layout" content="main" />--%>
    <title>Bootstrap try out</title>

    <%-- <script type='text/javascript' src='//code.jquery.com/jquery-1.12.1.js'></script>--%>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

    <script type='text/javascript' src='//code.jquery.com/jquery-3.3.1.js'></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.min.css">
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>

    <style>
.alert-info{
    background-color:#d9edf7 !important;
}
</style>

</head>
<body>

    <div class="row">
    <%--<div class="container-fluid col-sm-6" >--%>
    <div class="col-xs-4">

        <table class='table table-bordered table-striped table-condensed ' >

            <thead >
            <tr>
                <th class="bg-info text-white" scope="col">Tag</th>
                <th class="bg-info text-white" scope="col">Value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td scope="row">hello</td>
                <td scope="row">william</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<p>now table with border</p>
    <div class="row">
    <div class="col-xs-4">

        <table class="table table-bordered table-condensed">
            <caption> Map table</caption> <!-- appears at bottom -->
            <thead class="alert alert-info">
            <tr >
                <th>tag</th>
                <th>value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>hello</td>
                <td>there</td>
            </tr>
            <tr>
                <td>Peter</td>
                <td>Parker</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>



</body>
</html>

