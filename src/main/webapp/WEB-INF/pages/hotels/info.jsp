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
    <h1>HotelWorld 酒店信息</h1>
    <hr/>
    <c:if test="${!empty userInfo}">
        <div id="info">
            <p><strong>识别码:</strong>654321</p>
            <p><strong>酒店名称:</strong>如家快捷酒店</p>
            <p><strong>城市:</strong>南京</p>
            <p><strong>地点:</strong>鼓楼区汉口路22号</p>
            <p><strong>描述:</strong>快捷酒店，舒适宜人</p>
            <button id="modify" href="/users/modify" class="btn btn-primary">修改信息</button>
        </div>

        <form:form action="/hotels/edit" method="post" hidden="hidden" commandName="user" role="form">
            <p><strong>识别码:</strong>654321</p>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">酒店名称:</label>
                <div class="col-sm-8 col-xs-8">
                    <input type="text" class="form-control" id="name" name="name" placeholder="姓名"/>
                </div>
            </div>
            <div class="form-group">
                <label for="city" class="col-sm-2 control-label">城市</label>
                <div class="col-sm-8 col-xs-8">
                    <select name="city" id="city">
                        <option value="北京">北京</option>
                        <option value="上海">上海</option>
                        <option value="南京">南京</option>
                        <option value="杭州">杭州</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="location" class="col-sm-2 control-label">地点</label>
                <div class="col-sm-8 col-xs-8">
                    <input type="text" class="form-control" id="location" name="location" placeholder="地点"/>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 control-label">简介:</label>
                <div class="col-sm-8 col-xs-8">
                    <textarea class="form-control" id="description" name="description" placeholder="简介"></textarea>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="submit btn btn-sm btn-success">提交</button>
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