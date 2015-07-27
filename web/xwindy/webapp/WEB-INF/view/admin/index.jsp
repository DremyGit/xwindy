<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/jspHead.jsp" %>
    <meta charset="utf-8">
    <title>后台管理系统</title>
    <style>
        iframe{
            border: none;
        }

    </style>
</head>
<frameset rows="80px, 100%" framespacing="0" border="0" frameborder="0"noresize="noresize" style="width: 1000px">
    <frame src="admin/frame/top" >
    <frameset cols="150px, 100%" framespacing="0" border="0" frameborder="0" noresize="noresize">
        <frame src="admin/frame/menu" name="menu_frame">
        <frame src="admin/main" name="main_frame">
    </frameset>
</frameset>
</html>
