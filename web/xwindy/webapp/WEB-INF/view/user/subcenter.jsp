<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</html> --%>

<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <title></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/global.css">
    <style>
        .img-block {
            display: block;
            width: 99%;
            margin: 0 auto;
        }
        .public-list {
            padding: 4px;
        }
        .public-list > .card {
            margin: 0;
            height: 100px;
        }
        .public-list > .card > .head-pic {
            width: 60px;
            float: left;
        }
        .public-list > .card > .text {
            margin-left: 80px;
        }
        .public-list > .card > .text > .public-name {
            height: 20px;
            margin-bottom: 10px;
            font-size: 16px;
        }
        .public-list > .card > .text > .public-right {
            height: 40px;
            background-color: #fff;
            position: absolute;
            right: 18px;
            width: 150px;
            top: 12px;
        }
        .public-list > .card > .text  .public-num {
            float: left;
            margin-top: 10px;
            margin-left: 10px;
            color: #888;
            font-size: 12px;
        }
        .public-list > .card > .text > p {
            margin-top: 20px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <h2>订阅中心</h2>
    <div class="bar-20"></div>
    <div class="row">
        <div class="col-sm-3">
            <div class="card item-list">
                <!-- <a class="item active">特别推荐</a>
                <a class="item">学校官网</a>
                <a class="item">信息工程系</a> -->
                <a href="0" class='item ${classId == 0 ? "active" : ""}'>特别推荐</a>
			    <c:forEach items="${classList}" var="publicClass">
			         <a href="${publicClass.id}" class='item ${classId == publicClass.id ? "active" : ""}'>${publicClass.className}</a>
			    </c:forEach>
            </div>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <img class="img-block hidden-xs" src="<%=BASE_PATH%>/assets/pic/subcenter.jpg">
            </div>
            <div class="bar-10"></div>
            <div class="row">                
                <c:forEach items="${publicerList}" var="publicer">
                 <div class="col-xs-12 col-sm-6 public-list">
                    <div class="card">
                        <div class="head-pic">
                            <img src="${publicer.header}" class="pic-grey">
                        </div>
                        <div class="text">
                            <div class="public-name">${publicer.username}</div>
                            <div class="public-right">
                                <span class="public-num">${publicer.subscribeUserNum}人订阅</span>
                                <c:choose>
                                    <c:when test="${!publicer.isSub}">
                                        <button class="btn xf-btn pull-right" onclick="sub(${publicer.id})">+订阅</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn xf-btn pull-right" onclick="rem(${publicer.id})">-退订</button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <p>${publicer.introduce}</p>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<script src="<%=BASE_PATH%>/assets/js/jquery-1.11.2.min.js"></script>
<script src="<%=BASE_PATH%>/assets/js/bootstrap.min.js"></script>
<script>
function sub(publicId) {
    var url = "<%=BASE_PATH%>/news/subscribe.action";
    sendAjax(url, publicId);
}
function rem(publicId) {
    var url = "<%=BASE_PATH%>/news/unsubscribe.action";
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


