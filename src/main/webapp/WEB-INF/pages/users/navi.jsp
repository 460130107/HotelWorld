<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="main-header">
    <nav class="navbar navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand"><b>Hotel</b>World</a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                    <i class="fa fa-bars"></i>
                </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/uses/index">首页 <span class="sr-only">(current)</span></a></li>
                    <%--<li><a href="/uses/index">预订酒店</a></li>--%>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/users/account">个人信息</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">订单信息 <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/users/bookHistory">预订记录</a></li>
                            <li><a href="/users/checkinHistory">入住记录</a></li>
                            <li><a href="/users/summary">消费统计</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">周小帆 <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</header>