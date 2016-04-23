<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>${news.title} </title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/article.css">
<style>
body{
    background-color:#f3f3f3;
    font-family:"Microsoft Yahei";
    color: #333;
}
/*@media (max-width: 767px) {*/
    /*#body{*/
        /*margin-top: 0px;*/
    /*}*/
/*}*/

/*@media (max-width: 767px) {*/
    /*.card{*/
        /*margin: 0px -15px;*/
        /*margin-bottom: 10px;*/
    /*}*/
/*}*/
#article{
    /*padding: 10px;*/
}


#article-title h3{
    font-size: 22px;
}
#article-info{
    margin-left: 20px;
    margin-right: 10px;
    font-size: 12px;
}
#article-content {
    margin-top: 16px;
}

#comment-submit-area {
    margin-bottom: 70px;
    padding: 0 5%;
}
#comment-submit-area h4 {
    margin-top: 20px;
}
#comment-submit-content{
    width: 100%;
    height: 100px;
    /*margin: 0 5%;*/
}
.comment-time {
    font-size: 12px;
    color: #666;
}
#comment-area {
    padding-left: 20px;
}
#comment-area li {
    border-top: 1px #bbb solid;
    list-style: none;
    padding: 8px;
}
.comment-username{
    position: relative;
    top: -10px;
}
.comment-floor{
    float: right;
    font-size: 12px;
}
.comment-content{
    padding: 16px;
}

.head-img {
    width: 50px;
}


#article-userhead {
    background-color: #eee;
    height: 50px;
    width: 50px;
    float: left;
}
#article-userhead img {
    width: 100%;
}
#article-username {
    margin-left: 8px;
}
#article-user-info{
    font-size: 12px;
    color: #666;
    margin-top: 10px;
    margin-left: 60px;
}

</style>
</head>
<body>
<%-- <h1>${news.title}</h1>
<div>${news.publicer.username}</div>
<div>${news.publicIP}</div>
<div>${news.title}</div>
<div>${news.datetime}</div>
<div>${news.clickNum}</div>
<div>${news.commentNum}</div>
<div>${news.content}</div>
<div>publicer: ${news.publicer.password}</div>
<h1>Comment</h1>
<textarea id="comment-content" rows="6" cols="100"></textarea><br>
<button id="submit">提交</button><br>
<c:forEach items="${news.commentList}" var="comment">
	<div>${comment.username}</div>
	<div>${comment.content}</div>
</c:forEach> --%>
<%@ include file="../include/navbar.jsp" %>


<div class="container" id="body">
    <div class="row">
        <div class="col-sm-9">
            <div class="card" id="article">
                <div class="card-head" id="article-title">
                    <h3>${news.title}</h3>
                </div>
                <div id="article-info">
                    ${news.datetime.substring(0,16)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ${news.publicer.username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ${news.clickNum}次阅读&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ${news.commentNum}条评论
                </div>
                <div id="article-content">
                    ${news.content}
                </div>
            </div>
            <div class="card" id="comment">
                <div class="card-head">
                    <h3>评论区（2条评论）</h3>
                </div>
                <div id="comment-submit-area">
                    <h4>发表评论</h4>
                    <textarea id="comment-submit-content"></textarea>
                    <button id="comment-submit-button" class="btn xf-btn pull-right">发表</button>
                </div>
                <ul id="comment-area">
                    <c:forEach items="${news.commentList}" var="comment" varStatus="i">
                        <div>${comment.username}</div>
                        <div>${comment.content}</div>
                        <li>
	                        <div class="a-comment">
	                            <img class="head-img" src="pic/head.jpg">
	                            <span class="comment-username">${comment.username}</span>
	                            <span class="comment-floor">${i + 1}楼</span>
	                            <p class="comment-content">${comment.content}</p>
	                            <span class="comment-time">${comment.datetime}</span>
	                        </div>
	                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="card" id="article-user">
                <div id="article-userhead">
                    <img src="pic/head.jpg">
                </div>
                <div id="article-userblock">
                <span id="article-username">${news.publicer.username }</span>
                <c:choose>
                    <c:when test="${!news.publicer.isSub}">
                        <button class="btn xf-btn btn-sm float-right" id="article-user-sub" onclick="sub(${news.publicer.id})">
		                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>订阅
		                </button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn xf-btn btn-sm float-right" id="article-user-sub" onclick="rem(${news.publicer.id})">
                            已订阅
                        </button>
                    </c:otherwise>
                </c:choose>
                
                <div id="article-user-info">${news.publicer.introduce}</div>
                </div>
                <span id="article-user-fans"></span>
            </div>
            <!--<div class="card" id="lost">-->

            <!--</div>-->
            <!-- <div class="card" id="pub-user-recommend">
                <ul>
                    <li>
                        <div class="a-pub-user">
                            <img class="head-img" src="pic/head.jpg">
                            <span class="pub-user-username">校区新闻</span>
                            <button class="btn xf-btn btn-sm">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                订阅
                            </button>
                            <span class="pub-user-info">
                                从官网直接抓取，消息来源及时可靠，让你不错过一条消息
                            </span>
                        </div>
                    </li>
                    <li>
                        <div class="a-pub-user">
                            <img class="head-img" src="pic/head.jpg">
                            <span class="pub-user-username">校区新闻</span>
                            <button class="btn xf-btn">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                订阅
                            </button>
                            <span class="pub-user-info">
                                从官网直接抓取，消息来源及时可靠，让你不错过一条消息
                            </span>
                        </div>
                    </li>
                </ul>
            </div> -->
        </div>
    </div>
</div>
<script src="<%=BASE_PATH%>/assets/js/jquery-1.11.2.min.js"></script>
<script src="<%=BASE_PATH%>/assets/js/bootstrap.min.js"></script>
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