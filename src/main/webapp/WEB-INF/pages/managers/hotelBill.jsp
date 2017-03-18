<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>结算</title>

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
<div class="container index">
    <table class="table table-bordered table-striped">
        <tr>
            <th>会员编号</th>
            <th>房间类型</th>
            <th>入住时间</th>
            <th>金额</th>
            <th></th>
        </tr>
        <c:forEach items="${checkinList}" var="checkin">
            <tr>
                <td>${checkin.userId}</td>
                <td>${checkin.roomTypeName}</td>
                <td>${checkin.inTime.substring(0,10)}</td>
                <td>${checkin.price}</td>
                <td data-id="${checkin.id}">
                    <button class="btn btn-sm btn-success">结算</button>
                </td>
            </tr>
        </c:forEach>


    </table>


</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
    var $table=$('table');
    $table.on('click','button',payUp);

    function payUp(e) {
        var ele=e.target.parentNode;
        var id=ele.dataset.id;
        $.ajax({
            type:"POST",
            url:"payUp",
            data:{id:id},
            success:function (data) {
                data=JSON.parse(data);
                if(data.error){
                    alert(data.error);
                    return ;
                }
                $(ele).text("已清算");
            }
        });
    }
</script>
</body>
</html>