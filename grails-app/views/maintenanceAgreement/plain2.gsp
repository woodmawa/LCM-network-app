<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Example</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%-- these 4 lines work w3c schools--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <asset:stylesheet src="grails.css"></asset:stylesheet>
    <asset:stylesheet src="main.css"></asset:stylesheet>

    <style>
    /* this works and colours the headers */
    .table-condensed thead tr th{
        background-color: lightblue ;
        font-size: 0.7em !important;
        height: 15px;
    }

    /* this doesnt do a thing on any <thead class="thead-blue" - no idea why  */
    .thead-blue {
        background-color: cornflowerblue !important;
    }

    .tbody-condensed {
        font-size: 0.7em !important;
    }

    /* added to set the width of button that sits around icon in rendered field   */
    .btn-icon-fixed-width {
        width: 40px !important;
    }

    </style>

</head>
<body>


<%--
<div class="container">
<p>table by itself</p>
<div class="row">


<table class="table table-condensed table-bordered table-striped ">
<thead>
<tr>
<th scope="col">Tag</th>
<th scope="col">Value</th>
</tr>
</thead>
<tbody>
<tr>
<td scope="row">hello</td>
<td >william</td>
</tr>
</tbody>
</table>

<table class="table  table-bordered table-striped ">
    <thead class="thead-blue" >
    <tr>
        <th scope="col">Tag</th>
        <th scope="col">Value</th>
    </tr>
    </thead>
    <tbody class="tbody-condensed">
    <tr>
        <td scope="row">hello</td>
        <td >william</td>
    </tr>
    </tbody>
</table>
</div>
</div>
--%>


<p> table based drop down  </p>
<div class="container ">
        <div class="col-xs-4">
            <div class="btn-group input-group text " >
                <input class="form-control" readonly type="text" placeholder="<category map>">  <!-- form-control links field with the span -->
                <div class="input-group-append dropdown dropright">
                    <button class="btn btn-icon-fixed-width btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" />
                    <div class="dropdown-menu">
                        <table class="table table-condensed table-bordered table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Tag</th>
                                <th scope="col">Value</th>
                            </tr>
                            </thead>
                            <tbody class="tbody-condensed">
                                <tr>
                                <td scope="row">hello</td>
                                <td scope="row">william</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

</div>


        <div class="footer row " role="contentinfo" >
            <p>LCM Inventory v0.1</p>

        </div>

</body>
</html>
