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
        <th id="th-lost-id">序号</th>
        <th id="th-lost-news">发布人</th>
        <th id="th-lost-mode">失/拾</th>
        <th id="th-lost-content">关键词</th>
        <th id="th-lost-floor">内容</th>
        <th id="th-lost-user">图片</th>
        <th id="th-lost-ip">发布者IP</th>
        <th id="th-lost-time">发布时间</th>
        <th id="th-lost-state">招领状态</th>
        <th id="th-lost-option">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td><a href="user.html">测试用户</a></td>
        <td class="td-blue">失</td>
        <td>雨伞</td>
        <td>丢失雨伞一把,求好心人....</td>
        <td class="td-left"><img src=""/></td>
        <td>127.0.0.1</td>
        <td>2015-07-27 09:52:03</td>
        <td class="td-blue">进行中</td>
        <td>
            <a href="javascript:" title="设为完成"><span class="glyphicon glyphicon-ok-circle"></span></a>
            <a href="javascript:" title="设为取消"><span class="glyphicon glyphicon-remove-circle"></span></a>
            <a href="javascript:" title="隐藏"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td>测试用户</td>
        <td class="td-blue">失</td>
        <td>雨伞</td>
        <td>丢失雨伞一把,求好心人....</td>
        <td class="td-left"><img src=""/></td>
        <td>127.0.0.1</td>
        <td>2015-07-27 09:52:03</td>
        <td class="td-blue">进行中</td>
        <td>
            <a href="javascript:" title="设为完成"><span class="glyphicon glyphicon-ok-circle"></span></a>
            <a href="javascript:" title="设为取消"><span class="glyphicon glyphicon-remove-circle"></span></a>
            <a href="javascript:" title="隐藏"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td>测试用户</td>
        <td class="td-green">拾</td>
        <td>雨伞</td>
        <td>丢失雨伞一把,求好心人....</td>
        <td class="td-left"><img src=""/></td>
        <td>127.0.0.1</td>
        <td>2015-07-27 09:52:03</td>
        <td class="td-green">已找到</td>
        <td>
            <a href="javascript:" title="设为完成"><span class="glyphicon glyphicon-ok-circle"></span></a>
            <a href="javascript:" title="设为取消"><span class="glyphicon glyphicon-remove-circle"></span></a>
            <a href="javascript:" title="隐藏"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td>测试用户</td>
        <td class="td-green">拾</td>
        <td>雨伞</td>
        <td>丢失雨伞一把,求好心人....</td>
        <td class="td-left"><img src=""/></td>
        <td>127.0.0.1</td>
        <td>2015-07-27 09:52:03</td>
        <td class="td-grey">已取消</td>
        <td>
            <a href="javascript:" title="设为完成"><span class="glyphicon glyphicon-ok-circle"></span></a>
            <a href="javascript:" title="设为取消"><span class="glyphicon glyphicon-remove-circle"></span></a>
            <a href="javascript:" title="隐藏"><span class="glyphicon glyphicon-ban-circle"></span></a>
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td>测试用户</td>
        <td class="td-green">拾</td>
        <td>雨伞</td>
        <td>丢失雨伞一把,求好心人....</td>
        <td class="td-left"><img src=""/></td>
        <td>127.0.0.1</td>
        <td>2015-07-27 09:52:03</td>
        <td class="td-red">已隐藏</td>
        <td>
            <a href="javascript:" title="设为完成"><span class="glyphicon glyphicon-ok-circle"></span></a>
            <a href="javascript:" title="设为取消"><span class="glyphicon glyphicon-remove-circle"></span></a>
            <a href="javascript:" title="隐藏"><span class="glyphicon glyphicon-ban-circle"></span></a>
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
