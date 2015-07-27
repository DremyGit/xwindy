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
        <th id="th-news-comment">评论数</th>
        <th id="th-news-ip">发布者IP</th>
        <th id="th-news-pushstate">推送状态</th>
        <th id="th-news-sendtime">发布时间</th>
        <th id="th-news-option">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td><a href="#" title="宣城校区学生在安徽省第三届工程训练综合能力竞赛中喜获佳绩">宣城校区学生在安徽省第三届工程训练综合能..</a></td>
        <td>校区新闻</td>
        <td>10</td>
        <td>127.0.0.1</td>
        <td class="td-red">请求推送</td>
        <td>2015-07-27 01:14</td>
        <td>
            <a href="javascript:" title="允许推送"><span class="glyphicon glyphicon-ok"></span></a>
            <a href="javascript:" title="拒绝推送"><span class="glyphicon glyphicon-remove"></span></a>
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td><a href="#" title="宣城校区学生在安徽省第三届工程训练综合能力竞赛中喜获佳绩">宣城校区学生在安徽省第三届工程训练综合能..</a></td>
        <td>校区新闻</td>
        <td>10</td>
        <td>127.0.0.1</td>
        <td class="td-green">已推送</td>
        <td>2015-07-27 01:14</td>
        <td>
            <a href="javascript:" title="允许推送"><span class="glyphicon glyphicon-ok"></span></a>
            <a href="javascript:" title="拒绝推送"><span class="glyphicon glyphicon-remove"></span></a>
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td><a href="#" title="宣城校区学生在安徽省第三届工程训练综合能力竞赛中喜获佳绩">宣城校区学生在安徽省第三届工程训练综合能..</a></td>
        <td>校区新闻</td>
        <td>10</td>
        <td>127.0.0.1</td>
        <td class="td-blue">拒绝推送</td>
        <td>2015-07-27 01:14</td>
        <td>
            <a href="javascript:" title="允许推送"><span class="glyphicon glyphicon-ok"></span></a>
            <a href="javascript:" title="拒绝推送"><span class="glyphicon glyphicon-remove"></span></a>
        </td>
    </tr>





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
