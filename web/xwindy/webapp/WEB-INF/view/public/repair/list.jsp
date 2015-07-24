<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/jspBegin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../include/jspHead.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
<c:forEach items="${repairList}" var="repair">
	<li>id: ${repair.id}</li>
	<li>stu: ${repair.student.studentNumber}</li>
	<li>name: ${repair.student.username}</li>
	<li>repairer: ${repair.repairer.username}</li>
	<li>local: ${repair.local}</li>
	<li>content:${repair.content}</li>
	<li>phone: ${repair.phone}</li>
	<li>datatime: ${repair.datetime}</li>
	<select id="status_${repair.id}">
		<option value="0">待处理</option>
		<option value="1">已处理</option>
		<option value="2">拒绝处理</option>
	</select>
	<li>resolvetime: ${myRepair.resolvetime}</li>
	<li><button onclick="chuli(${repair.id})">处理</button>
</c:forEach>
</ul>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$(function () {
	<c:forEach items="${repairList}" var="repair">
	$("#status_${repair.id}").val("${repair.status}");
	</c:forEach>
})
function chuli(id) {
	var status = $("#status_" + id).val();
	$.ajax({
		url: "public/repair/edit.action",
		type: "get",
		dataTyep: "JSON",
		data: {
			id: id,
			status: status
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				alert("处理成功");
				window.location.reload(true);
			} else {
				alert("处理失败, 原因: " + res.reason);
			}
		},
		error: function () {
			alert("连接错误");
		}
	})
}
</script>
</body>
</html>