<!--不同界面展示-->
var mvNametb=null;
var mvTypetb=null;
var mvDirectortb =null;
var mvActortb = null;
var mvTimetb = null;
var timechart,namechart,directorchart,actorchart,categorychart,commentchart,combinationchart; //柱状图
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
function Table(id) {
        var time = document.getElementById('timeTable');
        var name = document.getElementById('nameTable');
        var director = document.getElementById('directorTable');
        var actor = document.getElementById('actorTable');
        var category = document.getElementById('categoryTable');
        var combination = document.getElementById('combinationTable');
        var comment = document.getElementById('commentTable');
        if (id === 'time') {
            time.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
            timeChoice();
            if (mvTimetb == null) {
                mvTimetb = $('#name').DataTable({
                    ajax: {
                        type: "post",
                        url: '/movie/ajax/showtime',
                        dataSrc: "",
                        data : {
                            "year":year.toString(),
                            "month":monthArray.toString(),
                            "dateType":dateType.toString(),
                            "day":dayArray.toString(),
                            "date":date.toString(),
                            "season":seasonArray.toString()
                        }
                    },
                    columns: [
                        {data: "movieId"},
                        {data: "title"},
                        {data: "releaseDate"},
                        {data: "runTime"},
                        {data: "studio"},
                        {data: "publisher"}
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
                $.ajax({
                    url: '/movie/ajax/showtime',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(timechart!==undefined){
                            timechart.destroy();
                        }
                        timechart =  Highcharts.chart('timecontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
            else {
                mvTimetb.ajax.url("/movie/name/search?name=" + moviename).load();
                $.ajax({
                    url: '/movie/ajax/showtime',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(timechart!==undefined){
                            timechart.destroy();
                        }
                        timechart =  Highcharts.chart('timecontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
        }
        if (id === 'name') {
            name.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';

            var moviename = $("#sjw-search-name").val();
            if (mvNametb == null) {
                mvNametb = $('#name').DataTable({
                    ajax: {
                        type: "post",
                        url: "/movie/name/search?name=" + moviename,
                        dataSrc: ""
                    },
                    columns: [
                        {data: "movieId"},
                        {data: "title"},
                        {data: "releaseDate"},
                        {data: "runTime"},
                        {data: "studio"},
                        {data: "publisher"}
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
                $.ajax({
                    url: '/movie/ajax/showmovieName',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(namechart!==undefined){
                            namechart.destroy();
                        }
                        namechart =  Highcharts.chart('namecontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
            else {
                mvNametb.ajax.url("/movie/name/search?name=" + moviename).load();
                $.ajax({
                    url: '/movie/ajax/showmovieName',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(namechart!==undefined){
                            namechart.destroy();
                        }
                        namechart =  Highcharts.chart('namecontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }





        }
        if (id === 'director') {
            director.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';

            var direcorName = $("#sjw-search-director").val();
            if (mvDirectortb == null) {
                mvDirectortb = $('#director').DataTable({
                    ajax: {
                        type: "post",
                        url: "/movie/director/search?directorName=" + direcorName,
                        dataSrc: ""
                    },
                    columns: [
                        {data: "movieId"},
                        {data: "title"},
                        {data: "director"},
                        {data: "releaseDate"},
                        {data: "runTime"},
                        {data: "studio"},
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
                $.ajax({
                    url: '/movie/ajax/showdirector',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(directorchart!==undefined){
                            directorchart.destroy();
                        }
                        directorchart =  Highcharts.chart('directorcontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
            else {
                mvDirectortb.ajax.url("/movie/director/search?directorName=" + direcorName).load();
                $.ajax({
                    url: '/movie/ajax/showdirector',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(directorchart!==undefined){
                            directorchart.destroy();
                        }
                        directorchart =  Highcharts.chart('directorcontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
        }
        if (id === 'actor') {
                actor.style.display = 'block';
                $.fn.dataTable.ext.errMode = 'throw';
                var actorName = $("#sjw-search-actor").val();
            if (mvActortb == null) {
                mvActortb = $('#actor').DataTable({
                    ajax: {
                        type: "post",
                        url: "/movie/actor/search?actorName=" +actorName,
                        dataSrc: ""
                    },
                    columns: [
                        {data: "movieId"},
                        {data: "title"},
                        {data: "actor"},
                        {data: "releaseDate"},
                        {data: "runTime"},
                        {data: "studio"},
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
                $.ajax({
                    url: '/movie/ajax/showactor',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(actorchart!==undefined){
                            actorchart.destroy();
                        }
                        actorchart =  Highcharts.chart('actorcontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
            else {
                mvActortb.ajax.url("/movie/actor/search?actorName=" + actorName).load();
                $.ajax({
                    url: '/movie/ajax/showactor',
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        if(actorchart!==undefined){
                            actorchart.destroy();
                        }
                        actorchart =  Highcharts.chart('actorcontainer',{
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: '两种模型执行时间比较'
                            },
                            data: {
                                columns: [
                                    [null, '执行时间'], // 分类
                                    ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                    ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                ]
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
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 's ' + this.point.name

                                            .toLowerCase();
                                }
                            },
                            plotOptions: {
                                column: {
                                    dataLabels: {
                                        enabled: true, // dataLabels设为true
                                        style: {
                                            color: '#42abf8'
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
               }
            }
        if (id === 'category') {
                category.style.display = 'block';
                $.fn.dataTable.ext.errMode = 'throw';
                var typename = $("#sjw-search-category").val();
                if (mvTypetb == null) {
                    mvTypetb = $('#category').DataTable({
                        ajax: {
                            type: "post",
                            url: "/movie/genere/search?genere=" + typename,
                            dataSrc: ""
                        },
                        columns: [
                            {data: "movieId"},
                            {data: "title"},
                            {data: "type"},
                            {data: "releaseDate"},
                            {data: "runTime"},
                            {data: "studio"},
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
                    $.ajax({
                        url: '/movie/ajax/showcategory',
                        dataSrc: '',
                        success: function (data) {

                            var dataSrc;
                            dataSrc = data[0];
                            if(categorychart!==undefined){
                                categorychart.destroy();
                            }
                            categorychart =  Highcharts.chart('categorycontainer',{
                                chart: {
                                    type: 'column'
                                },
                                title: {
                                    text: '两种模型执行时间比较'
                                },
                                data: {
                                    columns: [
                                        [null, '执行时间'], // 分类
                                        ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                        ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                    ]
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
                                        return '<b>' + this.series.name

                                            + '</b><br/>' +
                                            this.point.y + 's ' + this.point.name

                                                .toLowerCase();
                                    }
                                },
                                plotOptions: {
                                    column: {
                                        dataLabels: {
                                            enabled: true, // dataLabels设为true
                                            style: {
                                                color: '#42abf8'
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    });
                }
                else {
                    mvTypetb.ajax.url("/movie/genere/search?genere=" + typename).load();
                    $.ajax({
                        url: '/movie/ajax/showcategory',
                        dataSrc: '',
                        success: function (data) {

                            var dataSrc;
                            dataSrc = data[0];
                            if(categorychart!==undefined){
                                categorychart.destroy();
                            }
                            categorychart =  Highcharts.chart('categorycontainer',{
                                chart: {
                                    type: 'column'
                                },
                                title: {
                                    text: '两种模型执行时间比较'
                                },
                                data: {
                                    columns: [
                                        [null, '执行时间'], // 分类
                                        ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                        ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                                    ]
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
                                        return '<b>' + this.series.name

                                            + '</b><br/>' +
                                            this.point.y + 's ' + this.point.name

                                                .toLowerCase();
                                    }
                                },
                                plotOptions: {
                                    column: {
                                        dataLabels: {
                                            enabled: true, // dataLabels设为true
                                            style: {
                                                color: '#42abf8'
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    });
                }

            }
        if (id === 'combination') {
            combination.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
            var cdate = document.getElementById('cdate').value;
            var cname = document.getElementById('cname').value;
            var cactor = document.getElementById('cactor').value;
            var cdirector = document.getElementById('cdirector').value;
            var cgenre = document.getElementById('cgenre').value;
            $('#combination').DataTable({
                    ajax: {
                        url: '/movie/ajax/showcombination',
                        data : {
                            "date":cdate,
                            "name":cname,
                            "actor":cactor,
                            "director":cdirector,
                            "genre":cgenre
                        }
                    },
                    columns: [{data: "movieName"},
                        {data: "releaseTime"},
                        {data: "genre"},
                        {data: "director"},
                        {data: "actor"},
                        {data: "edition"}],
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
            $.ajax({
                url: '/movie/ajax/showcombination',
                dataSrc: '',
                success: function (data) {

                    var dataSrc;
                    dataSrc = data[0];
                    if(combinationchart!==undefined){
                        combinationchart.destroy();
                    }
                    combinationchart =  Highcharts.chart('conbinationcontainer',{
                        chart: {
                            type: 'column'
                        },
                        title: {
                            text: '两种模型执行时间比较'
                        },
                        data: {
                            columns: [
                                [null, '执行时间'], // 分类
                                ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                            ]
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
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name

                                        .toLowerCase();
                            }
                        },
                        plotOptions: {
                            column: {
                                dataLabels: {
                                    enabled: true, // dataLabels设为true
                                    style: {
                                        color: '#42abf8'
                                    }
                                }
                            }
                        }
                    });
                }

            });
            }
        if (id === 'comment') {
                comment.style.display = 'block';
                $.fn.dataTable.ext.errMode = 'throw';
                $('#comment').DataTable({
                    ajax: {
                        url: '/movie/ajax/showcomment',
                        dataSrc: ''
                    },
                    columns: [{data: "movieName"},
                        {data: "releaseTime"},
                        {data: "genre"},
                        {data: "director"},
                        {data: "actor"},
                        {data: "edition"}],
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
            $.ajax({
                url: '/movie/ajax/showcomment',
                dataSrc: '',
                success: function (data) {

                    var dataSrc;
                    dataSrc = data[0];
                    if(commentchart!==undefined){
                        commentchart.destroy();
                    }
                    commentchart =  Highcharts.chart('commentcontainer',{
                        chart: {
                            type: 'column'
                        },
                        title: {
                            text: '两种模型执行时间比较'
                        },
                        data: {
                            columns: [
                                [null, '执行时间'], // 分类
                                ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
                                ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
                            ]
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
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name

                                        .toLowerCase();
                            }
                        },
                        plotOptions: {
                            column: {
                                dataLabels: {
                                    enabled: true, // dataLabels设为true
                                    style: {
                                        color: '#42abf8'
                                    }
                                }
                            }
                        }
                    });
                }
            });
            }
        }
var seasonArray,dayArray,monthArray,date,year,dateType;
function timeChoice() {
    date=document.getElementById('myID').value;
    var type = document.getElementById('publicationdate');
    if(type.checked){
        dateType = 0; //0为出版日期 publicationdate 1为上映日期 releasedate
    }
    else {
        dateType=1;
    }
    if(date ==='') {
        var yearDiv = document.getElementById('myYear');
        for (var i=0;i<=2028-1888;i++)
        {
            if(yearDiv.options[i].selected){
                year = yearDiv.options[i].value;
            }
        }
        var season = document.getElementsByName('season');
        seasonArray = "";
        for (var i = 0; i < season.length; i++) {
            if (season[i].checked) {
                seasonArray += season[i].value+",";
            }
        }
        var month = document.getElementsByName('month');
        monthArray = "";
        for (var i = 0; i < month.length; i++) {
            if (month[i].checked) {
                monthArray += month[i].value+",";
            }
        }
        var day = document.getElementsByName('day');
        dayArray = "";
        for (var i = 0; i < day.length; i++) {
            if (day[i].checked) {
                dayArray += day[i].value+",";
            }
        }
    }
}