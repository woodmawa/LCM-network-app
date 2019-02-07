<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
<head>

    <meta name="layout" content="main" />
    <title>Bootstrap try out</title>

</head>
<body>



<jdt:jdtScaffoldField label="fred" ldt="${java.time.LocalDateTime.now()}"/>

<jdt:jdtScaffoldField />
<hr />
<h5> standalone Bootstrap datepicker </h5>
<div class="container">
    <div class="input-group date" id="datepicker2">
        <input type="text" class="form-control" value="${java.time.LocalDateTime.now().toString()}">
        <span class="input-group-addon">
            <i class="glyphicon glyphicon-calendar"></i>
        </span>
    </div>

</div>
<hr />
<h5> one row with input group and glph span before or after the input </h5>
<div class="row">
    <div class="col-xs-4">
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
            <input type="text" class="form-control" placeholder="Username">
        </div>
    </div>
    <div class="col-xs-4">
        <div class="input-group">
            <span class="input-group-addon">$</span>
            <input type="text" class="form-control" placeholder="US Dollar">
            <span class="input-group-addon">.00</span>
        </div>
    </div>
</div>
<p>now table with border</p>
<div class="row">
    <div class="col-xs-4">

        <table class="table table-bordered">
            <caption> Map table</caption> <!-- appears at bottom -->
        <thead>
        <tr>
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
<p> working input with dropdown icon </p>
<div class="row">
    <%--<form class="dropdown">--%>
    <div class="col-xs-4">  <!--fix size of cell by grid -->
        <div class="input-group  dropright" data-toggle="dropdown">
        <input class="form-control"  type="text" placeholder="Fill me">
        <span class="input-group-addon dropdown-toggle" aria-haspopup="true" aria-expanded="false">
        </span>


        <ul class="dropdown-menu">
            <li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
            <li><a href="#"><i class="icon-trash"></i> Delete</a></li>
            <li><a href="#"><i class="icon-ban-circle"></i> Ban</a></li>
            <li class="divider"></li>
            <li><a href="#"><i class="i"></i> Make admin</a></li>
        </ul>
        </div>
    <%--</form>--%>
    </div>
</div>
<hr />
<br />
<h5> button dropright </h5>
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
<hr />
<h5> input-group-append </h5>
<div class="container-fluid fieldcontain col-sm-6" >
    <div class="form-group form-inline">
        <!-- Default dropright button -->
        <div class="input-group-append btn-group">
            <input readonly class="form-control" size="16" type="text" />
         <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
                <span class="caret" />
            </a>

            <ul class="dropdown-menu">
                <li>hello</li>
                <li>there</li>

            </ul>
        </div>
    </div>
</div>

<hr />
        <h5> input-group-append + table</h5>
<div class="container-fluid fieldcontain col-sm-6" >
    <div class="col-xs-4">  <!--fix size of cell by grid -->

        <div class="form-group form-inline ">

            <div class="input-group text dropright " >
                <input class="form-control"  type="text" placeholder="Fill me">  <!-- form-control links field with the span -->
                <span class="input-group-addon dropdown-toggle " data-toggle="dropdown" />


               <table class='table table-bordered dropdown-menu' >
            <thead>
            <tr>
                <th scope="col">Tag</th>
                <th scope="col">Value</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <th scope="row">hello</th>
                <th scope="row">william</th>
            </tr>
            </tbody>
        </table>
            </div>
        </div>
    </div>

</div>
</body>
</html>

