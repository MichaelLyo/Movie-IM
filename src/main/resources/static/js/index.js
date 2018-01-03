<!--不同界面展示-->

    function toggle(id){
        var time=document.getElementById('search-time');
        var name=document.getElementById('search-name');
        var director=document.getElementById('search-director');
        var actor=document.getElementById('search-actor');
        var category=document.getElementById('search-category');
        var combination=document.getElementById('search-combination');
        var comment=document.getElementById('search-comment');
        if(id==='time') {comment.style.display='none';combination.style.display='none';category.style.display='none';name.style.display='none';director.style.display='none';actor.style.display='none';time.style.display='block';}
        if(id==='name') {comment.style.display='none';combination.style.display='none';category.style.display='none';director.style.display='none';actor.style.display='none';time.style.display='none';name.style.display='block';}
        if(id==='director') {comment.style.display='none';combination.style.display='none';category.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';director.style.display='block';}
        if(id==='actor'){comment.style.display='none';combination.style.display='none';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='block';}
        if(id==='category'){comment.style.display='none';combination.style.display='none';category.style.display='block';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
        if(id==='combination'){comment.style.display='none';combination.style.display='block';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
        if(id==='comment'){comment.style.display='block';combination.style.display='none';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
    }

    function Time(){
        var season=document.getElementById('season');
        var month=document.getElementById('month');
        var day=document.getElementById('day');
        var date=document.getElementById('date');
        if(document.getElementById("search-season").checked) {month.style.display='none';
            day.style.display='none';
            date.style.display='none';
            season.style.display='block';
            for (var i = 0; i <  document.getElementsByName('season').length ; i++) {
                if (document.getElementsByName('season')[i].checked) {
                    document.getElementsByName('season')[i].checked=false;
                }
            }
        }
        else if(document.getElementById("search-month").checked) {
            month.style.display='block';day.style.display='none';date.style.display='none';season.style.display='none';
            for (var i = 0; i <  document.getElementsByName('month').length ; i++) {
                if (document.getElementsByName('month')[i].checked) {
                    document.getElementsByName('month')[i].checked=false;
                }
            }
        }
        else if(document.getElementById("search-day").checked){
            month.style.display='none';day.style.display='block';date.style.display='none';season.style.display='none';
            for (var i = 0; i <  document.getElementsByName('day').length ; i++) {
                if (document.getElementsByName('day')[i].checked) {
                    document.getElementsByName('day')[i].checked=false;
                }
            }
        }
        else if(document.getElementById("search-date").checked){
            month.style.display='none';day.style.display='none';date.style.display='block';season.style.display='none';
            for (var i = 0; i <  document.getElementsByName('date').length ; i++) {
                if (document.getElementsByName('date')[i].checked) {
                    document.getElementsByName('date')[i].checked=false;
                }
            }
        }

    }

    function Table(id){
        var time=document.getElementById('timeTable');
        var name=document.getElementById('nameTable');
        var director=document.getElementById('directorTable');
        var actor=document.getElementById('actorTable');
        var category=document.getElementById('categoryTable');
        var combination=document.getElementById('combinationTable');
        var comment=document.getElementById('commentTable');
        if(id==='time') {
            time.style.display='block';
            $.fn.dataTable.ext.errMode = 'throw';
            /*            var dtname = $('#time').DataTable({ajax: "data.json"});
            setInterval( function () {
                dtname.ajax.reload();
            } );*/
            var dtname;
            dtname = $('#time').DataTable({
                    "data": [
                        [ "Tiger Nixon", "System Architect", "$3,120", "2011/04/25", "Edinburgh", 5421 ],
                        [ "Garrett Winters", "Director", "$8,422", "2011/07/25", "Edinburgh", 8422 ],
                        // 获得数据
                    ],
                    "bPaginage": true,
                    "sPaginationType": "full_numbers",
                    "sAjaxSource": "/graph_manag.action",
                    "oLanguage": {
                        "sLengthMenu": "每页显示 _MENU_ 条",
                        "sZeroRecords": "没有找到符合条件的数据",
                        "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                        "sInfoEmpty": "没有记录",
                        "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                        "sSearch": "搜索",
                        "sProcessing": "数据加载中...",
                        "oPaginate": {
                            "sFirst": "首页",
                            "sPrevious": "上一页",
                            "sNext": "下一页",
                            "sLast": "尾页"
                        }
                    }
                }
            )

        }
        if(id==='name') {
            name.style.display='block';
            $.fn.dataTable.ext.errMode = 'throw';
            $('#name').DataTable({
                "bPaginage": true,
                "sPaginationType": "full_numbers",
                "sAjaxSource": "/graph_manag.action",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "没有记录",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索",
                    "sProcessing": "数据加载中...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                }
            });
        }
        if(id==='director') {director.style.display='block';
            $.fn.dataTable.ext.errMode = 'throw';
            $('#director').DataTable({
                "bPaginage": true,
                "sPaginationType": "full_numbers",
                "sAjaxSource": "/graph_manag.action",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "没有记录",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索",
                    "sProcessing": "数据加载中...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                }
            });}
        if(id==='actor'){actor.style.display='block';
            $.fn.dataTable.ext.errMode = 'throw';
            $('#actor').DataTable({
                "bPaginage": true,
                "sPaginationType": "full_numbers",
                "sAjaxSource": "/graph_manag.action",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "没有记录",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索",
                    "sProcessing": "数据加载中...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                }
            });}
        if(id==='category'){category.style.display='block';            $.fn.dataTable.ext.errMode = 'throw';
            $('#category').DataTable({
                "bPaginage": true,
                "sPaginationType": "full_numbers",
                "sAjaxSource": "/graph_manag.action",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "没有记录",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索",
                    "sProcessing": "数据加载中...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                }
            });}
        if(id==='combination'){combination.style.display='block';            $.fn.dataTable.ext.errMode = 'throw';
            $('#combination').DataTable({
                "bPaginage": true,
                "sPaginationType": "full_numbers",
                "sAjaxSource": "/graph_manag.action",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "没有记录",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索",
                    "sProcessing": "数据加载中...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                }
            });}
        if(id==='comment'){
            comment.style.display='block';
            $.fn.dataTable.ext.errMode = 'throw';
            $('#comment').DataTable({
                "bPaginage": true,
                "sPaginationType": "full_numbers",
                "sAjaxSource": "/graph_manag.action",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "没有记录",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索",
                    "sProcessing": "数据加载中...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                }
            });}
    }
    $(function () {
        $('#container').highcharts({
            data: {
                table: 'datatable'
            },
            chart: {
                type: 'column'
            },
            title: {
                text: '两种模型执行时间比较'
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 's',
                    rotation: 0
                }
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        this.point.y + 's ' + this.point.name.toLowerCase();
                }
            },
            plotOptions: {
                column:{
                    dataLabels:{
                        enabled:true, // dataLabels设为true
                        style:{
                            color:'#42abf8'
                        }
                    }
                }
            }
        });
    });
$(function () {
    $('#actorcontainer').highcharts({
        data: {
            table: 'actordatatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: '两种模型执行时间比较'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 's',
                rotation: 0
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + 's ' + this.point.name.toLowerCase();
            }
        },
        plotOptions: {
            column:{
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#42abf8'
                    }
                }
            }
        }
    });
});
$(function () {
    $('#directorcontainer').highcharts({
        data: {
            table: 'directordatatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: '两种模型执行时间比较'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 's',
                rotation: 0
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + 's ' + this.point.name.toLowerCase();
            }
        },
        plotOptions: {
            column:{
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#42abf8'
                    }
                }
            }
        }
    });
});
$(function () {
    $('#combinationcontainer').highcharts({
        data: {
            table: 'combinationdatatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: '两种模型执行时间比较'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 's',
                rotation: 0
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + 's ' + this.point.name.toLowerCase();
            }
        },
        plotOptions: {
            column:{
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#42abf8'
                    }
                }
            }
        }
    });
});
$(function () {
    $('#categorycontainer').highcharts({
        data: {
            table: 'categorydatatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: '两种模型执行时间比较'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 's',
                rotation: 0
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + 's ' + this.point.name.toLowerCase();
            }
        },
        plotOptions: {
            column:{
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#42abf8'
                    }
                }
            }
        }
    });
});
$(function () {
    $('#namecontainer').highcharts({
        data: {
            table: 'namedatatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: '两种模型执行时间比较'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 's',
                rotation: 0
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + 's ' + this.point.name.toLowerCase();
            }
        },
        plotOptions: {
            column:{
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#42abf8'
                    }
                }
            }
        }
    });
});
$(function () {
    $('#commentcontainer').highcharts({
        data: {
            table: 'commentdatatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: '两种模型执行时间比较'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 's',
                rotation: 0
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + 's ' + this.point.name.toLowerCase();
            }
        },
        plotOptions: {
            column:{
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#42abf8'
                    }
                }
            }
        }
    });
});

