// used by bootsrap date picker - matching class selector is for class class='.input-group date'
$(function(){
    $('.input-group.date').datepicker({
        calendarWeeks: true,
        todayHighlight: true,
        autoclose: true
    });
});

$(function(){
    $('.input-group.datetime').datetimepicker({
        calendarWeeks: true,
        todayHighlight: true,
        autoclose: true
    });
});

function sayHelloWilliam() {
        $.ajax (
            document.write ("hello william")
        )
    }
