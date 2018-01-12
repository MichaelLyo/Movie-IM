<!--不同界面展示-->
var mvNametb=null;
var mvTypetb=null;
var mvDirectortb =null;
var mvDirectortb2 = null;
var mvActortb = null;
var mvTimetb = null;
var mvLanguagetb = null;
var mvCombinationtb = null;
var mvRunTimetb = null;
var mvCommenttb = null;
var timechart,namechart,directorchart,directorchart2,actorchart,categorychart,languagechart,combinationchart,runtimechart,commentchart; //柱状图
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

}
function toggle(id){
    var time=document.getElementById('search-time');
    var name=document.getElementById('search-name');
    var director=document.getElementById('search-director');
    var actor=document.getElementById('search-actor');
    var category=document.getElementById('search-category');
    var combination=document.getElementById('search-combination');
    var language=document.getElementById('search-language');
    var runtime=document.getElementById('search-runtime');
    var comment = document.getElementById('search-comment');
    if(id==='time') {comment.style.display='none';runtime.style.display='none';language.style.display='none';combination.style.display='none';category.style.display='none';name.style.display='none';director.style.display='none';actor.style.display='none';time.style.display='block';}
    if(id==='name') {comment.style.display='none';runtime.style.display='none';language.style.display='none';combination.style.display='none';category.style.display='none';director.style.display='none';actor.style.display='none';time.style.display='none';name.style.display='block';}
    if(id==='director') {comment.style.display='none';runtime.style.display='none';language.style.display='none';combination.style.display='none';category.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';director.style.display='block';}
    if(id==='actor'){comment.style.display='none';runtime.style.display='none';language.style.display='none';combination.style.display='none';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='block';}
    if(id==='category'){comment.style.display='none';runtime.style.display='none';language.style.display='none';combination.style.display='none';category.style.display='block';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
    if(id==='combination'){comment.style.display='none';runtime.style.display='none';language.style.display='none';combination.style.display='block';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
    if(id==='language'){comment.style.display='none';runtime.style.display='none';language.style.display='block';combination.style.display='none';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
    if(id==='runtime'){comment.style.display='none';runtime.style.display='block';language.style.display='none';combination.style.display='none';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
    if(id==='comment'){comment.style.display='block';runtime.style.display='none';language.style.display='none';combination.style.display='none';category.style.display='none';director.style.display='none';name.style.display='none';time.style.display='none';actor.style.display='none';}
}
function Table(id,type) {
    var time = document.getElementById('timeTable');
    var name = document.getElementById('nameTable');
    var director = document.getElementById('directorTable');
    var director2 = document.getElementById('directorTable2');
    var actor = document.getElementById('actorTable');
    var category = document.getElementById('categoryTable');
    var combination = document.getElementById('combinationTable');
    var language = document.getElementById('languageTable');
    var runtime = document.getElementById('runtimeTable');
    var comment = document.getElementById('commentTable');

    if (id === 'time') {
        time.style.display = 'block';
        $.fn.dataTable.ext.errMode = 'throw';
        timeChoice();
        if (mvTimetb === null) {
            mvTimetb = $('#time').DataTable({
                ajax: {
                    type: "post",
                    url: '/movie/time/search',
                    data: {
                        "year": String(year),
                        "month": String(monthArray),
                        "dateType": String(dateType),
                        "date": String(date),
                        "season": String(seasonArray)
                    },
                    dataSrc: ""
                },
                columns: [
                    {data: "title",width:'30%'},
                    {data: "publicationDate",width:'10%'},
                    {data: "releaseDate",width:'10%'},
                    {data: "runTime",width:'10%'},
                    {data: "director"},
                    {data: "formatName"}
                ],
                "bProcessing": true,
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
                url: '/movie/multiple/showtime',
                data: {
                    "year": String(year),
                    "month": String(monthArray),
                    "dateType": String(dateType),
                    "date": String(date),
                    "season": String(seasonArray)
                },
                dataSrc: '',
                success: function (data) {

                    var dataSrc;
                    dataSrc = data[0];
                    $('#timerelation').text(dataSrc.relation);
                    $('#timemix').text(dataSrc.mix);
                    if (timechart !== undefined) {
                        timechart.destroy();
                    }
                    timechart = Highcharts.chart('container', {
                        chart: {
                            type: 'column'
                        },
                        credits: {
                            enabled: false
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
                                text: '单位(ms)  '
                                
                            }
                        },
                        tooltip: {
                            formatter: function () {
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 'ms ' + this.point.name

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
            mvTimetb.destroy();
            mvTimetb = $('#time').DataTable({
                ajax: {
                    type: "post",
                    url: '/movie/time/search',
                    data: {
                        "year": String(year),
                        "month": String(monthArray),
                        "dateType": String(dateType),
                        "date": String(date),
                        "season": String(seasonArray)
                    },
                    dataSrc: ""
                },
                columns: [
                    {data: "title",width:'30%'},
                    {data: "publicationDate",width:'10%'},
                    {data: "releaseDate",width:'10%'},
                    {data: "runTime",width:'10%'},
                    {data: "director"},
                    {data: "formatName"}
                ],
                "bProcessing": true,
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
                url: '/movie/multiple/showtime',
                dataSrc: '',
                success: function (data) {
                    var dataSrc;
                    dataSrc = data[0];
                    $('#timerelation').text(dataSrc.relation);
                    $('#timemix').text(dataSrc.mix);
                    if (timechart !== undefined) {
                        timechart.destroy();
                    }
                    timechart = Highcharts.chart('container', {
                        chart: {
                            type: 'column'
                        },
                        credits: {
                            enabled: false
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
                                text: '单位(ms)  '
                                
                            }
                        },
                        tooltip: {
                            formatter: function () {
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 'ms ' + this.point.name

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
        if (mvNametb === null) {
            mvNametb = $('#name').DataTable({
                ajax: {
                    type: "post",
                    url: "/movie/name/search?name=" + moviename,
                    dataSrc: ""
                },
                columns: [
                    {data: "title"},
                    {data: "releaseDate", width: "20%"},
                    {data: "duration",width: "10%"},
                    {data: "director"},
                    {data: "actor"},
                    {data:"studio"}
                ],
                "bPaginage": true,
                "bProcessing": true,
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
            // $.ajax({
            //     url: '/movie/ajax/showmovieName',
            //     dataSrc: '',
            //     success: function (data) {
            //
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#namerelation').text(dataSrc.relation);
            //         $('#namemix').text(dataSrc.mix);
            //         if (namechart !== undefined) {
            //             namechart.destroy();
            //         }
            //         namechart = Highcharts.chart('namecontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }
        else {
            mvNametb.ajax.url("/movie/name/search?name=" + moviename).load();
            // $.ajax({
            //     url: '/movie/ajax/showmovieName',
            //     dataSrc: '',
            //     success: function (data) {
            //
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#namerelation').text(dataSrc.relation);
            //         $('#namemix').text(dataSrc.mix);
            //         if (namechart !== undefined) {
            //             namechart.destroy();
            //         }
            //         namechart = Highcharts.chart('namecontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }
    }
    if (id === 'director') {
        director.style.display = 'block';
        $.fn.dataTable.ext.errMode = 'throw';
        var direcorName = $("#sjw-search-director").val();
        if (mvDirectortb === null) {
            mvDirectortb = $('#director').DataTable({
                ajax: {
                    type: "post",
                    url: "/movie/director/search?directorName=" + direcorName,
                    dataSrc: ""

                },
                columns: [
                    {data: "title",width:"30%"},
                    {data: "director"},
                    {data: "releaseDate"},
                    {data: "runTime"},
                    {data:"formatName"}
                ],
                "bPaginage": true,
                "bProcessing": true,
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
                    $('#directorrelation').text(dataSrc.relation);
                    $('#directormix').text(dataSrc.mix);
                    if (directorchart !== undefined) {
                        directorchart.destroy();
                    }
                    directorchart = Highcharts.chart('directorcontainer', {
                        chart: {
                            type: 'column'
                        },
                        credits: {
                            enabled: false
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
                            title: {
                                text: '单位(ms)'
                            }
                        },
                        tooltip: {
                            formatter: function () {
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 'ms ' + this.point.name

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
                    $('#directorrelation').text(dataSrc.relation);
                    $('#directormix').text(dataSrc.mix);
                    if (directorchart !== undefined) {
                        directorchart.destroy();
                    }
                    directorchart = Highcharts.chart('directorcontainer', {
                        chart: {
                            type: 'column'
                        },
                        credits: {
                            enabled: false
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
                                text: '单位(ms)  '
                                
                            }
                        },
                        tooltip: {
                            formatter: function () {
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 'ms ' + this.point.name

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
        director2.style.display = 'block';
        if (mvDirectortb2 === null) {
            mvDirectortb2 = $('#director2').DataTable({
                ajax: {
                    type: "post",
                    url: "/movie/director/actor?directorName=" + direcorName,

                  dataSrc: ""

                    },
                    columns: [
                        {data: "title",width:"30%"},
                        {data: "director"},
                        {data: "actor"},
                        {data: "genre"}
                    ],
                "bProcessing": true,
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
                url: '/movie/ajax/showdirectorcoactor',
                dataSrc: '',
                success: function (data) {
                    var dataSrc;
                    dataSrc = data[0];
                    $('#directorrelation2').text(dataSrc.relation);
                    $('#directormix2').text(dataSrc.mix);
                    if (directorchart2 !== undefined) {
                        directorchart2.destroy();
                    }

                    directorchart2 = Highcharts.chart('directorcontainer2', {
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
                                text: '单位(ms)',
                              
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

            mvDirectortb2.ajax.url("/movie/director/actor?directorName=" + direcorName).load();
            $.ajax({
                url: '/movie/ajax/showdirectorcoactor',
                dataSrc: '',
                success: function (data) {
                    var dataSrc;
                    dataSrc = data[0];
                    $('#directorrelation2').text(dataSrc.relation);
                    $('#directormix2').text(dataSrc.mix);
                    if (directorchart2 !== undefined) {
                        directorchart2.destroy();
                    }

                    directorchart2 = Highcharts.chart('directorcontainer2', {
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
                                text: '单位(ms)'

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
        var url;

        if (type === 'lead')
            url = "/movie/actor/starring?actorName=";
        if (type === 'none')
            url = "/movie/actor/search?actorName=";
        if (mvActortb === null) {

            mvActortb = $('#actor').DataTable({
                ajax: {
                    type: "post",
                    url: url + actorName,
                    dataSrc: ""
                },
                columns: [
                    {data: "title",width:"20%"},
                    {data:"actor"},
                    {data: "releaseDate",width:"10%"},
                    {data: "runTime",width:"10%"},
                    {data: "type"}
                ],
                "bProcessing": true,
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
            // $.ajax({
            //     url: '/movie/ajax/showactor',
            //     dataSrc: '',
            //     success: function (data) {
            //
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#actorrelation').text(dataSrc.relation);
            //         $('#actormix').text(dataSrc.mix);
            //         if (actorchart !== undefined) {
            //             actorchart.destroy();
            //         }
            //         actorchart = Highcharts.chart('actorcontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }
        else {
            mvActortb.ajax.url(url + actorName).load();
            // $.ajax({
            //     url: '/movie/ajax/showactor',
            //     dataSrc: '',
            //     success: function (data) {
            //
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#actorrelation').text(dataSrc.relation);
            //         $('#actormix').text(dataSrc.mix);
            //         if (actorchart !== undefined) {
            //             actorchart.destroy();
            //         }
            //         actorchart = Highcharts.chart('actorcontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }
    }
    if (id === 'category') {
        category.style.display = 'block';
        $.fn.dataTable.ext.errMode = 'throw';
        var typename = $("#sjw-search-category").val();
        if (mvTypetb === null) {
            var dataSrc;
            mvTypetb = $('#category').DataTable({
                ajax: {
                    type: "post",
                    url: "/movie/genere/search?genere=" + typename,
                    dataSrc: ""
                },
                columns: [
                    {data: "title",width:"20%"},
                    {data: "type",width:"10%"},
                    {data: "releaseDate",width:"10%"},
                    {data: "director"},
                    {data: "runTime",width:"10%"}
                ],
                "bProcessing": true,
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
            // $.ajax({
            //     url: '/movie/ajax/showcategory',
            //     dataSrc: '',
            //     success: function (data) {
            //         dataSrc = data[0];
            //         if (categorychart !== undefined) {
            //             categorychart.destroy();
            //         }
            //         $('#genrerelation').text(dataSrc.relation);
            //         $('#genremix').text(dataSrc.mix);
            //         categorychart = Highcharts.chart('categorycontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }
        else {
            mvTypetb.ajax.url("/movie/genere/search?genere=" + typename).load();
            // $.ajax({
            //     url: '/movie/ajax/showcategory',
            //     dataSrc: '',
            //     success: function (data) {
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#genrerelation').text(dataSrc.relation);
            //         $('#genremix').text(dataSrc.mix);
            //         if (categorychart !== undefined) {
            //             categorychart.destroy();
            //         }
            //         categorychart = Highcharts.chart('categorycontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }

    }
    if (id === 'language') {
        var languagename = $("#sjw-search-language").val();
        language.style.display = 'block';
        $.fn.dataTable.ext.errMode = 'throw';
        if (mvLanguagetb === null) {
            mvLanguagetb = $('#language').DataTable({
                ajax: {
                    url: '/movie/language/search?language=' + languagename,
                    dataSrc: "",
                    type: "post"
                },
                columns: [
                    {data: "title",width:"30%"},
                    {data: "language"},
                    {data: "releaseDate",width:"10%"},
                    {data: "director",width:"10%"}
                ],
                "bProcessing": true,
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
            // $.ajax({
            //     url: '/movie/ajax/showlanguage',
            //     dataSrc: '',
            //     success: function (data) {
            //
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#languagerelation').text(dataSrc.relation);
            //         $('#languagemix').text(dataSrc.mix);
            //         if (languagechart !== undefined) {
            //             languagechart.destroy();
            //         }
            //         languagechart = Highcharts.chart('languagecontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }
        else {
            mvLanguagetb.ajax.url('/movie/language/search?language=' + languagename).load();
            // $.ajax({
            //     url: '/movie/ajax/showlanguage',
            //     dataSrc: '',
            //     success: function (data) {
            //
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#languagerelation').text(dataSrc.relation);
            //         $('#languagemix').text(dataSrc.mix);
            //         if (languagechart !== undefined) {
            //             languagechart.destroy();
            //         }
            //         languagechart = Highcharts.chart('languagecontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            // });
        }
    }
    if (id === 'runtime') {
            runtime.style.display = 'block';
            $.fn.dataTable.ext.errMode = 'throw';
            var time1 = document.getElementById('time1').value;
            var time2 = document.getElementById('time2').value;
            if (mvRunTimetb === null) {
                mvRunTimetb = $('#rtime').DataTable({
                    ajax: {
                        type: "post",
                        url: "/movie/runtime/search",
                        dataSrc: "",
                        data: {
                            "time1": String(time1),
                            "time2": String(time2)
                        }
                    },
                    columns: [
                        {data: "title"},
                        {data: "runTime"},
                        {data: "publisher"},
                        {data: "releaseDate",width:"10%"}
                    ],
                    "bProcessing": true,
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
                    url: '/movie/multiple/showruntime',
                    data: {
                        "time1": String(time1),
                        "time2": String(time2)
                    },
                    dataSrc: '',

                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        $('#runtimerelation').text(dataSrc.relation);
                        $('#runtimemix').text(dataSrc.mix);
                        if (runtimechart !== undefined) {
                            runtimechart.destroy();
                        }
                        runtimechart = Highcharts.chart('runtimecontainer', {
                            chart: {
                                type: 'column'
                            },
                            credits: {
                                enabled: false
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
                                    text: '单位(ms)  '
                                    
                                }
                            },
                            tooltip: {
                                formatter: function () {
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 'ms ' + this.point.name
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
                mvRunTimetb.ajax.url("/movie/runtime/search?time1="+String(time1)+"&time2="+String(time2)).load();
                $.ajax({
                    url: '/movie/multiple/showruntime',
                    data: {
                        "time1": String(time1),
                        "time2": String(time2)
                    },
                    dataSrc: '',
                    success: function (data) {

                        var dataSrc;
                        dataSrc = data[0];
                        $('#runtimerelation').text(dataSrc.relation);
                        $('#runtimemix').text(dataSrc.mix);
                        if (runtimechart !== undefined) {
                            runtimechart.destroy();
                        }
                        runtimechart = Highcharts.chart('runtimecontainer', {
                            chart: {
                                type: 'column'
                            },
                            credits: {
                                enabled: false
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
                                    text: '单位(ms)  '
                                    
                                }
                            },
                            tooltip: {
                                formatter: function () {
                                    return '<b>' + this.series.name

                                        + '</b><br/>' +
                                        this.point.y + 'ms ' + this.point.name
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
    if (id === 'comment') {
        comment.style.display = 'block';
        $.fn.dataTable.ext.errMode = 'throw';
        var movieName = $("#sjw-search-comment").val();
        var commenturl;
        if (type === 'positive')
            commenturl = "/movie/comment/positive?movieName=";
        if (type === 'moderate')
            commenturl = "/movie/comment/moderate?movieName=";
        if (type === 'negative')
            commenturl = "/movie/comment/negative?movieName=";
        if (mvCommenttb=== null) {
            mvCommenttb = $('#comment').DataTable({
                ajax: {
                    type: "post",
                    url: commenturl+movieName,
                    dataSrc: ""
                },
                columns: [
                    {data: "title"},
                    {data: "userName",width:"10%"},
                    {data: "summary"},
                    {data: "score"},
                    {data: "helpfulness"}
                ],
                "bProcessing": true,
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
                url: '/movie/multiple/showcomment',
                dataSrc: '',
                success: function (data) {
                    var dataSrc;
                    dataSrc = data[0];
                    $('#commentrelation').text(dataSrc.relation);
                    $('#commentmix').text(dataSrc.mix);
                    if (commentchart !== undefined) {
                        commentchart.destroy();
                    }
                    commentchart = Highcharts.chart('commentcontainer', {
                        chart: {
                            type: 'column'
                        },
                        credits: {
                            enabled: false
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
                                text: '单位(ms)  '

                            }
                        },
                        tooltip: {
                            formatter: function () {
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 'ms ' + this.point.name
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
            mvCommenttb.ajax.url(commenturl + movieName).load();
            $.ajax({
                url: '/movie/ajax/showcomment',
                dataSrc: '',
                success: function (data) {

                    var dataSrc;
                    dataSrc = data[0];
                    $('#commentrelation').text(dataSrc.relation);
                    $('#commentmix').text(dataSrc.mix);
                    if (commentchart !== undefined) {
                        commentchart.destroy();
                    }
                    commentchart = Highcharts.chart('commentcontainer', {
                        chart: {
                            type: 'column'
                        },
                        credits: {
                            enabled: false
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
                                text: '单位(ms)  '

                            }
                        },
                        tooltip: {
                            formatter: function () {
                                return '<b>' + this.series.name

                                    + '</b><br/>' +
                                    this.point.y + 'ms ' + this.point.name

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
        var cdate = $("#lsl-cdate").val();
        var cname = $("#lsl-cname").val();
        var cactor = $("#lsl-cactor").val();
        var cdirector = $("#lsl-cdirector").val();
        var cgenre = $("#lsl-cgenre").val();
        if (mvCombinationtb === null) {
            mvCombinationtb = $('#combination').DataTable({
                ajax: {
                    url: '/movie/combination/search',
                    type: 'post',
                    data: {
                        "date": cdate,
                        "name": cname,
                        "actor": cactor,
                        "director": cdirector,
                        "genre": cgenre
                    },
                    dataSrc: ""

                },
                columns: [
                    {data: "title"},
                    {data: "director"},
                    {data: "actor"},
                    {data: "genre"},
                    {data: "releaseDate",width:"10%"},
                    {data: "runTime"}
                ],
                "bProcessing": true,
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
            // $.ajax({
            //     url: '/movie/multiple/showcombination',
            //     data: {
            //         "date": cdate,
            //         "name": cname,
            //         "actor": cactor,
            //         "director": cdirector,
            //         "genre": cgenre
            //     },
            //     dataSrc: '',
            //     success: function (data) {
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#combinationrelation').text(dataSrc.relation);
            //         $('#combinationmix').text(dataSrc.mix);
            //         if (combinationchart !== undefined) {
            //             combinationchart.destroy();
            //         }
            //         combinationchart = Highcharts.chart('combinationcontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            //
            // });
        }
        else {
            mvCombinationtb.destroy();
            mvCombinationtb = $('#combination').DataTable({
                ajax: {
                    url: '/movie/combination/search',
                    type: 'post',
                    data: {
                        "date": cdate,
                        "name": cname,
                        "actor": cactor,
                        "director": cdirector,
                        "genre": cgenre
                    },
                    dataSrc: ""

                },
                columns: [
                    {data: "title"},
                    {data: "director"},
                    {data: "actor"},
                    {data: "genre"},
                    {data: "releaseDate",width:"10%"},
                    {data: "runTime"}
                ],
                "bProcessing": true,
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
            // $.ajax({
            //     url: '/movie/multiple/showcombination',
            //     data: {
            //         "date": cdate,
            //         "name": cname,
            //         "actor": cactor,
            //         "director": cdirector,
            //         "genre": cgenre
            //     },
            //     dataSrc: '',
            //     success: function (data) {
            //
            //         var dataSrc;
            //         dataSrc = data[0];
            //         $('#combinationrelation').text(dataSrc.relation);
            //         $('#combinationmix').text(dataSrc.mix);
            //         if (combinationchart !== undefined) {
            //             combinationchart.destroy();
            //         }
            //         combinationchart = Highcharts.chart('combinationcontainer', {
            //             chart: {
            //                 type: 'column'
            //             },
            //             credits: {
            //                 enabled: false
            //             },
            //             title: {
            //
            //                 text: '两种模型执行时间比较'
            //             },
            //             data: {
            //                 columns: [
            //                     [null, '执行时间'], // 分类
            //                     ['关系型数据仓库存储模型', dataSrc.relation],           // 第一个数据列
            //                     ['混合型数据存储模型', dataSrc.mix]            // 第二个数据列
            //                 ]
            //             },
            //             yAxis: {
            //                 allowDecimals: false,
            //                 title: {
            //                     text: '单位(ms)  '
            //
            //                 }
            //             },
            //             tooltip: {
            //                 formatter: function () {
            //                     return '<b>' + this.series.name
            //
            //                         + '</b><br/>' +
            //                         this.point.y + 'ms ' + this.point.name
            //                             .toLowerCase();
            //                 }
            //             },
            //             plotOptions: {
            //                 column: {
            //                     dataLabels: {
            //                         enabled: true, // dataLabels设为true
            //                         style: {
            //                             color: '#42abf8'
            //                         }
            //                     }
            //                 }
            //             }
            //         });
            //     }
            //
            // });
        }
    }
    }
    var seasonArray,  monthArray, date, year, dateType;

function timeChoice() {
    date = document.getElementById('myID').value;
    var type = document.getElementById('publicationdate');
    if (type.checked) {
        dateType = 0; //0为出版日期 publicationdate 1为上映日期 releasedate
    }
    else {
        dateType = 1;
    }
    if (date === '') {
        var yearDiv = document.getElementById('myYear');
        for (var i = 0; i <= 2028 - 1888; i++) {
            if (yearDiv.options[i].selected) {
                year = yearDiv.options[i].value;
            }
        }


        var season = document.getElementsByName('season');
        seasonArray = [];
        for (var i = 0, j = 0; i < season.length; i++) {
            if (season[i].checked) {
                seasonArray[j] = season[i].value;
                j++;
            }
        }
        var month = document.getElementsByName('month');
        monthArray = [];
        for (var i = 0, j = 0; i < month.length; i++) {
            if (month[i].checked) {
                monthArray[j] = month[i].value;
                j++;
            }
        }
    }
}
