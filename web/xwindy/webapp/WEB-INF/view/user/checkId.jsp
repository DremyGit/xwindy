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
学号:<input id="stuId" /><br>
教务密码<input id="stuPass" /><br>
<button id="submit">下一步</button>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$("#submit").click(function () {
	var stuId = $("#stuId").val();
	var stuPass = $("#stuPass").val();
	$.ajax({
		url: "user/checkid.action",
		type: "POST",
		data:{
			stuId: stuId,
			stuPass: stuPass
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				alert("验证成功, 请填写注册信息");
				window.location.href="user/register";
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