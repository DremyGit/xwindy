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
<c:forEach items="${lafList}" var="laf">
	<li>
		<div>${laf.id}</div>
		<div>${laf.sendUsername}</div>
		<div>${laf.sendIp}</div>
		<div>${laf.type}</div>
		<div>${laf.local}</div>
		<div>${laf.keyWord}</div>
		<div>${laf.sendTime}</div>
		<div>${laf.content}</div>
		<div>${laf.picUrl}</div>
		<div>${laf.phone}</div>
		<div>${laf.status}</div>
		<div><a href="public/lost/edit/${laf.id}">编辑</a></div>
	</li>
</c:forEach>
</ul>
<a href="public/lost/add">添加 </a>
</body>
</html>