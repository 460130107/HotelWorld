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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@ include file="navi.jsp"%>
<div class="container hotel">
    <h1>${hotel.name}</h1>
    <div class="col-sm-6">
        <img src="${pageContext.request.contextPath}/img/hotel1.jpg"/>
    </div>
    <div class="col-sm-6">
        <div class="time">
            <label for="start">入住日期</label>
            <input type="date" name="start" id="start"/>

            <label for="end">离店日期</label>
            <input type="date" name="end" id="end" />
            <button id="search" class="btn btn-primary btn-sm">搜索</button>
        </div>

        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>房型</th>
                    <th>房价</th>
                    <th>房间数量</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.js"></script>
<script src="${pageContext.request.contextPath}/js/stdSubmit.js"></script>
<script>
    $(function(){
        var $end = $("#end"),
            $start = $("#start"),
            $table=$('table'),
            $tbody=$table.find('tbody'),
            $search=$('#search'),
            id=window.location.pathname.split("/").pop();

        initDate();
        getRooms();

        $tbody.on('click','button.book',booking);

        $search.click(function () {
            $tbody.empty();
            getRooms();
        });

        function initDate() {
            $end.val(moment().add(1,'days').format('YYYY-MM-DD'));
            $end.attr("min",moment().add(1,'days').format());
            $start.val(moment().format('YYYY-MM-DD'));
            $start.attr("min",moment().format());
        }

        function booking(e) {
            var data={
                start:$start.val(),
                end:$end.val(),
                hotelId:id,
                price:e.target.parentNode.previousSibling.previousSibling.innerHTML,
                roomNum:e.target.parentNode.previousSibling.innerHTML,
                typeId:e.target.id,
                typeName:e.target.name
            };
            $.stdGet("${pageContext.request.contextPath}/users/booking",data);
        }


        function getRooms() {

            var data={
                id: id,
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
        function mountRoom(msg) {
            msg=JSON.parse(msg);
            var roomList=msg.rooms;
            for (var roomtype in roomList){
                var rooms=roomList[roomtype];
                var $tr=$('<tr/>');
                var $tdName=$('<td/>');
                var $tdPrice=$('<td/>');
                var $tdNum=$('<td/>');
                var $tdBut=$('<td></td>');
                var $but=$('<button class="book btn btn-primary btn-sm">预订</button>');
                $but.attr('id',rooms.id);
                $but.attr('name',roomtype);

                $tdName.text(roomtype);
                $tdNum.text(rooms.list.length);

                if(rooms.price<0){
                    $tdPrice.text("暂时无价格");
                    $but.addClass("disabled");
                }else if (rooms.list.length<1){
                    $but.addClass('disabled');
                }
                else {
                    $tdPrice.text(rooms.price);
                }
                $tdBut.append($but);
                $tr.append($tdName);
                $tr.append($tdPrice);
                $tr.append($tdNum);
                $tr.append($tdBut);
                $tbody.append($tr);
            }
        }
        
        function handleError() {
            console.log("error");
        }
        


    });


</script>
</body>
</html>