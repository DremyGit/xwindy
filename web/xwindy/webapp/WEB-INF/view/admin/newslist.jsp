<%@page import="com.xwindy.web.util.SysUtil"%>
<%@page import="org.apache.ibatis.annotations.Param"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/jspHead.jsp" %>
    <meta charset="utf-8">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/main-frame.css" rel="stylesheet">
    <style>

    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>后退</a></li>
    <li><a href="admin"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 主页</a></li>
    <li class="active">资讯列表</li>
    <a href="javascript:" id="nav-search"><span class="glyphicon glyphicon-search"></span> 搜索</a>
</ol>
<div style="display: none;" id="div-search"><input type="text" id="input-search" class="form-control" placeholder="输入资讯标题或公众号后敲回车进行搜索"><br></div>


<table class="table table-bordered table-hover table-list">
    <thead>
    <tr>
        <th id="th-news-id">序号</th>
        <th id="th-news-title">资讯标题</th>
        <th id="th-news-user">公众号</th>
        <th id="th-news-read">点击数</th>
        <th id="th-news-comment">评论数</th>
        <th id="th-news-ip">发布者IP</th>
        <th id="th-news-pushstate">推送状态</th>
        <th id="th-news-state">资讯状态</th>
        <th id="th-news-sendtime">发布时间</th>
        <th id="th-news-option">操作</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${newsList}" var="news">
    <tr>
        <td>${news.id}</td>
        <td><a href="news/${news.id}" title="${news.title}">${news.title}</a></td>
        <td>${news.publicer.username}</td>
        <td>${news.clickNum}</td>
        <td>${news.commentNum}</td>
        <td>${news.publicIP}</td>
        <td>
            <c:choose>
              <c:when test="${news.push == 0}">不推送</c:when>
              <c:when test="${news.push == 1}"><span class="td-red">申请推送</span></c:when>
              <c:when test="${news.push == 2}"><span class="td-green">已推送</span></c:when>
              <c:when test="${news.push == 3}"><span class="td-blue">拒绝推送</span></c:when>
            </c:choose>
        </td>
        <td>${news.state==0?"<span class=td-red>隐藏</span>":"正常"}</td>
        <td>${news.datetime}</td>
        <td>
            <a href="admin/news/${news.id}" title="编辑"><span class="glyphicon glyphicon-edit"></span></a>
    <c:choose>
        <c:when test="${news.state == 1}">
            <a href="javascript:" title="隐藏"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </c:when>
        <c:when test="${news.state == 0}">
            <a href="javascript:" title="显示"><span class="glyphicon glyphicon-ok-circle"></span></a>
        </c:when>
    </c:choose>
        </td>
    </tr>
</c:forEach>
    </tbody>
</table>


<ul class="pagination">
    <c:if test="${pag.preShow}"><li><a href="${pag.pageUrl}${pag.viewFirstPage - 1}"><span aria-hidden="true">&laquo;</span></a></li></c:if>
    <c:forEach begin="${pag.viewFirstPage}" end="${pag.viewLastPage}" var="i">
        ${(i == pag.nowPage) ? '<li class="active">' : '<li>'}
        <a href="${pag.pageUrl}${i}">${i}</a>${'<li>'}
    </c:forEach>
    <c:if test="${pag.nextShow}"><li><a href="${pag.pageUrl}${pag.viewLastPage + 1}"><span aria-hidden="true">&raquo;</span></a></li></c:if>
</ul>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/main-frame.js"></script>
</body>
</html>
