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
    <title>查看个人信息</title>

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
<div class="container account">
    <h1>HotelWorld 会员信息</h1>
    <hr/>
    <c:if test="${!empty userInfo}">
            <c:if test="${userInfo.state==0}"><p class="alarm">还未激活，请一次充值不少于1000元激活账户</p>
            </c:if>
            <c:if test="${userInfo.state==2}"><p class="alarm">会员到期一年，当前余额少于100，已暂停会员卡记录，请及时消费重新激活账户</p>
            </c:if>
            <c:if test="${userInfo.state==3}"><p class="alarm">会员已暂停超过一年，会员记录停止</p>
            </c:if>
            <p><strong>姓名:</strong>${userInfo.name}</p>
            <p><strong>会员卡号:</strong>${userInfo.id}</p>
            <p><strong>等级:</strong>${userInfo.level}级</p>
            <p><strong>积分:</strong>${userInfo.points}分</p>
            <p><strong>会员卡余额:</strong>${userInfo.balance}元</p>
            <p><strong>注册日期:</strong>${userInfo.creatTime.toString().substring(0,10)} （有效期一年）</p>
            <div class="form-group">
                <label for="idcard">身份证:</label>
                <input type="text" disabled ="true" class="form-control" id="idcard" value="${userInfo.idcard}" name="idcard" placeholder="身份证"/>
            </div>
            <div class="form-group">
                <label for="bank">银行卡号:</label>
                <input type="text" id="bank" class="form-control" disabled ="true" name="bank" value="${userInfo.bank}" placeholder="银行卡号"/>
            </div>

            <div class="form-group">
                <label for="telephone">联系电话:</label>
                <input type="tel" id="telephone" class="form-control" disabled ="true" name="phone" value="${userInfo.phone}" placeholder="联系电话"/>
            </div>

            <div class="form-group">
                <label for="password">密码:</label>
                <input type="password" class="form-control" id="password" disabled ="true" name="psw" value="${userInfo.psw}" placeholder="密码">
            </div>
            <div class="form-group">
                <button type="button" id="modify" href="/users/modify" class="btn btn-primary">修改信息</button>
                <button id="activate" class="btn btn-primary">账户充值</button>
                <button id="logOff" class="btn btn-danger">注销会员</button>
            </div>

    </c:if>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>

    $(function () {
        var $modifyB=$("#modify");
        var $activeB=$("#activate");
        var $logOffB=$("#logOff");
        var $modal=$('.modal-money');

        $modifyB.click(enableEdit);
        $activeB.click(activate);
        $logOffB.click(logOff);
        function enableEdit (e) {
            e.preventDefault();
            var data={};
            $("input").each(function (index,item) {
                item.disabled=!item.disabled;
                data[item.name]=item.value;
            });
            var type=$modifyB.attr("type");

            if(type==="submit"){
                //提交
                $.ajax({
                    type:"POST",
                    contentType:"application/json",
                    url:"json/update",
                    data:JSON.stringify(data),
                    success:function(msg){
                        console.log(msg);
                    },
                    error:function(){
                        console.log("error");
                    },
                    complete : function(XMLHttpRequest,status){
                        $modifyB.attr("type","button");
                        $modifyB.attr("class","btn btn-primary");
                    }
                });

            }
            else{
                //不提交就改成修改状态
                $modifyB.attr("type","submit");
                $modifyB.attr("class","submit btn btn-sm btn-success");
                $modifyB.text("确认修改")
            }
        }

        function activate(e) {
            e.preventDefault();
            $(this).attr("disabled","disabled");
            popRequest();
        }
        function popRequest() {
            if($modal.length>0){
                $modal.css({"opacity":1, "z-index":1});
                return;
            }
            $modal=$('<div class="modal-money"/>');
            var $dialog=$('<div class="money-dialog"/>');

            var $title=$("<h1>充值金额<h1/>");
            var $input=$('<input type="number"/>');
            var $submit= $('<button id="charge" class="btn btn-danger">确定充值</button>');
            $submit.click(charge);
            var $cancle= $('<button id="cancle" class="btn btn-primary">取消</button>');
            $cancle.click(cancleCharge);
            var $body=$(window.document.body);
            $dialog.append($title);
            $dialog.append($input);
            $dialog.append($submit);
            $dialog.append($cancle);
            $modal.append($dialog);
            $body.append($modal);
            setTimeout(function () {
                $modal.addClass('fadein');
            },200);
        }

        function cancleCharge(e) {
            $activeB.removeAttr("disabled");
            $modal.css({"opacity":0, "z-index":-1});
        }

        function charge(e) {

            if($(this).prev().val()==0){
                cancleCharge();
                return;
            }
            var data={money:$(this).prev().val()};
            $.ajax({
                type:"POST",
                url:"json/charge",
                data:data,
                success:function(msg){
                    msg=JSON.parse(msg);
                    if(msg.result=="NOTENOUGH"){
                        alert("银行账户余额不足，充值失败")
                    }else if (msg.result=="SUCCESS"){
                        alert("充值成功");
                        window.location.reload();
                    }else {
                        alert("银行卡号有误");
                    }
                    console.log(msg);
                },
                error:function(){
                    alert("充值失败");
                    console.log("error");
                },
                complete : function(XMLHttpRequest,status){
                    cancleCharge();
                }
            });
        }

        function logOff(e) {
            e.preventDefault();
            var result=confirm("注销后将不能恢复，无法登陆账户，确认注销吗");
            if(result){
                $.ajax({
                    type:"GET",
                    url:"json/logOff",
                    success:function(msg){
                        msg=JSON.parse(msg);
                        if(msg.result=="success"){
                            alert("注销成功")
                        }
                        location.href="/";
                    },
                    error:function(){
                        alert("网络问题，注销失败");
                        console.log("error");
                    }
                });
            }
        }
    });
</script>
</body>
</html>