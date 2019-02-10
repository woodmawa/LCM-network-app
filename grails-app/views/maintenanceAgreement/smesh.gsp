<!DOCTYPE html>
<html>
<head>

    <%-- put the page through site mesh layout --%>
    <meta name="layout" content="min-main" />

    <title>Bootstrap Example</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%-- these 4 lines work w3c schools--%>
    <%--
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    --%>


</head>
<body>

<div class="container">
    <h2>Dropdowns</h2>
    <p>The .dropdown class is used to indicate a dropdown menu.</p>
    <p>Use the .dropdown-menu class to actually build the dropdown menu.</p>
    <p>To open the dropdown menu, use a button or a link with a class of .dropdown-toggle and data-toggle="dropdown".</p>
    <div class="dropdown">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            Dropdown button
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Link 1</a>
            <a class="dropdown-item" href="#">Link 2</a>
            <a class="dropdown-item" href="#">Link 3</a>
        </div>
    </div>
</div>


<div class="container">
    <div class="input-group text dropright " >
        <input class="form-control" readonly type="text" placeholder="<category map>">  <!-- form-control links field with the span -->
        <div class="input-group-append dropdown" >
            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">dd
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
                <div role="separator" class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Separated link</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
