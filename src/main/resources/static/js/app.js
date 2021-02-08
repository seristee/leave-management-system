$(function () {

    var url = window.location;
    // for single sidebar menu
    // $('ul.nav-sidebar a').filter(function () {
    //     return this.href == url;
    // }).addClass('active');
    //     .css('color', 'white');
    
    // for sidebar menu and treeview
    // $('ul.nav-treeview a').filter(function () {
    //     return this.href == url;
    // }).parentsUntil(".nav-sidebar > .nav-treeview")
    //     .css({'display': 'block'})
    //     .addClass('menu-open').prev('a')
    //     .addClass('active');

    //select2
    $('.select2').select2({
        placeholder: {
            text: "Select an Option",
            id: '-1'
        },
        allowClear: false
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
    //datetimepicker class
    $('.dateTime').datetimepicker({
        format: 'YYYY/MM/DD',
        minDate: Date.now(),
    });

    // delete button prompt
    $('.delete').each(function() {
       $(this).click(function (eve) {
           // eve.preventDefault();
           // Swal.fire({
           //     title: 'Are you sure you want to delete this?',
           //     text: "You won't be able to revert this!",
           //     icon: 'warning',
           //     showCancelButton: true,
           //     confirmButtonColor: '#3085d6',
           //     cancelButtonColor: '#d33',
           //     confirmButtonText: 'Yes, delete it!'
           // }).then((result) => {
           //     if (result.isConfirmed) {
           //         eve.returnValue = true;
           //     }
           // })
       })
    });

});
