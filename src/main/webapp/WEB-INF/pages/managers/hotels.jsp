<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>客栈信息</title>

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
    店的列表
    日均入住率，日均入住人数，
    近30天入住情况
    <div id="hotelCheckin">

    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script>
    $(function () {
        $.getJSON('getHotelStat', function (data) {
            console.log(data);
            data=format(data);
            $('#hotelCheckin').highcharts(getConf(data));

        });
    });

    function format(data) {
        return data.map(function (item) {
            return [item[0],parseInt(item[1])];
        });

    }

    function getConf(data) {
        var conf={
            chart: {
                type: 'column'
            },
            title: {
                text: '近30天各店入住统计'
            },
            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '房间 (间)'
                }
            },
            legend: {
                enabled: false
            },
            credits: {
                enabled: false
            },
            tooltip: {
                pointFormat: '入住数量: <b>{point.y} </b>',
                valueSuffix: ' 间'
            },
            series: [{
                data: data,
                dataLabels: {
                    enabled: true,
                    color: '#FFFFFF',
                    align: 'right',
                    format: '{point.y}', // one decimal
                    y: 0, // 10 pixels down from the top
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            }]
        };
        return conf;
    }


</script>
</body>
</html>