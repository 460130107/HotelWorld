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
    <h1>入住记录</h1>
    <table class="table table-bordered table-striped">
        <tr>
            <th>入住时间</th>
            <th>离店时间</th>
            <th>房间类型</th>
            <th>住客1姓名</th>
            <th>住客1身份证号</th>
            <th>住客2姓名</th>
            <th>住客2身份证号</th>
            <th>是否预订</th>
            <th>支付</th>
            <th>总价</th>
        </tr>
        <c:forEach items="${checkinList}" var="checkin">
            <tr>
                <td>${checkin.inTime.substring(0,10)}</td>
                <td>${checkin.outTime.substring(0,10)}</td>
                <td>${checkin.roomTypeName}</td>
                <td>${checkin.user1}</td>
                <td>${checkin.idcard1}</td>
                <td>${checkin.user2}</td>
                <td>${checkin.idcard2}</td>
                <td>${checkin.bookingId==0?"否":"是"}</td>
                <td>${checkin.payType==0?"现金":"会员卡"}</td>
                <td>${checkin.price}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>