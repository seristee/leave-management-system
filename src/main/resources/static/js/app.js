$(function () {

    var url = window.location;
    // for single sidebar menu
    $('ul.nav-sidebar a').filter(function () {
        return this.href == url;
    }).addClass('active');
        //.css('color', 'white');

    // for sidebar menu and treeview
    $('ul.nav-treeview a').filter(function () {
        return this.href == url;
    }).parentsUntil(".nav-sidebar > .nav-treeview")
        .css({'display': 'block'})
        .addClass('menu-open').prev('a')
        .addClass('active');

    //password reset



    //select2
    $('.select2').select2();


    //datatables
    $('.dataTable').DataTable({
        responsive: true,
        "paging":   true,
        "ordering": true,
        "info":     true,
    });

});