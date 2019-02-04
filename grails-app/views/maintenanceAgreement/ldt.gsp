<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap datepicket demo</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type='text/javascript' src='//code.jquery.com/jquery-3.3.1.js'></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.min.css">
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
    <script type='text/javascript'>
        $(function(){
            //$('.input-group.date').datepicker({
            $('#datepicker').datepicker({

                calendarWeeks: true,
                todayHighlight: true,
                autoclose: true
            });
        });

    </script>
</head>
<body>
<div class="container">
    <h1>Bootstrap datepicker</h1>
    <div class="input-group date" id="datepicker">
        <input type="text" class="form-control" value="${java.time.LocalDateTime.now().toString()}">
        <span class="input-group-addon">
            <i class="glyphicon glyphicon-calendar"></i>
        </span>
    </div>
</div>
</body>
</html>

