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
<h1>${news.title}</h1>
<div>${news.publicName}</div>
<div>${news.publicIP}</div>
<div>${news.title}</div>
<div>${news.datetime}</div>
<div>${news.clickNum}</div>
<div>${news.commentNum}</div>
<div>${news.content}</div>
<h1>Comment</h1>
<textarea id="comment-content" rows="6" cols="100"></textarea><br>
<button id="submit">提交</button><br>
<c:forEach items="${commentList}" var="comment">
	<div>${comment.username}</div>
	<div>${comment.content}</div>
</c:forEach>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$("#submit").click(function () {
	var content = $("#comment-content").val();
	$.ajax({
		url: "news/commentsubmit.action",
		type: "POST",
		dataType: "JSON",
		data: {
			newsId: '${news.id}',
			content: content
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
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