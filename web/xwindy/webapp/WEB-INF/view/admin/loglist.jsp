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
    <li class="active">系统日志</li>
    <a href="javascript:" id="nav-search"><span class="glyphicon glyphicon-search"></span> 搜索</a>
</ol>
<div style="display: none;" id="div-search"><input type="text" id="input-search" class="form-control" placeholder="输入日志内容后敲回车进行搜索"><br></div>


<table class="table table-bordered table-hover table-list">
    <thead>
    <tr>
        <th id="th-news-id">序号</th>
        <th id="th-news-title">日志内容</th>
        <th id="th-news-user">触发用户</th>
        <th id="th-news-comment">记录时间</th>
        <th id="th-news-ip">用户IP</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${logList}" var="log">
    <tr>
        <td>${log.id}</td>
        <td class="td-left">${log.content}</td>
        <td>${log.user.username}</td>
        <td>${log.datetime}</td>
        <td>${log.userIp}</td>
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
