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
<c:forEach items="${newsList}" var="news">
<div>
	<span><a href="public/news/${news.id}">${news.title}</a></span>
	<span>clickNum:${news.clickNum}</span>
	<span>commentNum:${news.commentNum}</span>
	<span><a href="public/news/edit/${news.id}">编辑</a></span>
</div>
</c:forEach>
</body>
</html>