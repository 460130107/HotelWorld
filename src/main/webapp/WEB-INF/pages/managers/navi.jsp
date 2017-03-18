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
                    <li class="active"><a href="/hotels/index">客栈审批 <span class="sr-only">(current)</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/managers/bills">客栈结算</a></li>
                    <li><a href="${pageContext.request.contextPath}/managers/hotels">客栈信息</a></li>
                    <li><a href="${pageContext.request.contextPath}/managers/users">会员信息</a></li>
                    <li><a href="${pageContext.request.contextPath}/managers/finance">财务信息</a></li>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">张经理 <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</header>