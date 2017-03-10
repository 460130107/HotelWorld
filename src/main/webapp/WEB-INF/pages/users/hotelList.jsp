<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>酒店预订</title>

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
<div class="container">
    <h1>HotelWorld 酒店预订</h1>
    <div class="row">
        <div class="col-sm-6">
            <h2>七天连锁酒店</h2>
            <p>地点：南京市鼓楼区汉口路222号</p>
        </div>
        <div class="col-sm-3 roomSelect">
            <select>
                <option value="标间">标间</option>
                <option value="双人房">双人房</option>
            </select>
            价格：100／晚
        </div>
        <div class="col-sm-3">
            <a href="/users/hotel/1" class="btn btn-primary btn-sm">查看详情</a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <h2>如家</h2>
            <p>地点：南京市鼓楼区汉口路222号</p>
        </div>
        <div class="col-sm-3 roomSelect">
            <select>
                <option value="标间">标间</option>
                <option value="双人房">双人房</option>
            </select>
            价格：100／晚
        </div>
        <div class="col-sm-3">
            <a href="/users/hotel/2" class="btn btn-primary btn-sm">查看详情</a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <h2>莫泰</h2>
            <p>地点：南京市鼓楼区汉口路222号</p>
        </div>
        <div class="col-sm-3 roomSelect">
            <select>
                <option value="标间">标间</option>
                <option value="双人房">双人房</option>
            </select>
            价格：100／晚
        </div>
        <div class="col-sm-3">
            <a href="/users/hotel/3" class="btn btn-primary btn-sm">查看详情</a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <h2>希尔顿酒店</h2>
            <p>地点：南京市鼓楼区汉口路222号</p>
        </div>
        <div class="col-sm-3 roomSelect">
            <select>
                <option value="标间">标间</option>
                <option value="双人房">双人房</option>
            </select>
            价格：100／晚
        </div>
        <div class="col-sm-3">
            <a href="/users/hotel/4" class="btn btn-primary btn-sm">查看详情</a>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>