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
        .td-label {
            width: 150px;
        }
        #version{width: 16em;}
    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>后退</a></li>
    <li><a href="admin"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 主页</a></li>
    <li class="active">静态资源管理</li>
</ol>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">静态资源管理</h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <tr>
                <td class="td-label">CSS/JS版本号</td>
                <td><input type="text" id="version" class="form-control"></td>
            </tr>
            <tr>
                <td class="td-label">JS统计代码</td>
                <td><textarea class="form-control" rows="10"></textarea></td>
            </tr>
            <tr>
                <td colspan="2"><button class="btn btn-primary" id="submit">修改</button></td>
            </tr>
        </table>
    </div>
</div>

<script src="assets/js/jquery-1.11.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script></script>

</body>
</html>
