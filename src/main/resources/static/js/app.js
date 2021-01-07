$(function () {

    // var url = window.location;
    // for single sidebar menu
    // $('ul.nav-sidebar a').filter(function () {
    //     return this.href == url;
    // }).addClass('active');
        //.css('color', 'white');
    //
    // for sidebar menu and treeview
    // $('ul.nav-treeview a').filter(function () {
    //     return this.href == url;
    // }).parentsUntil(".nav-sidebar > .nav-treeview")
    //     .css({'display': 'block'})
    //     .addClass('menu-open').prev('a')
    //     .addClass('active');

    //password reset



    //select2
    $('.select2').select2({
    });


    //datatables
    $('.dataTable').DataTable({
        renderer: "bootstrap",
        responsive: true,
        paging:   true,
        ordering: true,
        info:     false,
    });

    $('.datePickerPast').datetimepicker({
        format: 'YYYY-MM-DD',
        maxDate: new Date(),
        useCurrent: false,
        icons: {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            up: "fa fa-arrow-up",
            down: "fa fa-arrow-down"
        }
    });
    //datepicker


});