<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>

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
<body class="index">
<div class="container login">
    <div class="login-pane">
        <h1>登陆</h1>
        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#user" data-toggle="tab">会员</a>
            </li>
            <li><a href="#hotel" data-toggle="tab">客栈</a></li>
            <li><a href="#manager" data-toggle="tab">经理</a></li>

        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="user">
                <c:if test="${!empty user.error}">
                   <div class="error-tips">${user.error}</div>
                </c:if>
                <form:form action="/users/login" method="post" commandName="user" role="form">
                    <div class="form-group">
                        <input type="number" id="id" class="form-control" name="id" placeholder="会员卡号">
                    </div>
                    <div class="form-group">
                        <input type="password" id="psw" class="form-control" name="psw" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-sm btn-primary">登陆</button>
                        <a href="/users/add" class="right">注册会员</a>
                    </div>
                </form:form>
            </div>
            <div class="tab-pane fade" id="hotel">
                <form:form action="/hotels/login" method="post" commandName="hotel" role="form">
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="客栈编号">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-sm btn-primary">登陆</button>
                        <a href="/hotels/add" class="right">注册客栈</a>
                    </div>
                </form:form>
            </div>
            <div class="tab-pane fade" id="manager">
                <form:form action="/managers/login" method="post" commandName="man" role="form">
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="经理姓名">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-sm btn-primary">登陆</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>