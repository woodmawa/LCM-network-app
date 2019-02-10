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

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment-with-locales.min.js"></script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />

    <style>
    /* this works and colours the headers */
    .table-condensed thead tr th{
        background-color: lightblue ;
        font-size: 0.7em !important;
        height: 15px;
    }

    table {
        margin:0px !important;
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



<p>f:display category  </p>
<f:display bean="maintenanceAgreement" >

</f:display>

<hr />

<p>f:field category</p>
<f:field bean="${this.pageScope.maintenanceAgreement}" property="category">
    <g:render template="/_fields/map/displayWidget" ></g:render>
    </f:field>

<hr />

<p>render whole bean </p>
<f:all bean="maintenanceAgreement" templates="/_fields"/>

<hr />
<div class="container ">
    <p> table based drop down  </p>
        <div class="col-xs-4">
            <div class="btn-group input-group text " >
                <input class="form-control" readonly type="text" value="${[hi:'there']}" placeholder="<empty category map>">  <!-- form-control links field with the span -->
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
