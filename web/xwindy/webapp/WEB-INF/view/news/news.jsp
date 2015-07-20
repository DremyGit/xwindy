<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<c:forEach items="${commentList}" var="comment">
	<div>${comment.username}</div>
	<div>${comment.content}</div>
</c:forEach>
</body>
</html>