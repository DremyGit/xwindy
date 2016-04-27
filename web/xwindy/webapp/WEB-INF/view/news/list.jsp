<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/global.css">
    <meta charset="UTF-8">
    <title>资讯列表</title>
    <style>
        @media (max-width: 767px) {
            #body{
                margin-top: 20px;
            }
        }
        #class-ul, #news-ul{
            padding: 0;
            margin: 0;
        }
        #class-ul li, #news-ul li{
            list-style: none;
            margin-left: 0;
        }
        #class-ul li{
            float: left;
        }
        .title-bar{
            height: 40px;
            background: #3399cc;
            color: #fff;;
        }
        #class-ul{
            padding: 14px 15px;
            display: inline-block;
        }
        @media(max-width: 767px) {
            #class-ul{
                padding: 14px 10px;
            }
        }
        #class-ul li a{
            margin: 3px;
        }
        @media(max-width: 767px) {
            #class-ul li a{
                margin: 2px;
            }
        }
        .title-bar a{
            color: #fff;
            padding:8px 15px;
        }
        @media (max-width: 767px) {
            .title-bar a{
                padding: 8px 8px;
            }
        }
        .title-bar a.active{
            background-color: #fff;
            color: #333;

        }
        .title-bar>h3{
            margin: 0 15px;
            padding-top: 10px;
            font-size: 16px;
        }

        #search-news, #subcenter{
            font-size: 18px;
            float: right;
            margin: 10px 10px;
        }
        #news-ul .card{
            padding: 20px;
            margin-bottom: 10px;
        }
        .news-head{
        }
        @media (min-width: 768px) {
            .news-head h2, .news-head div{
                padding-left: 75px;
            }
        }
        .news-user-head{
            float: left;
            width: 60px;
            height: 60px;
        }
        .news-title {
            font-size: 22px;
            line-height: 30px;
            margin-top: 0px;
            margin-bottom: 15px;
            font-size: 20px;
            font-style: normal;
            font-variant: normal;
            font-weight: normal;
        }
        @media (max-width: 767px) {

            .news-title{
                min-height: initial;
                margin-bottom: 5px;
            }
            .news-head{
                min-height: initial;
                margin-bottom: 5px;
            }
        }
        .news-info, .news-info a{
            margin-right: -5px;
            font-size: 12px;
            color: #888;
        }
        .news-pic{
            width: 200px;
            float: right;
            margin-top: -40px;
            margin-left: 20px;
        }
        @media (max-width: 767px) {
            .news-pic{
                margin: 0px;
                width: auto;
                max-width: 100%;
                float: none;
            }
        }

        .news-summary p{
            margin-top: 10px;
            line-height: 28px;
            clear: left;
        }

        @media (max-width: 767px) {
            .news-summary p{
                line-height: 25px;
                max-height: 100px;
                margin-top: 0px;
                overflow: hidden;
            }
        }

        #top-ul{
            padding: 0px;
        }
        #top-ul li{
            list-style: none;
            border-top: 1px;
            display: inline-block;
            height: 20px;
            overflow: hidden;
            margin-bottom: 5px;
        }
        #top-ul li i{
            float: left;
            width: 20px;
            padding: 2px 4px;
            background-color: #31708f;
            color: #fff;
        }
        #top-ul li a{
            position: relative;
            top: -3px;
            margin-left: 10px;
            height: 20px;
            line-height: 28px;
        }
    </style>
</head>
<%@ include file="../include/navbar.jsp" %>
<div class="container" id="body">
    <div class="row">
        <div class="col-sm-9">
            <div class="title-bar">
                <ul id="class-ul">
                    <li><a class='${listType == "allNews" ? "active" : "" }' href="<%=BASE_PATH%>/news">全部资讯</a></li>
                    <c:if test="${isLogin}">
                        <li><a class='${listType == "mySub" ? "active" : "" }' href="<%=BASE_PATH%>/news/mysub">我的订阅</a></li>
                    </c:if>
                </ul>
                <a href="<%=BASE_PATH%>/news/subcenter/0"><span id="subcenter" class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
                <span>

                </span>
            </div>
            <ul id="news-ul">
                
                <c:forEach items="${newsList}" var="news">
				    <li class="card">
                    <div class="news-head">
                        <img class="news-user-head hidden-xs" src="${news.publicer.header}">
                        <h2 class="news-title">
                            <a href="<%=BASE_PATH%>/news/${news.id}">${news.title}</a>
                        </h2>
                        <div class="news-info">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            <span>${news.publicer.username}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-time" aria-hidden="true"></span>
                            ${news.datetime.substring(0,16)}&nbsp;&nbsp;&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            ( ${news.clickNum} )&nbsp;&nbsp;&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                            ( ${news.commentNum} )&nbsp;&nbsp;&nbsp;&nbsp;
                        </div>
                    </div>

                    <div class="news-summary">
                        <c:if test="${news.firstPicUrl != null}">
                            <img class="news-pic" src="${news.firstPicUrl}">
                        </c:if>
                        <p>
                            ${news.summary}
                        </p>
                    </div>
                </li>
                </c:forEach>
                <ul class="pagination">
				    <c:if test="${pag.preShow}"><li><a href="${pag.pageUrl}${pag.viewFirstPage - 1}"><span aria-hidden="true">&laquo;</span></a></li></c:if>
				    <c:forEach begin="${pag.viewFirstPage}" end="${pag.viewLastPage}" var="i">
				        ${(i == pag.nowPage) ? '<li class="active">' : '<li>'}
				        <a href="${pag.pageUrl}${i}">${i}</a>${'<li>'}
				    </c:forEach>
				    <c:if test="${pag.nextShow}"><li><a href="${pag.pageUrl}${pag.viewLastPage + 1}"><span aria-hidden="true">&raquo;</span></a></li></c:if>
				</ul>
                
                
            </ul>
        </div>
        <div class="col-sm-3">
            <div class="title-bar">
                <h3>一周资讯排行</h3>
            </div>
            <div class="card">
                <ul id="top-ul">
                    <c:forEach items="${rankList}" var="news" varStatus="i">
                    <li>
                        <i>${i.index + 1}</i>
                        <a href="<%=BASE_PATH%>/news/${news.id}">${news.title}</a>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="<%=BASE_PATH%>/assets/js/jquery-1.11.2.min.js"></script>
<script src="<%=BASE_PATH%>/assets/js/bootstrap.min.js"></script>
<script>
//    $('#nav-user').hover(function () {
//        $('#dropdown-user').dropdown('toggle');
//    }, function () {
//
//    })
</script>
</body>
</html>