<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>会员信息</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <style>
        body{
            text-align: center;
        }
        .container{
            display: flex;
            justify-content: space-around;
        }
        .statis{
            width: 300px;
            height: 400px;
            border:solid 2px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
            align-items: center;
            /*justify-content: center;*/
        }
        .outer{
            width: 200px;
            height: 200px;
            background-color: rgb(124, 181, 236);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .inner{
            width: 150px;
            height: 150px;
            background-color: white;
            font-size: 50px;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@ include file="navi.jsp"%>
<h1>近30天会员统计信息</h1>

<div class="container index">


    <div class="statis">
        <h2>预订房间总数(间)</h2>
        <div class="outer">
            <div class="inner"><p>${bookNum}</p></div>
        </div>
    </div>

    <div class="statis">
        <h2>总消费金额(元)</h2>
        <div class="outer">
            <div class="inner"><p>${consumption}</p></div>
        </div>
    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>