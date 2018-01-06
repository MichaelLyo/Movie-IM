<!--不同界面展示-->
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

var timechart,namechart,directorchart,actorchart,categorychart,commentchart,combinationchart; //柱状图
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
            $.ajax({
                url: '/movie/ajax/showtime',
                dataSrc: '',
                success: function (data) {
                    
                    var dataSrc;
                    dataSrc = data[0];
                    if(timechart!==undefined){
                        timechart.destroy();
                    }
                    timechart =  Highcharts.chart('container',{
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
                                return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name.toLowerCase();
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
            var dttime;
            dttime = $('#time').DataTable({
                    ajax: {
                        url: '/movie/ajax/showtime',
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
                }
            );
        }
        if (id === 'name') {
            name.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
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
                                return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name.toLowerCase();
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
            var dtname;
            dtname = $('#name').DataTable({
                    ajax: {
                        url: '/movie/ajax/showmovieName',
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
                }
            );
        }
        if (id === 'director') {
            director.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
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
                                return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name.toLowerCase();
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
            var dtdirector;
            dtdirector = $('#director').DataTable({
                    ajax: {
                        url: '/movie/ajax/showdirector',
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
                }
            );
        }
        if (id === 'actor') {
            actor.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
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
                                return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name.toLowerCase();
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
            var dtactor;
            dtactor = $('#actor').DataTable({
                    ajax: {
                        url: '/movie/ajax/showactor',
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
                }
            );
        }
        if (id === 'category') {
            category.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
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
                                return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name.toLowerCase();
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
            var dtcategory;
            dtcategory = $('#category').DataTable({
                    ajax: {
                        url: '/movie/ajax/showcategory',
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
                }
            );
        }
        if (id === 'combination') {
            combination.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
            $.ajax({
                url: '/movie/ajax/showcombination',
                dataSrc: '',
                success: function (data) {
                    var dataSrc;
                    dataSrc = data[0];
                    if(combinationchart!==undefined){
                        combinationchart.destroy();
                    }
                    combinationchart=  Highcharts.chart('combinationcontainer',{
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
                                return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name.toLowerCase();
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
            var dtcombination;
            dtcombination = $('#combination').DataTable({
                    ajax: {
                        url: '/movie/ajax/showcombination',
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
                }
            );
        }
        if (id === 'comment') {
            comment.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
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
                                return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + 's ' + this.point.name.toLowerCase();
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
            var dtcomment;
            dtcomment = $('#comment').DataTable({
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
                }
            );
        }
    }

