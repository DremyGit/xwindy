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
        .td-left{
            text-align: left !important;
        }
        #th-comment-content{
            width: 25%;
        }
    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>后退</a></li>
    <li><a href="admin"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 主页</a></li>
    <li class="active">评论列表</li>
    <a href="javascript:" id="nav-search"><span class="glyphicon glyphicon-search"></span> 搜索</a>
</ol>
<div style="display: none;" id="div-search"><input type="text" id="input-search" class="form-control" placeholder="输入资讯标题或评论内容"><br></div>


<table class="table table-bordered table-hover table-list">
    <thead>
    <tr>
        <th id="th-comment-id">序号</th>
        <th id="th-comment-news">资讯标题</th>
        <th id="th-comment-content">评论信息</th>
        <th id="th-comment-floor">楼层</th>
        <th id="th-comment-user">评论用户</th>
        <th id="th-comment-ip">发布者IP</th>
        <th id="th-comment-time">发布时间</th>
        <th id="th-comment-state">评论状态</th>
        <th id="th-comment-option">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td><a href="#" title="宣城校区学生在安徽省第三届工程训练综合能力竞赛中喜获佳绩">宣城校区学生在安徽省第三届工程训练综合能..</a></td>
        <td class="td-left">这个干的不错!!!</td>
        <td>1</td>
        <td>测试用户</td>
        <td>127.0.0.1</td>
        <td>2015-07-27 09:52:03</td>
        <td>正常</td>
        <td>
            <a href="javascript:" title="屏蔽"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </td>
    </tr>
    <tr>
        <td>2</td>
        <td><a href="#" title="宣城校区学生在安徽省第三届工程训练综合能力竞赛中喜获佳绩">宣城校区学生在安徽省第三届工程训练综合能..</a></td>
        <td class="td-left">这个干的不错!!!,宣城校区学生在安徽省第三届工程训练综合能力竞赛中喜获佳绩<br>
        好样的!!</td>
        <td>2</td>
        <td>测试用户</td>
        <td>127.0.0.1</td>
        <td>2015-07-27 09:52:03</td>
        <td>正常</td>
        <td>
            <a href="javascript:" title="屏蔽"><span class="glyphicon glyphicon-ban-circle"></span></a>
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
<script>
    function search() {
        var str = $("#input-search").val();
        alert("搜索"+str);
    }
</script>
</body>
</html>
