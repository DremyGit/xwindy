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

        #username{width: 16em;}
        #phone{width: 16em;}
        #email{width: 16em;}
    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>后退</a></li>
    <li><a href="admin"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 主页</a></li>
    <li><a href="admin/student">学生用户管理</a></li>
    <li class="active">学生用户信息编辑</li>
</ol>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">学生用户信息编辑</h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <tr>
                <td class="td-label">用户名 </td>
                <td><input type="text" id="username" class="form-control">
                </td>
            </tr>
            <tr>
                <td class="td-label">头像</td>
                <td><img src=""></td>
            </tr>
            <tr>
                <td class="td-label">密码</td>
                <td><button class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">修改密码</button></td>
            </tr>
            <tr>
                <td class="td-label">手机</td>
                <td><input type="text" id="phone" class="form-control"></td>
            </tr>
            <tr>
                <td class="td-label">邮箱</td>
                <td><input type="text" id="email" class="form-control"></td>
            </tr>
            <tr>
                <td colspan="2"><button class="btn btn-primary" id="submit">修改</button></td>
            </tr>
        </table>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <p>输入新密码</p>
                <input type="text" id="password" class="form-control"><br>
                <button type="button" class="btn btn-primary">修改</button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script></script>

</body>
</html>
