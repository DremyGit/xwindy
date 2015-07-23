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
<c:if test="${!empty myRepairList}">
<ul>
<c:forEach items="${myRepairList}" var="myRepair">
	<li>id: ${myRepair.id}</li>
	<li>stu: ${myRepair.studentNumber}</li>
	<li>name: ${myRepair.studentUsername}</li>
	<li>repairer: ${myRepair.repairerName}</li>
	<li>local: ${myRepair.local}</li>
	<li>content:${myRepair.content}</li>
	<li>phone: ${myRepair.phone}</li>
	<li>datatime: ${myRepair.datetime}</li>
	<li>status: ${myRepair.status}</li>
	<li>resolvetime: ${myRepair.resolvetime}</li>
</c:forEach>
</ul>
</c:if>
<select id="repairerId">
	<option value="null" selected="selected">请选择</option>
	<c:forEach items="${repairerList}" var="repairer">
	<option value="${repairer.id}">${repairer.username}</option>
	</c:forEach>
</select><br>
报修地点: <input id="local" /><br>
报修内容: <textarea id="content" rows="4" cols="10"></textarea><br>
联系电话: <input id="phone" /><br>
<button id="submit">提交</button>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$("#submit").click(function () {
	var repairerId = $("#repairerId").val();
	if (repairerId == "null") {
		alert("请选择需要报修的部门");
		return 0;
	}
	var local = $("#local").val();
	var content = $("#content").val();
	var phone = $("#phone").val();
	$.ajax({
		url: "repair/add.action",
		type: "POST",
		dataType: "JSON",
		data: {
			repairerId: repairerId,
			local: local,
			content: content,
			phone: phone
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				alert("添加成功");
				window.location.reload(true);
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