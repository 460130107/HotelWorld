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
                        <li>单人间</li>
                        <li>双人间</li>
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
            <div id="room" class="panel-collapse collapse">
                <div class="panel-body">
                    <div class="roomDetail">
                        <h5>单人间</h5>
                        <ul>
                            <li>305</li>
                            <li>306</li>
                            <li>307</li>
                            <li>308</li>
                            <li>309</li>
                            <li>310</li>
                            <li>311</li>
                        </ul>
                    </div>
                    <div>
                        <select>
                            <option>单人间</option>
                            <option>双人间</option>
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
            <div id="new-plan" class="panel-collapse collapse">
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
                            <option>单人间</option>
                            <option>双人间</option>
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
                        酒店计划 <i class="fa fa-plus"></i>
                    </a>
                </h4>
            </div>
            <div id="hotel-plan" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>房间类型</th>
                            <th>开始日期</th>
                            <th>结束日期</th>
                            <th>房间价格</th>
                            <th>房间数量</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>单人间</td>
                            <td>2017-01-03</td>
                            <td>2017-01-05</td>
                            <td>220¥</td>
                            <td>30</td>
                        </tr>
                        <tr>
                            <td>单人间</td>
                            <td>2017-01-03</td>
                            <td>2017-01-05</td>
                            <td>220¥</td>
                            <td>30</td>
                        </tr>
                        <tr>
                            <td>单人间</td>
                            <td>2017-01-03</td>
                            <td>2017-01-05</td>
                            <td>220¥</td>
                            <td>30</td>
                        </tr>
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
        // $('.panel-collapse').collapse('hide');
    });
</script>
</body>
</html>