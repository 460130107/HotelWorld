<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>查看个人信息</title>

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
    <h1>HotelWorld 会员信息</h1>
    <hr/>
    <c:if test="${!empty userInfo}">
        <div id="info">
            <p class="alarm">会员余额不足，资格已暂停，充值后可恢复</p>
            <p><strong>会员卡号:</strong>123456</p>
            <p><strong>等级:</strong>6级</p>
            <p><strong>积分:</strong>120分</p>
            <p><strong>姓名:</strong>周小帆</p>
            <p><strong>银行账号:</strong>125435363</p>
            <p><strong>身份证号码:</strong>5456906479</p>
            <p><strong>联系电话:</strong>18234564321</p>
            <p><strong>会员卡余额:</strong>200元</p>
            <button class="btn btn-primary">激活</button>
            <button class="btn btn-primary">充值</button>
            <button id="modify" href="/users/modify" class="btn btn-primary">修改信息</button>
            <button class="btn btn-danger">取消资格</button>
        </div>

        <form:form action="/users/edit" method="post" hidden="hidden" commandName="user" role="form">
            <p><strong>会员卡号:</strong>123456</p>
            <p><strong>等级:</strong>6级</p>
            <p><strong>积分:</strong>120分</p>
            <p><strong>姓名:</strong>周小帆</p>
            <p><strong>性别:</strong>女</p>
            <p><strong>会员卡余额:</strong>200元</p>
            <div class="form-group">
                <label for="idcard">身份证:</label>
                <input type="text" id="idcard" name="idcard" placeholder="身份证"/>
            </div>
            <div class="form-group">
                <label for="bank">银行卡号:</label>
                <input type="text" id="bank" name="bank" placeholder="银行卡号"/>
            </div>

            <div class="form-group">
                <label for="telephone">联系电话:</label>
                <input type="tel" id="telephone" name="telephone" placeholder="联系电话"/>
            </div>

            <div class="form-group">
                <label for="password">密码:</label>
                <input type="password" id="password" name="password" placeholder="密码">
            </div>
            <div class="form-group">
                <button type="submit" id="submitMod" class="submit btn btn-sm btn-success">确认修改</button>
            </div>
        </form:form>
    </c:if>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>

    $("#modify").click(function () {
        showModel();
    });
    $("#submitMod").click(function () {
        submitModification();
    });
    var showModel=function () {
        $("form").removeAttr("hidden");
        $("#info").attr("hidden","hidden");
    };
    var submitModification=function () {
        $("form").attr("hidden","hidden");
        $("#info").removeAttr("hidden");
    }
</script>
</body>
</html>