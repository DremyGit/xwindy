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
        .breadcrumb .btn{
            margin: 0 20px;
        }
    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>后退</a></li>
    <li><a href="admin"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 主页</a></li>
    <li class="active">公众号用户管理</li>
    <a href="javascript:" id="nav-search"><span class="glyphicon glyphicon-search"></span> 搜索</a>
    <a class="btn btn-primary btn-sm" href="publicer.html">添加新用户</a>
</ol>
<div style="display: none;" id="div-search"><input type="text" id="input-search" class="form-control" placeholder="输入用户名或学号敲回车进行搜索"><br></div>


<table class="table table-bordered table-hover table-list">
    <thead>
    <tr>
        <th id="th-user-id">用户id</th>
        <th id="th-user-name">用户名</th>
        <th id="th-user-usertype">类型</th>
        <th id="th-user-news">资讯数</th>
        <th id="th-user-user">订阅数</th>
        <th id="th-user-lastsend">最后发布日期</th>
        <th id="th-user-active">上次登录时间</th>
        <th id="th-user-state">状态</th>
        <th id="th-user-option">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>12312</td>
        <td>fhgsm123</td>
        <td>资讯号</td>
        <td>22</td>
        <td>10</td>
        <td>2015-07-27</td>
        <td>2015-07-26 22:21</td>
        <td>正常</td>
        <td>
            <a href="student.html" title="编辑"><span class="glyphicon glyphicon-edit"></span></a>
            <a href="javascript:" title="禁用"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </td>
    </tr>
<c:forEach items="${publicerList}" var="publicer">
    <tr>
        <td>${publicer.id}</td>
        <td>${publicer.username}</td>
        <td>
            <c:choose>
            <c:when test="${publicer.userType == 'GZH'}">资讯号</c:when>
            <c:when test="${publicer.userType == 'BX'}">报修号</c:when>
            <c:when test="${publicer.userType == 'ZL'}">报修号</c:when>
            <c:when test="${publicer.userType == 'GLY'}">超级管理员</c:when>
            </c:choose>
        </td>
        <td>
            ${publicer.newsNumber}
        </td>
        <td>${publicer.subscribeUserNum}</td>
        <td>${publicer.lastNewsTime}</td>
        <td>${publicer.lastActive}</td>
        <td>${publicer.state==0?"<span class=td-red>禁用</span>":"正常"}</td>
        <td>
            <a href="student.html" title="编辑"><span class="glyphicon glyphicon-edit"></span></a>
    <c:choose>
        <c:when test="${publicer.state == 1}">
            <a href="javascript:" title="禁用"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </c:when>
        <c:when test="${publicer.state == 0}">
            <a href="javascript:" title="启用"><span class="glyphicon glyphicon-ok-circle"></span></a>
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
<script>
    function search() {
        var str = $("#input-search").val();
        alert("搜索"+str);
    }
</script>
</body>
</html>
