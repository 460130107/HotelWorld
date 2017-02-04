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
<div class="container account">
    <h1>HotelWorld 酒店计划</h1>
    <table class="table table-bordered table-striped">
        <tr>
            <th>房间类型</th>
            <th>开始日期</th>
            <th>结束日期</th>
            <th>房间价格</th>
            <th>房间数量</th>
        </tr>
        <tr>
            <td>单人间</td>
            <td>2017-01-03</td>
            <td>2017-01-05</td>
            <td>220¥</td>
            <td>30</td>
        </tr>

    </table>
    <div>
        <h2>房间类型</h2>
        <ul>
            <li>单人间</li>
            <li>双人间</li>
        </ul>
        <form:form action="/hotels/addRoomType" method="post" commandName="user" role="form">
            <input type="text" name="roomType" id="roomType" placeholder="类型名称"/>
            <button id="addRoomType" class="submit btn btn-sm btn-primary">添加</button>
        </form:form>
    </div>
    <form:form action="/hotels/addRoom" method="post" commandName="user" role="form">
        <h2>添加房间</h2>
        <label class="control-label">房间类型</label>
        <select>
            <option>单人间</option>
            <option>双人间</option>
        </select>
        <input type="text" name="name" id="name" placeholder="房间名"/>
        <button id="addRoom" class="submit btn btn-sm btn-primary">添加</button>
    </form:form>
    <form:form action="/hotels/addPlan" method="post" commandName="user" role="form">
        <h2>发布计划</h2>
        <div class="form-group">
            <label for="start" class="control-label">开始日期</label>
            <input type="date" name="start" id="start"/>

            <label for="end" class="control-label">结束日期</label>
            <input type="date" name="end" id="end"/>
        </div>
        <div class="form-group">
            <label class="control-label">房间类型</label>
            <select>
                <option>单人间</option>
                <option>双人间</option>
            </select>

            <label for="price" class="control-label">房间价格</label>
            <input type="number" name="price" id="price"/>
        </div>

        <div class="form-group">
            <button type="submit" class="submit btn btn-sm btn-primary">发布</button>
        </div>
    </form:form>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>