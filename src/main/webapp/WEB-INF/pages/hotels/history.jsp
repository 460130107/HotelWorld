<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>查看酒店信息</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-hotel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="checkout">
<%@ include file="asides.jsp"%>
<div class="container">
<%@ include file="header.jsp"%>
    <main class="panel-group">
        <div class="panel-heading">
            <h3 class="panel-title">HotelWorld 入住记录</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>入住日期</th>
                        <th>退房日期</th>
                        <th>房间类型</th>
                        <th>房间号</th>
                        <th>住客信息1</th>
                        <th>住客信息2</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>2017-01-01</td>
                        <td>无</td>
                        <td>单人间</td>
                        <td>302</td>
                        <td>张三</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>2017-01-01</td>
                        <td>2017-01-03</td>
                        <td>单人间</td>
                        <td>302</td>
                        <td>张三</td>
                        <td>张三</td>
                    </tr>
                    <tr>
                        <td>2017-01-01</td>
                        <td>2017-01-03</td>
                        <td>单人间</td>
                        <td>302</td>
                        <td>张三</td>
                        <td>张三</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="panel-heading">
            <h3 class="panel-title">HotelWorld 预订记录</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                 <thead>
                    <tr>
                        <th>入住日期</th>
                        <th>退房日期</th>
                        <th>房间类型</th>
                        <th>房间数量</th>
                        <th>住客信息</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>2017-01-01</td>
                        <td>2017-01-03</td>
                        <td>单人间</td>
                        <td>3</td>
                        <td>张三</td>
                    </tr>
                    <tr>
                        <td>2017-01-01</td>
                        <td>2017-01-03</td>
                        <td>单人间</td>
                        <td>3</td>
                        <td>张三</td>
                    </tr>
                    <tr>
                        <td>2017-01-01</td>
                        <td>2017-01-03</td>
                        <td>单人间</td>
                        <td>3</td>
                        <td>张三</td>
                    </tr>
                    <tr>
                        <td>2017-01-01</td>
                        <td>2017-01-03</td>
                        <td>单人间</td>
                        <td>3</td>
                        <td>张三</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script>
    $.focusNav(5);
</script>
</body>
</html>