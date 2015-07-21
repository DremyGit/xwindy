<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/jspHead.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
isLogin:${isLogin}<br>
用户名:<input id="username" /><br>
密码:<input type="password" id="password" /><br>
手机号:<input id="telNumber" /><br>
电子邮箱:<input id="email" /><br>
头像url:<input id="header" /><br>
<button id="submit">提交</button>
<script src="assets/js/jquery-1.11.2.min.js"></script>
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