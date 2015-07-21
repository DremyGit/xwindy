<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/jspHead.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#classList{
	width: 200px;
	position: absolute;
}
#publicList{
	left: 250px;
	position: absolute;
}
</style>
</head>
<body>
<ul id="classList">
	<li><a href="user/subcenter/0">推荐订阅</a></li>
	<c:forEach items="${classList}" var="publicClass">
	<li><a href="user/subcenter/${publicClass.id}">${publicClass.className}</a></li>
	</c:forEach>
</ul>
<ul id="publicList">
	<c:forEach items="${publicerList}" var="publicer">
	<li>
		<span>${publicer.username}</span>
		<span>${publicer.introduce}</span>
		<span>${publicer.isSub}</span>
		<c:choose>
			<c:when test="${publicer.isSub}">
				<span><button onclick="rem(${publicer.id})">取消订阅</button></span>
			</c:when>
			<c:otherwise>
				<span><button onclick="sub(${publicer.id})">订阅</button></span>
			</c:otherwise>
		</c:choose>
	</li>
	</c:forEach>
</ul>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
function sub(publicId) {
	var url = "user/subscribe.action";
	sendAjax(url, publicId);
}
function rem(publicId) {
	var url = "user/unsubscribe.action";
	sendAjax(url, publicId);
}
function sendAjax(url, publicId) {
	$.ajax({
		url: url,
		type: "GET",
		data:{publicId: publicId},
		success: function(data) {
			var res = eval(data);
			if (res.isSuccess) {
				window.location.reload(true);
			} else {
				alert("操作失败")
			}
		},
		error: function() {
			alert("连接错误");
		}
	});
}
</script>
</body>
</html>