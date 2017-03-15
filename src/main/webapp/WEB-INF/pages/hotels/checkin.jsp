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
                <input type="date" name="inTime" id="start"/>
                <input type="date" name="outTime" id="end"/>
                <button id="search" class="btn btn-primary btn-sm">查找</button>
            </div>
            <div>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>房型</th>
                            <th>房价</th>
                            <th>房间数量</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>

            <div>
                <select name="roomType" id="roomType">

                </select>
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
<script src="${pageContext.request.contextPath}/js/moment.js"></script>
<script>

    $.focusNav(1);
    var $end = $("#end"),
        $start = $("#start"),
        $search=$('#search'),
        $roomTbody=$('.add-panel tbody'),
        $roomTypeSelect=$('select#roomType');
        $addRoomBook=$('#addRoomBook');
        $bookTbody=$('.list-panel tbody');
        checkinList=[];


    initDate();
    getRooms();

    $addRoomBook.click(addBookItem);

    function initDate() {
        $end.val(moment().add(1,'days').format('YYYY-MM-DD'));
        $end.attr("min",moment().add(1,'days').format());
        $start.val(moment().format('YYYY-MM-DD'));
        $start.attr("min",moment().format());
    }

    function getRooms() {

        var data={
            id:-1,
            start:$start.val(),
            end:$end.val()
        };
        $.ajax({
            type: "GET",
            url: "/json/hotel/getSpareRoom",
            data: data,
            success: mountRoom,
            error: handleError
        });
    }
    function handleError() {
        console.log("error");
    }

    function mountRoom(msg) {
        msg=JSON.parse(msg);
        var roomList=msg.rooms;
        for (var roomtype in roomList){
            var rooms=roomList[roomtype];
            var $tr=$('<tr/>');
            var $tdName=$('<td/>');
            var $tdPrice=$('<td/>');
            var $tdNum=$('<td/>');

            $tdName.text(roomtype);
            $tdNum.text(rooms.list.length);

            if(rooms.price<0)
                $tdPrice.text("暂时无价格");
            else {
                $tdPrice.text(rooms.price);
            }

            var $option=$('<option/>');
            $option.text(roomtype);
            $option.attr('value',rooms.id);
            $roomTypeSelect.append($option);

            $tr.append($tdName);
            $tr.append($tdPrice);
            $tr.append($tdNum);
            $roomTbody.append($tr);
        }
    }

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

    $addRoomBook.click(function (e) {
//        location.href="/hotels/index";
    });

    function addBookItem(e) {
        var $tr=$('<tr/>');
        var tdArr=[];
        var data={};
        for (var i=0;i<7;i++){
            tdArr[i]=$('<td/>');
        }

        $('.add-panel input').each(function (index,item) {
            data[item.name]=item.value;
            if(index<2){
                tdArr[index].text(item.value);
            }else {
                tdArr[index+1].text(item.value);
            }
        });
        data.roomTypeId=$roomTypeSelect.val();
        data.roomTypeName=$roomTypeSelect.find('option:selected').text();
        checkinList.push(data);
        tdArr[2].text(data.roomTypeName);
        for (var i=0;i<7;i++){
            $tr.append(tdArr[i]);
        }
        $bookTbody.append($tr);

    }


</script>
</body>
</html>