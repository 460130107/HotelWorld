<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>订单记录</title>

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
<div class="container hotel">
    <h1>订单记录</h1>
    <table class="table table-bordered table-striped">
        <tr>
            <th>下单时间</th>
            <th>入住时间</th>
            <th>离店时间</th>
            <th>房间数量</th>
            <th>住客姓名</th>
            <th>联系电话</th>
            <th>联系邮箱</th>
            <th>状态</th>
        </tr>
        <tr>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2</td>
            <td>张三，李四，周小帆</td>
            <td>15902283998</td>
            <td>无</td>
            <td><button class="btn btn-sm btn-primary">取消订单</button></td>
        </tr>
        <tr>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2</td>
            <td>张三，李四，周小帆</td>
            <td>15902283998</td>
            <td>无</td>
            <td><button class="btn btn-sm btn-primary">取消订单</button></td>
        </tr>
        <tr>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2</td>
            <td>张三，李四，周小帆</td>
            <td>15902283998</td>
            <td>无</td>
            <td>已入住</td>
        </tr>
        <tr>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2017-01-14</td>
            <td>2</td>
            <td>张三，李四，周小帆,周小帆,周小帆,周小帆,周小帆</td>
            <td>15902283998</td>
            <td>无</td>
            <td>已入住</td>
        </tr>
    </table>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>