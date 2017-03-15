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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-hotel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="bookList">
<%@ include file="asides.jsp"%>
<div class="container">
    <%@ include file="header.jsp"%>
    <main class="panel-group">
            
        <div class="panel-body">
            <div><h4>预订记录</h4></div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>房间类型</th>
                        <th>住客姓名</th>
                        <th>总价</th>
                        <th>办理入住</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${bookingList}" var="booking">
                        <tr>
                            <td>${booking.roomTypeName}</td>
                            <td>${booking.nameinfo}</td>
                            <td>${booking.price}</td>
                            <td id="${booking.id}">
                                <button class="bookToCheckin btn btn-sm btn-primary">办理入住</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="panel-body checkinInfo" style="display: none">
            <div class="idCardInput">

            </div>
            <div>
                <button id="cashpay" class="btn btn-sm btn-primary">现金支付</button>
                <button id="cardpay" class="btn btn-sm btn-primary">会员卡支付</button>
            </div>
        </div>
    </main>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/stdSubmit.js"></script>

<script>
    $.focusNav(0);

    var $table=$('table');
    var $checkinInfo=$('.checkinInfo');
    var $inputArea=$('.idCardInput');
    $table.on('click','button',requestInfo);
    $checkinInfo.on('click','button',requestCheckin);
    function requestInfo(e) {
        var target=e.target;
        var nameInfo=target.parentNode.parentNode.querySelectorAll('td')[1].innerHTML
        var nameList=nameInfo.split(' ');
        nameList.map(function (name) {
            var $label=$('<label/>');
            $label.text(name);
            var $input=$('<input type="number" placeholder="身份证号"/>');
            $inputArea.append($label);
            $inputArea.append($input);
        });
        $checkinInfo.css("display","block");
        $checkinInfo.attr("data-id",target.parentNode.id);
    }
    function requestCheckin(e) {
        var id=e.target.parentNode.parentNode.dataset.id;
        var payTypeName=e.target.id;
        var payType=0;
        if(payTypeName=="cardPay")payType=1;
        var idCards=[];
        $('.idCardInput input').each(function(index,item){
            idCards.push(item.value);
        });
        $('#'+id).text("已入住");
        $.ajax({
            type:"POST",
            url:"checkinPost",
            data:{bookingId:id,payType:payType,idCards:idCards.join(' ')},
            success:function (msg) {
                msg=JSON.parse(msg);
                $checkinInfo.css('display','none');
                $('#'+id).text("已入住");
                showAssign(msg);
            }
        });

    }
    function showAssign(msg) {
        if(msg.error){
            alert(msg.error);
            return;
        }
        else {
            var result="";
            var roomAssigns=msg.roomAssign;
            roomAssigns.map(function (item) {
                result+="房间号："+item.roomName+" 住户："+item.user1+","+item.user2;
            });


            alert(result);
        }
    }
</script>
</body>
</html>