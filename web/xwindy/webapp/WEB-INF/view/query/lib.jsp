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
${isSuccess}
${isLogin}
${reason}
哈哈
<div id="state"></div>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$(function () {
	$("#state").text("查询中,请稍后");
	$.ajax({
		url: "query/lib.action",
		type: "GET",
		dataType: "JSON",
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				var html = "<ul>";
				var b = res.borrowList;
				for (i in b) {
					html += "<li><ul><li>书名: " + b[i].bookName + "</li><li>借阅日期: " + b[i].borrowDate + "</li><li>应还日期" + b[i].expectDate
					      + "</li><li>有无附件: " + b[i].haveAttach + "</li><li><a href='" + b[i].renewHref + "'>一键续借</a></li></ul></li>";
				}
				html += "</ul>";
				$("#state").after(html).text("查询成功");
			} else {
				alert("查询失败, 原因: " + res.reason);
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