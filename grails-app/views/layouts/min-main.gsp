
<!doctype html>
<html >
<head>
    <title>
        <g:layoutTitle default="LCM Inventory"/>
    </title>
    <%-- <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>  -->

    <%-- <script type='text/javascript' src='//code.jquery.com/jquery-1.12.1.js'></script>--%>

    <%-- used same sources as w3 schools demoes for first 4 lines --%>
    <!--still not working fix tomorrow -->

    <asset:stylesheet src="application.css"/>


    <g:layoutHead/>


</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <a class="navbar-brand" href="/#"><asset:image src="inventoryDisksImage.png" width="7%" height="6%" alt="LCM Logo" /></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
            <g:pageProperty name="page.nav"/>
        </ul>
    </div>

</nav>


    <g:layoutBody />

    <div class="footer row" role="contentinfo" class="container-fluid">
        <p>LCM Inventory v0.1</p>
    </div>

<asset:javascript src="application.js"/>





</body>
</html>
