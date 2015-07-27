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
        #label-title{
            width: 200px;
        }
    </style>
    <link rel="stylesheet" href="assets/js/kindeditor/themes/default/default.css" />
    <script charset="utf-8" src="assets/js/kindeditor/kindeditor-min.js"></script>
    <script charset="utf-8" src="assets/js/kindeditor/lang/zh_CN.js"></script>
    <script>
        var editor;
        KindEditor.ready(function(K) {
            editor = K.create('textarea[name="content"]', {
                width: "100%",
                height: "400px",
                resizeType : 1,
                allowPreviewEmoticons : false,
                allowImageUpload : true,
                allowImageRemote: false,
                cssPath: ["../assets/css/edit.css","../../assets/css/edit.css"],
                uploadJson: "/upload_json.php",
                items : [
                    'source', 'preview', '|', 'formatblock', 'bold', 'italic', '|', 'justifyleft',
                    'justifycenter', 'justifyright', 'insertunorderedlist', '|', 'link', 'image',
                    'clearhtml'],
                urlType: "domain",
                pasteType: 1,
                dialogAlignType: ""
            });
//             editor.html('<p></p>');
        });
    </script>

</head>
<body>
<ol class="breadcrumb">
    <li><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>后退</a></li>
    <li><a href="admin"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 主页</a></li>
    <li><a href="admin/student">资讯列表</a></li>
    <li class="active">资讯编辑</li>
</ol>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">资讯编辑</h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <tr>
                <td class="td-label" id="label-title">资讯标题 </td>
                <td><input type="text" id="title" class="form-control" value="${news.title}"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <textarea id="content" name="content" style="width:700px;height:200px;visibility:hidden;">${news.content}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2"><button class="btn btn-primary" id="submit">修改</button></td>
            </tr>
        </table>
    </div>
</div>

<script src="assets/js/jquery-1.11.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script>
    function search() {
        var str = $("#input-search").val();
        alert("搜索"+str);
    }
</script>

</body>
</html>
