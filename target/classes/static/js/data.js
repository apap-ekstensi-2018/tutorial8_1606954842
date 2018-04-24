function loadData() {
    var iDisplayIndex = 0,nRow;
    var table = $('#tableStudent').DataTable({
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "ajax": "/student/data-all-student",
        "sAjaxDataProp": "",
        "columns": [
            { render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart +1;
                }
            },
            { data: "npm" },
            { data: "name" },
            { data: "gpa" },
            {
                data: "gpa",
                render: function(data){
                    if (data>=3.49){
                        return "Cum Laude!";
                    }else{
                        return "Sangat Memuaskan";
                    }
                }
            },
            {
                defaultContent: "<button class='btn btn-default btn-xs btn-primary' id='update'>Update</button>&nbsp;<button class='btn btn-danger btn-xs' id='delete'>Delete</button>"
            }
        ]
    });
    return table;
}

$(document).ready( function () {
    var table = loadData();

    $("#tableStudent tbody").on('click', 'button[id="update"]', function(){
        var data = table.row($(this).parents('tr')).data();
        window.open("/student/update/"+data["npm"],"_self");
    })

    $("#tableStudent tbody").on('click', 'button[id="delete"]', function(){
        var data = table.row($(this).parents('tr')).data();
        var bool = confirm("Apakah anda yakin hapus data "+data["name"]+" ?");
        if(bool){
            $.ajax({
                url: "/student/delete/"+data["npm"],
                success: function(e){
                    table.ajax.reload(null, false);
                }
            });
        }
    })
} );