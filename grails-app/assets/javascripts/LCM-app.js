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

$(function(){
    $('#datetimepicker1').datetimepicker({
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

// Select all elements with data-toggle="popover" in the document
$('[data-toggle="popover"]').popover();

// Select all elements with data-toggle="tooltip" in the document
$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})