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
<input id="title" value="${news.title}" />
<textarea id="content">${news.content}</textarea>
<button id="submit">修改</button>
<a href="javascript:history.back()">返回上页</a>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$("#submit").click(function () {
	var title = $("#title").val();
	var content = $("#content").val();
	$.ajax({
		url: "public/news/edit.action",
		type: "POST",
		data: {
			title: title,
			content: content,
			publicId: 6,
			id: "${news.id}"
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess == "true") {
				alert("发布成功");
			} else {
				alert("发布失败");
			}
		},
		error: function (data) {
			alert("连接失败");
		}
	})
});
</script>
</body>
</html>