<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-hotel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="checkin">
<%@ include file="asides.jsp"%>
<div class="container">
    <%@ include file="header.jsp"%>
    <main class="panel-group">
        <div class="panel-heading" data-toggle="collapse" data-target="#room-type">
            <h4 class="panel-title">
                <a>
                    用户入住
                </a>
            </h4>
            <div>
                <span>会员登录</span>
                <input type="text" name="userId" placeholder="会员卡号">
                <a id="checkUser" class="fa fa-check"></a>
            </div>
        </div>

        <div class="panel-body add-panel">
            <div>
                <input type="date" name="start" id="start"/>
                <input type="date" name="end" id="end"/>
                <select name="roomType" id="roomType">
                    <option value="单人房">单人房</option>
                    <option value="双人房">双人房</option>
                    <option value="大床房">大床房</option>
                </select>
                <select name="nums" id="nums">
                    <option value="1">301</option>
                    <option value="2">302</option>
                    <option value="3">303</option>
                    <option value="4">304</option>
                    <option value="5">305</option>
                </select>
            </div>
            <div>
                <input type="text" name="name1" id="name1" placeholder="姓名">
                <input type="text" name="idcard1" id="idcard1" placeholder="身份证号">
                <input type="text" name="name2" id="name2" placeholder="姓名">
                    <input type="text" name="idcard2" id="idcard2" placeholder="身份证号">
                <a id="addRoomBook" class="fa fa-check"></a>
                <a id="cancelRoomBook" class="fa fa-close"></a>
            </div>
        </div>

        <div class="panel-body list-panel">
            <div>
                <span>总价：1000元</span>
                <button class="btn btn-sm btn-primary" id="cashPay">现金付款
                </button>
                <button class="btn btn-sm btn-primary" id="cardPay">会员卡付款</button>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                    <th>入住日期</th>
                    <th>离店日期</th>
                    <th>房间类型</th>
                    <th>房间号</th>
                    <th>住客姓名1</th>
                    <th>住客身份证1</th>
                    <th>住客姓名2</th>
                    <th>住客身份证2</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
        </div>

    </main>
</div>



<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

<script>

    $.focusNav(1);

    $("#cashPay").click(function () {
        alert("成功入住");
        location.href="/hotels/history";
    });

    var counter=(function(){
        var x=1;
        return {
            add:function () {
                x++;
                return x;
            },
            get:function () {
                return x;
            }
        };
    })();

    $("#add").click(function (e) {
        $("table").append('<tr id="room' +counter.add()+
            '">' +'<td><input type="date" name="start" id="start"/></td>'+
            '<td><input type="date" name="end" id="end"/></td>'+
            '<td> ' +
            '<select name="roomType" id="roomType"> ' +
            '<option value="单人房">单人房</option> ' +
            '<option value="双人房">双人房</option> ' +
            '<option value="大床房">大床房</option> ' +
            '</select>'+
            '</td>'+
            '<td> ' +
            '<select name="nums" id="nums">' +
            '<option value="1">301</option>' +
            '<option value="2">302</option>' +
            '<option value="3">303</option>' +
            '<option value="4">304</option><option value="5">305</option></select>' +
            '</td>'+
            '<td><input type="text" name="name1" id="name1" placeholder="姓名"></td>' +
            '<td><input type="text" name="idcard1" id="idcard1" placeholder="身份证号"></td>' +
            '<td><input type="text" name="name2" id="name2" placeholder="姓名"></td>' +
            '<td><input type="text" name="idcard2" id="idcard2" placeholder="身份证号"></td>'+
            '<td><button class="btn btn-primary btn-sm delete" id="' +counter.get()+
            '">删除</button></td>'+
            '</tr>');

//        location.href="/hotels/index";
    });

    $("table").on("click",".delete",function (e) {
        var num=e.target.id;
        $('#room'+num).remove();
    });

//    $("table").on("click",".delete",function (e) {
//        alert(e.target.id);
//    })







</script>
</body>
</html>