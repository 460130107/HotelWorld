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
<body class="account">
<%@ include file="asides.jsp"%>
<div class="container">
    <%@ include file="header.jsp"%>
    <main class="panel-group">
        <c:if test="${!empty hotelInfo}">
            <div class="info">
                <c:if test="${hotelInfo.approved==0}">
                    <p class="alarm">未审批，请耐心等待</p>
                </c:if>
                <c:if test="${hotelInfo.approved==2}">
                    <p class="alarm">审批未通过</p>
                </c:if>
                <div class="form-group">
                    <label for="name" class="control-label">客栈编码:</label>
                    <div>
                        ${hotelInfo.id}
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="control-label">客栈名称:</label>
                    <div>
                        <input type="text" value="${hotelInfo.name}" disabled class="form-control" id="name" name="name" placeholder="姓名"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="psw" class="control-label">账号密码</label>
                    <div>
                        <input type="password" value="${hotelInfo.psw}" disabled class="form-control" id="psw" name="psw" placeholder="账号密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="city" class="control-label">城市</label>
                    <div>
                        <select name="city" id="city" disabled class="form-control">
                            <option value="北京" ${hotelInfo.city=='北京'?'selected':''}>北京</option>
                            <option value="上海" ${hotelInfo.city=='上海'?'selected':''}>上海</option>
                            <option value="南京" ${hotelInfo.city=='南京'?'selected':''}>南京</option>
                            <option value="杭州" ${hotelInfo.city=='杭州'?'selected':''}>杭州</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="location" class="control-label">地址</label>
                    <div>
                        <input type="text" value="${hotelInfo.location}" disabled class="form-control" id="location" name="location" placeholder="地点"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description" class="control-label">简介:</label>
                    <div>
                        <textarea disabled class="form-control" id="description" name="description" placeholder="简介">${hotelInfo.description}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <button type="button" id="modify" class="submit btn btn-sm btn-success">修改</button>
                    <button type="submit" id="submit" class="submit btn btn-sm btn-success" style="display: none;">提交</button>
                    <button type="button" id="cancel" class="submit btn btn-sm btn-danger" style="display: none;">取消</button>
                </div>
            </div>
        </c:if>

    </main>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script>
    $.focusNav(3);

    $cancel=$('#cancel');
    $modify=$('#modify');
    $submit=$('#submit');
    $inputList=$('.form-control');
    $modify.click(enableEdit);
    $cancel.click(disableEdit);
    $submit.click(submitUp);

    function enableEdit(e) {
        $inputList.each(function (index,item) {
            $(this).removeAttr("disabled");
        });
        $modify.css("display","none");
        $submit.css("display","block");
        $cancel.css("display","block");
    }
    function disableEdit(e) {
        window.location.reload();
    }
    function submitUp(e) {
        var data={};
        $inputList.each(function (index,item) {
            data[$(this).attr('name')]=$(this).val();
        });
        console.log(data);
        $.ajax({
            type:"post",
            contentType:"application/json",
            url:"json/update",
            data:JSON.stringify(data),
            success:function (mes) {
                console.log(mes);
            },
            error:function () {
                alert("error");
            },
            complete:function () {
                window.location.reload();
            }

        });

    }
</script>
</body>
</html>