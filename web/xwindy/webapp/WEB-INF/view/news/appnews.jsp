<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>${news.title} </title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/article.css">
<style>
body{
    background-color:#fff;
    font-family:"Microsoft Yahei";
    color: #333;
}
html, body {
    margin: 0;
    padding: 0;
}

#body {
}

#article{
    margin: 0;
}


#article-title h3{
    font-size: 22px;
    
    font-size: 18px;
    font-weight: normal;
    border-left: 7px #00ccff solid;
    border-bottom: 1px #f3f3f3 solid;
    padding: 10px 5px 10px 20px;
    margin-left: 0px;
    margin-top: 0px;
}
#article-info{
    margin-left: 20px;
    margin-right: 10px;
    font-size: 12px;
}

#article-content {
    padding: 8px;
    margin: 0;
}

</style>
</head>
<body>
<div class="container" id="body">
  <div class="card" id="article">
	   <div class="card-head" id="article-title">
	       <h3>${news.title}</h3>
	   </div>
	   <div id="article-info">
	       ${news.datetime.substring(0,16)}&nbsp;&nbsp;&nbsp;&nbsp;${news.publicer.username}&nbsp;&nbsp;&nbsp;&nbsp;${news.clickNum}次阅读&nbsp;&nbsp;&nbsp;&nbsp;${news.commentNum}条评论
	   </div>
	   <div id="article-content">
	       ${news.content}
	   </div>
 </div>
</div>
</body>
</html>