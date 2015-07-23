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
地点:<input id="local" value="" />
关键词:<input id="keyWord" value="" />
描述:<textarea id="content" rows="4" cols="20"></textarea>
上传图片:<input id="picUrl" value=""/>
联系电话:<input id="phone" value="" />
<button id="submit">提交</button>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$("#submit").click(function () {
	var type = $("#type").val();
	if (type == "null") {
		alert("请选择类型");
		return 0;
	}
	var local = $("#local").val();
	var keyWord = $("#keyWord").val();
	var content = $("#content").val();
	var picUrl = $("#picUrl").val();
	var phone = $("#phone").val();
	$.ajax({
		url: "lost/add.action",
		type: "POST",
		data: {
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
				alert("发布成功");
			} else {
				alert("发布失败, 原因:" + res.reason);
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