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
<div class="container booking">
    <h1>七天连锁酒店</h1>
    <div>
        <form class="form-horizontal" action="${pageContext.request.contextPath}/users/submitbooking" method="post" onsubmit="return check(this)">
            <div class="form-group">
                <label class="col-sm-2 control-label">入住日期</label>
                <div class="col-sm-8 col-xs-10">
                    2017-10-03
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">离店日期</label>
                <div class="col-sm-8 col-xs-10">
                    2017-10-13
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">房间类型</label>
                <div class="col-sm-8 col-xs-10">
                    单人间
                </div>
            </div>

            <div class="form-group">
                <label for="nums" class="col-sm-2 control-label">房间数量</label>
                <div class="col-sm-8 col-xs-10">
                    <select name="nums" id="nums">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="name1" class="col-sm-2 control-label">住客姓名</label>
                <div class="col-sm-8 col-xs-10" id="nameInput">
                    <input type="text" class="name" name="name1" id="name1" placeholder="住客姓名1">
                    <input type="text" class="name" name="name2" id="name2" placeholder="住客姓名2">
                    至少填写1个住客信息
                </div>
            </div>

            <div class="form-group">
                <label for="telephone" class="col-sm-2 control-label">联系电话</label>
                <div class="col-sm-8 col-xs-10">
                    <input type="tel" class="form-control" id="telephone" name="telephone" placeholder="联系电话"/>
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">联系邮箱</label>
                <div class="col-sm-8 col-xs-10">
                    <input type="email" class="form-control" id="email" name="email" placeholder="联系邮箱"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-danger">提交订单</button>
                </div>
            </div>
        </form>
    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.js"></script>
<script>
    var numSelect=$("#nums");
    numSelect.change(nameInput);
    function nameInput() {
        var name=$("#nameInput");
        name.empty();
        var roomNum=numSelect.val();
        for(var i=1;i<=roomNum*2;i++){
            name.append('<input type="text" class="name" name="name' +i+
                '" id="name' +i+
                '" placeholder="住客姓名' +i+
                '">');
        }
        name.append("至少填写"+roomNum+"个住客信息");

    }
</script>
</body>
</html>