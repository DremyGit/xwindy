<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <title></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/global.css">
</head>
<body>

<%@ include file="../include/navbar.jsp" %>
<div class="container" id="body">
    <div class="row">
        <div class="col-sm-9">
            <div class="card" id="login-card">
                <div class="card-head">
                    <span class="card-head-right">第一步:验证教务账号 <span class="color-grey"> >> 第二步:填写注册信息,完成注册</span></span>
                    <h3>注册</h3>
                </div>
                <div class="bar-40"></div>
                <div class="row" id="login-main">
                    <form class="form-horizontal left">
                        <div class="col-sm-offset-3 col-sm-7">
                            <div class="form-group">
                                <label for="stuId" class="col-sm-3 control-label ">学号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="stuId">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="stuPass" class="col-sm-3 control-label">教务密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="stuPass">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-4">
                                    <button type="button" id="submit" class="btn xf-btn btn-block">下一步</button>
                                </div>
                            </div>
                            <div class="bar-20"></div>
                            <p class="color-grey">郑重声明: 我们不保存您的教务系统密码, 仅供注册时检验账号唯一性所用</p>
                        </div>
                    </form>
                </div>
                <div class="bar-40"></div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="card item-list">
                <a class="item active" href="register">学生用户注册</a>
                <a class="item" href="login">用户登录</a>
            </div>
        </div>
    </div>
</div>
<script src="<%=BASE_PATH%>/assets/js/jquery-1.11.2.min.js"></script>
<script src="<%=BASE_PATH%>/assets/js/bootstrap.min.js"></script>
<script>
$("#submit").click(function () {
	var stuId = $("#stuId").val();
	var stuPass = $("#stuPass").val();
	$.ajax({
		url: "<%=BASE_PATH%>/user/checkid.action",
		type: "POST",
		data:{
			stuId: stuId,
			stuPass: stuPass
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				alert("验证成功, 请填写注册信息");
				window.location.href="<%=BASE_PATH%>/user/register";
			} else if (res.isExisted) {
				alert("该学号已存在");
			} else if (!res.isSuccess) {
				alert("密码错误, 请重试");
			}
		},
		error: function () {
			alert("连接错误");
		}
	})
})
</script>
</body>
</html>