<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>酒店管理</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-hotel.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"> -->
</head>
<body class="plan">

<%@ include file="asides.jsp"%>
<div class="container">
    <%@ include file="header.jsp"%>
    <main class="panel-group" id="accordion">


        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse"
                 data-target="#room-type">
                <h4 class="panel-title">
                    <a>
                        房间类型 <i class="fa fa-plus"></i>
                    </a>
                </h4>
            </div>
            <div id="room-type" class="panel-collapse collapse in">
                <div class="panel-body">
                    <ul>
                        <c:forEach items="${roomTypes}" var="roomType">
                            <li>${roomType.roomType}</li>
                        </c:forEach>
                    </ul>
                    <div>
                        <input type="text" name="roomType" id="roomType" placeholder="添加类型"/>
                        <a id="addRoomType" class="fa fa-check"></a>
                        <a id="cancelRoomType" class="fa fa-close"></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse"
                 data-target="#room">
                <h4 class="panel-title">
                    <a>
                        客栈房间 <i class="fa fa-plus"></i>
                    </a>
                </h4>
            </div>
            <div id="room" class="panel-collapse collapse in">
                <div class="panel-body">
                    <div class="roomDetail">

                    </div>
                    <div class="addRoomForm">
                        <select>
                            <c:forEach items="${roomTypes}" var="roomType">
                                <option value="${roomType.id}">${roomType.roomType}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="name" id="name" placeholder="添加房间"/>
                        <a id="addRoom" class="fa fa-check"></a>
                        <a id="cancelRoom" class="fa fa-close"></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse"
                 data-target="#new-plan">
                <h4 class="panel-title">
                    <a>
                        发布计划 <i class="fa fa-plus"></i>
                    </a>
                </h4>
            </div>
            <div id="new-plan" class="panel-collapse collapse in">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="start" class="control-label">开始日期</label>
                        <input type="date" name="start" id="start"/>

                        <label for="end" class="control-label">结束日期</label>
                        <input type="date" name="end" id="end"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">房间类型</label>
                        <select>
                            <c:forEach items="${roomTypes}" var="roomType">
                                <option value="${roomType.id}" data-name="${roomType.roomType}">${roomType.roomType}</option>
                            </c:forEach>
                        </select>

                        <label for="price" class="control-label">房间价格</label>
                        <input type="number" name="price" id="price"/>
                        <a id="addPlan" class="fa fa-check"></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading" data-toggle="collapse"
                 data-target="#hotel-plan">
                <h4 class="panel-title">
                    <a>
                        客栈计划 <i class="fa fa-plus"></i>
                    </a>
                </h4>
            </div>
            <div id="hotel-plan" class="panel-collapse collapse in">
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>房间类型</th>
                            <th>入住日期</th>
                            <th>离店日期</th>
                            <th>房间价格</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${planList}" var="plan">
                            <tr>
                                <td>${plan.roomType}</td>
                                <td>${plan.startTime.toString().substring(0,10)}</td>
                                <td>${plan.endTime.toString().substring(0,10)}</td>
                                <td>${plan.price}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </main>
</div>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
    $(function () {
         var $roomDetail=$(".roomDetail");
         var $addRoom=$('#addRoom');
         var $addRoomType=$('#addRoomType');
         var $cancelRoom=$('#cancelRoom');
         var $cancelRoomType=$('#cancelRoomType');
         var $addPlan=$('#addPlan');

         $addRoom.click(addRoom);
         $addRoomType.click(addRoomType);
         $cancelRoom.click(cancelRoom);
         $cancelRoomType.click(cancelRoomType);
         $addPlan.click(addPlan);
         getRooms();
         function getRooms() {
             $.ajax({
                 type:"GET",
                 url:"getRoom",
                 success:function (mes) {
                     var data=JSON.parse(mes);
                     console.log(data);
                     mountRooms(data);
                 },
                 error:function () {
                     alert("error");
                 },
                 complete:function () {
                 }

             });
         }

         function mountRooms(msg) {
             for (var roomtype in msg){
                 var $div=$('<div/>');
                 var $head=$('<h5>');
                 $head.text(roomtype);
                 var $ul=$('<ul/>');
                 var rooms=msg[roomtype];
                 rooms.forEach(function (item) {
                     var $li=$('<li/>');
                     $li.text(item.name);
                     $ul.append($li);
                 });

                 $div.append($head);
                 $div.append($ul);
                 $roomDetail.append($div);
             }
         }

         function cancelRoom(e) {
             e.preventDefault();
             $('.addRoomForm input').val("");
         }

        function cancelRoomType(e) {
            e.preventDefault();
            $('#roomType').val("");
        }

        function addRoomType(e) {
            e.preventDefault();
            var $roomType=$('#roomType');
            var data={};
            data.roomType=$roomType.val();
            mountRoomType(data);
//            $.ajax({
//                type:"POST",
//                url:"addRoomType",
//                data:data,
//                success:function (msg) {
//                    $roomType.val("");
//                    mountRoomType(data);
//                }
//            });
        }

        function mountRoomType(data) {
            var $roomtype=$('#room-type ul');
            var $li=$('<li/>');
            $li.text(data.roomType);
            $roomtype.append($li);
        }
         
         function addRoom(e) {
             e.preventDefault();
             var $addRoomForm=$('.addRoomForm');
             var data={};
             data.roomTypeId=$('.addRoomForm select').val();
             data.name=$('.addRoomForm input').val();
             $.ajax({
                 type:"POST",
                 url:"addRoom",
                 data:data,
                 success:function (msg) {
                     $roomDetail.empty();
                     getRooms();
                 }
             });
         }

         function addPlan(e) {
             e.preventDefault();var data={};
             $form=$('#new-plan input');
             $form.each(function (index,item) {
                 data[item.name]=item.value;
             });
             $select=$('#new-plan select');
             data[roomTypeId]=$select.val();
             data[roomType]=$select.dataset.name;
             $.ajax({
                 type:"POST",
                 url:"addPlan",
                 data:data,
                 success:function (msg) {

                     mountPlan(data);
                 }
             });
         }

         function mountPlan(data) {
             var $tbody=$('#hotel-plan tbody');
             var $tr=$('<tr/>');
             var $tdRoomType=$('<td/>');
             var $tdStart=$('<td/>');
             var $tdEnd=$('<td/>');
             var $tdPrice=$('<td/>');
             $tdRoomType.text(data.roomType);
             $tdStart.text(data.start);
             $tdEnd.text(data.end);
             $tdPrice.text(data.price);
             $tr.append($tdRoomType);
             $tr.append($tdStart);
             $tr.append($tdEnd);
             $tr.append($tdPrice);
             $tbody.append($tr);

         }



    });
</script>
</body>
</html>