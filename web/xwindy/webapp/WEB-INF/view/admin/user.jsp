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

        #userType{width: 16em;}
        #username{width: 16em;}
        #password{width: 16em;}
        #phone{width: 16em;}
        #email{width: 16em;}
        #info{width: 16em;}
        #recommend{width: 6em;}
    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>后退</a></li>
    <li><a href="admin"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 主页</a></li>
    <li><a href="admin/student">用户管理</a></li>
    <li class="active">用户信息${mode=="add"?"添加":"编辑"}</li>
</ol>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">用户信息${mode=="add"?"添加":"编辑"}</h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">

          <c:if test="${mode == 'add'}">
            <tr>
                <td class="td-label">公众号类型</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 公众号用户
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 报修号用户
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> 招领号用户
                    </label>
                </td>
            </tr>
          </c:if>  
          <c:if test="${mode == 'add' || publicer.userType == 'GZH'}">
            <tr>
                <td class="td-label">资讯号分类</td>
                <td>
                    <select id="userType" class="form-control">
                        <option value="0" selected>请选择</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </td>
            </tr>
          </c:if>  
            
            <!-- 全显 -->
            <tr>
                <td class="td-label">用户名 </td>
                <td><input type="text" id="username" class="form-control" value="${publicer.username}">
                </td>
            </tr>
            <tr>
                <td class="td-label">头像</td>
                <td><img src="${publicer.header}"></td>
            </tr>
            <tr>
                <td class="td-label">密码</td>
      <c:choose>
          <c:when test="${mode == 'add'}">
                <td><input type="text" id="password" class="form-control"></td>
          </c:when>
          <c:when test="${mode == 'edit'}">
                <td><button class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">修改密码</button></td>
          </c:when>
      </c:choose>
            </tr>
            <tr>
                <td class="td-label">手机</td>
                <td><input type="text" id="phone" class="form-control" value="${publicer.telNumber}"></td>
            </tr>
            <tr>
                <td class="td-label">邮箱</td>
                <td><input type="text" id="email" class="form-control" value="${publicer.email}"></td>
            </tr>
            
      <c:if test="${publicer.userType != 'XS'}">
            <!-- 用户类型不为学生则显示 -->
            <tr>
                <td class="td-label">简介</td>
                <td><input type="text" id="info" class="form-control" value="${publicer.introduce}"></td>
            </tr>
      </c:if>

      <c:if test="${publicer.userType == 'GZH'}">
            <!-- 资讯公众号显示 -->
            <tr>
                <td class="td-label">推荐指数</td>
                <td><input type="text" id="recommend" class="form-control" value="${publicer.recommendNum}"></td>
            </tr>
      </c:if>
            
            <tr>
                <td colspan="2"><button class="btn btn-primary" id="${mode=='edit'?'edit':'add'}">${mode=='edit'?'修改':'添加'}</button></td>
            </tr>
        </table>
      
    </div>
</div>

<c:if test="${mode == 'edit'}">
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
                <input type="text" id="input-password" class="form-control"><br>
                <button type="button" class="btn btn-primary">修改</button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</c:if>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script></script>

</body>
</html>
