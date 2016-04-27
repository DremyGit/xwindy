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
                    <span class="card-head-right"><span class="color-grey">第一步:验证教务账号 >></span> 第二步:填写注册信息,完成注册</span>
                    <h3>注册</h3>
                </div>
                <div class="bar-40"></div>
                <div class="row" id="login-main">
                    <form class="form-horizontal left">
                        <div class="col-sm-offset-3 col-sm-7">
                            <div class="form-group">
                                <label for="username" class="col-sm-3 control-label ">用户名</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="passagain" class="col-sm-3 control-label">确认密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="passagain">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="telNumber" class="col-sm-3 control-label">手机号码</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="telNumber">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-3 control-label">电子邮箱</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">头像</label>
                                <div class="col-sm-8">
                                    <button type="button" class="btn xf-btn">选择图像</button>
                                </div>
                            </div>
                            <div class="bar-20"></div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-4">
                                    <button type="button" id="submit" class="btn xf-btn btn-block">注册</button>
                                </div>
                            </div>
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
	var username = $("#username").val();
	var password = $("#password").val();
	var telNumber = $("#telNumber").val();
	var email = $("#email").val()
	var header = $("#header").val();
	$.ajax({
		url: "user/register.action",
		type: "POST",
		data:{
			username: username,
			password: password,
			telNumber: telNumber,
			email: email,
			header: header
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				alert("恭喜你注册成功");
			} else {
				alert(res.reason);
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