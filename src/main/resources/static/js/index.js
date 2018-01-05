<!--不同界面展示-->
var mvNametb=null;
function Time () {
    var season=document.getElementsByName('season');
    var month=document.getElementsByName('month');
    for (var i = 0; i <  season.length ; i++) {
        if (season[i].checked) {
            for (var j = 0; j <  month.length ; j++) {
                month[j].checked = false;
                month[j].disabled = true;
            }
        }
    }
    for (var i = 0; i <  season.length ; i++) {
        if (!season[i].checked) {
            if(i===3){
                for (var j = 0; j <  month.length ; j++) {
                    month[j].disabled = false;
                }
            }
        }
        else{break;}
    }
    for (var i = 0; i <  month.length ; i++) {
        if (month[i].checked) {
            for (var j = 0; j <  month.length ; j++) {
                season[j].checked = false;
                season[j].disabled = true;
            }
        }
    }
    for (var i = 0; i <  month.length ; i++) {
        if (!month[i].checked) {
            if(i===11){
                for (var j = 0; j <  season.length ; j++) {
                    season[j].disabled = false;
                }
            }
        }
        else{break;}
    }
    if(document.getElementById("search-date").checked){
        for (var i = 0; i <  document.getElementsByName('date').length ; i++) {
            if (document.getElementsByName('date')[i].checked) {
                document.getElementsByName('date')[i].checked=false;
                document.getElementsByName('date')[i].disabled=true;
            }
        }
    }

}
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
            var dtname;
            $.ajax({
                type: "post",
                url: "/movie/ajax/page?name=1",
                async: false,
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    dtname = $('#time').DataTable({
                            ajax:{
                                url:'/movie/ajax/show',
                                dataSrc: '',
                            },
                            columns: [ {data:"movieName"},
                                {data:"releaseTime"},
                                {data:"style"},
                                {data:"director"},
                                {data:"actor"},
                                {data:"edition"}],
                            "bPaginage": true,
                            "sPaginationType": "full_numbers",
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
                    );
                },
                error: function (data) {
                    alert("删除失败");
                }
            });


        }
        if(id==='name') {
            name.style.display='block';
            $.fn.dataTable.ext.errMode = 'throw';
            var moviename = $("#search-input").val();
            if(mvNametb==null) {
                mvNametb = $('#name').DataTable({
                    ajax:{
                        type:"post",
                        url:"/movie/name/search?name="+moviename,
                        dataSrc:""
                    },
                    columns: [
                        {data: "movieId"},
                        {data: "title"},
                        {data: "releaseDate"},
                        {data: "runTime"},
                        {data: "studio"},
                        {data:"publisher"}
                    ],
                    "bPaginage": true,
                    "sPaginationType": "full_numbers",
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
            else
            {
                mvNametb.ajax.url("/movie/name/search?name="+moviename).load();
                console.log("???");
            }


        }
        if(id==='director') {director.style.display='block';
            $.fn.dataTable.ext.errMode = 'throw';
            $('#director').DataTable({
                ajax:{
                    url: '/movie/ajax/showdirector',
                    dataSrc: ''
                },
                columns: [ {data:"movieName"},
                    {data:"releaseTime"},
                    {data:"genre"},
                    {data:"director"},
                    {data:"actor"},
                    {data:"edition"}],
                "bPaginage": true,
                "sPaginationType": "full_numbers",
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
                ajax:{
                    url: '/movie/ajax/showactor',
                    dataSrc: ''
                },
                columns: [ {data:"movieName"},
                    {data:"releaseTime"},
                    {data:"genre"},
                    {data:"director"},
                    {data:"actor"},
                    {data:"edition"}],
                "bPaginage": true,
                "sPaginationType": "full_numbers",
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
                ajax:{
                    url: '/movie/ajax/showcategory',
                    dataSrc: ''
                },
                columns: [ {data:"movieName"},
                    {data:"releaseTime"},
                    {data:"genre"},
                    {data:"director"},
                    {data:"actor"},
                    {data:"edition"}],
                "bPaginage": true,
                "sPaginationType": "full_numbers",
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
                ajax:{
                    url: '/movie/ajax/showcombination',
                    dataSrc: ''
                },
                columns: [ {data:"movieName"},
                    {data:"releaseTime"},
                    {data:"genre"},
                    {data:"director"},
                    {data:"actor"},
                    {data:"edition"}],
                "bPaginage": true,
                "sPaginationType": "full_numbers",
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
                ajax:{
                    url: '/movie/ajax/showcomment',
                    dataSrc: ''
                },
                columns: [ {data:"movieName"},
                    {data:"releaseTime"},
                    {data:"genre"},
                    {data:"director"},
                    {data:"actor"},
                    {data:"edition"}],
                "bPaginage": true,
                "sPaginationType": "full_numbers",
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


