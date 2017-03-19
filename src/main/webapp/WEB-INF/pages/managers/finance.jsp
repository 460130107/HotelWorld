<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>财务信息</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@ include file="navi.jsp"%>
<div class="container index">

    <div id="finance">

    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script>

    $(function () {
        $.getJSON('getFinanc', function (data) {
            data=formatDate(data);
            $('#finance').highcharts(getConf(data));
        });
    });
    function formatDate(data) {
        return data.map(function (item,index) {
            var date=new Date();
            date.setDate(date.getDate()-14+index);
            return [Date.UTC(date.getYear(),date.getMonth(),date.getDate()),item[1]];
        });
    }

    function getConf(data) {
        var conf={
            chart: {
                type: 'column'
            },
            title: {
                text: '近期每日收入统计'
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '收入 (元)'
                }
            },
            legend: {
                enabled: false
            },
            credits: {
                enabled: false
            },
            tooltip: {
                dateTimeLabelFormats: {
                    millisecond: '%Y-%m-%d',
                    second: '%Y-%m-%d',
                    minute: '%Y-%m-%d',
                    hour: '%Y-%m-%d',
                    day: '%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                },
                pointFormat: '收入<b>{point.y} </b>',
                valueSuffix: ' 元'
            },

            series: [{
                type: 'area',
                name: '收入',
                data: data,
                pointInterval: 24 * 3600 * 1000 // one day
            }]
        };
        return conf;
    }


</script>

</body>
</html>