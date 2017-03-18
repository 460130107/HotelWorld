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
<body>
<%@ include file="navi.jsp"%>
<div class="container index">
    <div class="hotelApprove">

        <div>
            <h2>开店申请</h2>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>客栈编号</th>
                    <th>客栈名称</th>
                    <th>城市</th>
                    <th>地址</th>
                    <th>简介</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${hotels}" var="hotel">
                    <tr>
                        <td>${hotel.id}</td>
                        <td>${hotel.name}</td>
                        <td>${hotel.city}</td>
                        <td>${hotel.location}</td>
                        <td>${hotel.description}</td>
                        <td data-id="${hotel.id}">
                            <button class="btn btn-sm btn-success approve">通过</button>
                            <button class="btn btn-sm btn-warning disapprove">不通过</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>


        </table>

    </div>
    <div class="updateApprove">
        <div>
            <h2>修改申请</h2>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>客栈编号</th>
                    <th>客栈名称</th>
                    <th>银行卡号</th>
                    <th>城市</th>
                    <th>地址</th>
                    <th>简介</th>
                    <th>申请时间</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${updates}" var="update">
                <tr>
                    <td>${update.hotelId}</td>
                    <td>${update.name}</td>
                    <td>${update.bank}</td>
                    <td>${update.city}</td>
                    <td>${update.location}</td>
                    <td>${update.description}</td>
                    <td>${update.creatTime}</td>
                    <td data-id="${update.id}">
                        <button class="btn btn-sm btn-success approve">通过</button>
                        <button class="btn btn-sm btn-warning disapprove">不通过</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>

    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
    var $hTable=$('.hotelApprove table');
    var $uTable=$('.updateApprove table');
    $hTable.on('click','button.approve',approveHotel);
    $hTable.on('click','button.disapprove',disapproveHotel);
    $uTable.on('click','button.approve',approveUpdate);
    $uTable.on('click','button.disapprove',disapproveUpdate);


    function approveHotel(e) {
        var target=e.target;
        var id=target.parentNode.dataset.id;
        $.ajax({
            type:"POST",
            url:"approveHotel",
            data:{id:id},
            success:function () {
                updateStateApproved(target.parentNode);
            }
        });
    }

    function disapproveHotel(e) {
        var target=e.target;
        var id=target.parentNode.dataset.id;
        $.ajax({
            type:"POST",
            url:"disapproveHotel",
            data:{id:id},
            success:function () {
                updateStateDisapproved(target.parentNode);
            }
        });
    }

    function approveUpdate(e) {
        var target=e.target;
        var id=target.parentNode.dataset.id;
        $.ajax({
            type:"POST",
            url:"approveUpdate",
            data:{id:id},
            success:function () {
                updateStateApproved(target.parentNode);
            }
        });
    }

    function disapproveUpdate(e) {
        var target=e.target;
        var id=target.parentNode.dataset.id;
        $.ajax({
            type:"POST",
            url:"disapproveUpdate",
            data:{id:id},
            success:function () {
                updateStateDisapproved(target.parentNode);
            }
        });
    }

    function updateStateApproved(ele) {
        $(ele).text("已通过");
    }

    function updateStateDisapproved(ele) {
        $(ele).text("不通过");
    }
</script>
</body>
</html>