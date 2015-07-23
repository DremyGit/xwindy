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
类型
<select id="type">
	<option selected="selected" value="null">请选择</option>
	<option value="0">找东西</option>
	<option value="1">找失主</option>
</select><br>
地点:<input id="local" value="${laf.local}" />
关键词:<input id="keyWord" value="${laf.keyWord}" />
描述:<textarea id="content" rows="4" cols="20">${laf.content}</textarea>
上传图片:<input id="picUrl" value="${laf.picUrl}"/>
联系电话:<input id="phone" value="${laf.phone}" />
<button id="submit">修改</button>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$(function () {
	$("#type").val("${laf.type?1:0}");
})
$("#submit").click(function () {
	var type = $("#type").val();
	var local = $("#local").val();
	var keyWord = $("#keyWord").val();
	var content = $("#content").val();
	var picUrl = $("#picUrl").val();
	var phone = $("#phone").val();
	$.ajax({
		url: "lost/edit.action",
		type: "POST",
		data: {
			id: "${laf.id}",
			type: type,
			local: local,
			keyWord: keyWord,
			content: content,
			picUrl: picUrl,
			phone: phone
		},
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				alert("修改成功");
			} else {
				alert("修改失败,原因:" + laf.reason);
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