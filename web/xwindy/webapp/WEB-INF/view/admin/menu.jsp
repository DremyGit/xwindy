<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../include/jspHead.jsp" %>
    <meta charset="utf-8">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .list-group-item{
            border-radius: 0 !important;
        }
        .active{
            background-color: #e6e6e6 !important;
            border-color: #e6e6e6 !important;
            color: #333 !important;
        }
        a.list-group-item{
            padding-left: 25px;
        }

    </style>
</head>
<body>
<div class="list-group">
    <a class="list-group-item" href="admin/main" target="main_frame">管理系统首页</a>
    <li class="list-group-item list-group-item-info">用户管理</li>
    <a class="list-group-item" href="admin/student" target="main_frame">学生用户列表</a>
    <a class="list-group-item" href="admin/public" target="main_frame">公众号用户列表</a>
    <li class="list-group-item list-group-item-info">资讯管理</li>
    <a class="list-group-item" href="admin/news" target="main_frame">资讯列表</a>
    <a class="list-group-item" href="admin/comment" target="main_frame">评论列表</a>
    <a class="list-group-item" href="admin/news/push" target="main_frame">推送列表</a>
    <a class="list-group-item" href="admin/lost" target="main_frame">招领列表</a>
    <li class="list-group-item list-group-item-info">系统管理</li>
    <a class="list-group-item" href="admin/log" target="main_frame">系统日志查看</a>
    <a class="list-group-item" href="admin/resource" target="main_frame">网站资源管理</a>
</div>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
    $("a.list-group-item").click(function () {
        $(".active").removeClass("active");
        $(this).addClass("active");
    })
</script>
</body>
</html>
