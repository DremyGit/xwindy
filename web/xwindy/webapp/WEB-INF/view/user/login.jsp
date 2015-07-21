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
<div>Account:<input type="text" id="account" /></div>
<div>Password:<input type="password" id="password" /></div>
<div><input type="checkbox" id="autoLogin" val="true" />自动登录</div>
<div><button id="submit">登录</button></div>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$("#submit").click(function () {
	var account = $("#account").val();
	var password= $("#password").val();
	var autoLogin = $("input[id='autoLogin']:checked").val();
	$.ajax({
		url: "user/login.action",
		type: "POST",
		data:{
			account: account,
			password: password,
			autoLogin: autoLogin
		},
		success: function (data) {
			var res = eval(data);
			if(res.isRight) {
				alert("登录成功" + "isRight: " + res.isRight + "userId: " + res.userId + "userType: " + res.userType);
			} else {
				alert("isRight: " + res.isRight);
			}
		},
		error: function () {
			allert("连接错误");
		}
	});
});
</script>
</body>
</html>