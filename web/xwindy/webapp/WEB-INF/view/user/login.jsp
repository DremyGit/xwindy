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
<!-- <style>
    #login-main{
        padding: 30px 0px;
    }
    #item-list{
        padding-right: 0;
    }
    #item-list .item{
        display: block;
        padding: 10px 30px;
        margin: 10px 0;
        margin-left: -10px;
    }
    #item-list a:hover{
        text-decoration: none;
        cursor: pointer;
    }
    #item-list .item.active,
    #item-list .item:hover {
        background-color: #f3f3f3;
        border-left: 4px #61B3E6 solid;
        margin-left: -14px;

    }
</style> -->
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container" id="body">
    <div class="row">
        <div class="col-sm-9">
            <div class="card" id="login-card">
                <div class="card-head">
                    <h3>登录</h3>
                </div>
                <div class="row" id="login-main">
                    <form class="form-horizontal">
                        <div class="col-sm-offset-3 col-sm-5">
                            <div class="form-group">
                                <label for="schoolnumber" class="col-sm-3 control-label">学号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="account">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" id="autoLogin"> 记住登录状态
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-4">
                                    <button type="button" id="submit" class="btn xf-btn btn-block">登录</button>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="card item-list">
                <a class="item" href="register">学生用户注册</a>
                <a class="item active" href="login">用户登录</a>
            </div>
        </div>
    </div>
    </div>
<script src="<%=BASE_PATH%>/assets/js/jquery-1.11.2.min.js"></script>
<script src="<%=BASE_PATH%>/assets/js/bootstrap.min.js"></script>
<!-- <script>
    $("#item-list .item").hover(function () {
        $(this).addClass('active');
    }, function () {
        $(this).removeClass('active');
    })
</script> -->
<!-- <div>Account:<input type="text" id="account" /></div>
<div>Password:<input type="password" id="password" /></div>
<div><input type="checkbox" id="autoLogin" />自动登录
<input type="checkbox" id="md5" /> 前端加密
</div>
<div><button id="submit">登录</button></div>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script src="assets/js/jquery.md5.js"></script> -->
<script>
$("#submit").click(function () {
	var account = $("#account").val();
	var password= $("#password").val();
	var autoLogin = $("input[id='autoLogin']:checked").val();
/* 	var md5 = $("input[id='md5']:checked").val(); */
	
/* 	if (autoLogin) alert("autoLoginChoose: " + autoLogin); */
/* 	if (md5) password = $.md5(password); */
	
	$.ajax({
		url: "<%=BASE_PATH%>/user/login.action",
		type: "POST",
		data:{
			account: account,
			password: password,
			autoLogin: autoLogin
		},
		success: function (data) {
			var res = eval(data);
			if(res.isRight) {
				if (location.href.indexOf("login") != -1) {
					window.location.href="<%=BASE_PATH%>";
				} else {
					window.location.reload(true);
				}
			} else {
				alert("isRight: " + res.isRight);
			}
		},
		error: function () {
			alert("连接错误");
		}
	});
	
});
</script>
</body>
</html>