<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=BASE_PATH%>">云上宣风</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
<%--                 <li><a href="<%=BASE_PATH%>">首页</a></li> --%>
                <li class="active"><a href="<%=BASE_PATH%>/news">资讯</a></li>
                <li><a href="<%=BASE_PATH%>/query/lib">查询</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${isLogin == null }">
                        <li><a href="<%=BASE_PATH%>/user/login">登录</a></li>
                        <li><a href="<%=BASE_PATH%>/user/register">注册</a></li>
                    </c:when>
                
	                <c:otherwise>
		                <li class="dropdown">
		                    <a href="javascript:;" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户${username} <span class="caret"></span></a>
		                    <ul class="dropdown-menu">
		                        <li><a href="javascript:;">个人信息</a></li>
		                        <li role="separator" class="divider"></li>
		                        <li><a href="javascript:;" onclick="logout()">注销用户</a></li>
		                    </ul>
		                </li>
		                <script>
		                function logout() {
		                	$.get('<%=BASE_PATH%>/user/logout.action', function() {
		                		location.href = '<%=BASE_PATH%>';
		                	})
		                }
		                </script>
	               </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>