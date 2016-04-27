<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/jspBegin.jsp" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/jspHead.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${isSuccess}
${isLogin}
${reason}
哈哈
<div id="state"></div>
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script>
$(function () {
	$("#state").text("查询中,请稍后");
	$.ajax({
		url: "query/lib.action",
		type: "GET",
		dataType: "JSON",
		success: function (data) {
			var res = eval(data);
			if (res.isSuccess) {
				var html = "<ul>";
				var b = res.borrowList;
				for (i in b) {
					html += "<li><ul><li>书名: " + b[i].bookName + "</li><li>借阅日期: " + b[i].borrowDate + "</li><li>应还日期" + b[i].expectDate
					      + "</li><li>有无附件: " + b[i].haveAttach + "</li><li><a href='" + b[i].renewHref + "'>一键续借</a></li></ul></li>";
				}
				html += "</ul>";
				$("#state").after(html).text("查询成功");
			} else {
				alert("查询失败, 原因: " + res.reason);
			}
		},
		error: function () {
			alert("连接错误");
		}
	});
});
</script>
</body>
</html> --%>
<!DOCTYPE html>
<html>
<head lang="zh-cn">
  <title></title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0">
  <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=BASE_PATH%>/assets/css/global.css">
  <style>
    .title-bar{
      height: 40px;
      background: #3399cc;
      color: #fff;;
    }
    .title-bar>h3{
      margin: 0 15px;
      padding-top: 10px;
      font-size: 16px;
    }
    #lib-query {
        min-height: 600px;
    }
  </style>
</head>
<body>

<%@ include file="../include/navbar.jsp" %>
<div class="container">
  <div class="bar-20"></div>
  <div class="row">
    <div class="col-sm-3">
      <div class="title-bar">
        <h3>信息查询</h3>
      </div>
      <div class="card item-list">
        <a class="item">图书借阅查询</a>
        <a class="item">体育打卡查询</a>
      </div>
    </div>
    <div class="col-sm-9">
      <div class="card" id="lib-query">
        <h3>图书借阅查询</h3>
        <div class="bar-20"></div>
        <div id="main-info">
          <p>点击按钮查询你的图书借阅信息</p>
        <div class="bar-20"></div>
        <div class="bar-20"></div>
          <center><button class="btn xf-btn" id="query">&nbsp;&nbsp;查询&nbsp;&nbsp;</button></center>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="<%=BASE_PATH%>/assets/js/jquery-1.11.2.min.js"></script>
<script src="<%=BASE_PATH%>/assets/js/bootstrap.min.js"></script>
<script>
  $("#query").click(function () {

    $("#main-info").html("<h3>查询中...</h3>");
    $.ajax({
      url: '<%=BASE_PATH%>/query/lib.action',
      success: function(res){
        var obj = res;
        if (obj.isSuccess != true) {
          alert(obj.reason);
          return;
        }
        $("#main-info").html("");
        var table = '<table class="table table-bordered table-hover table-list">' +
            '<thead>' +
            '<th>书名</th>' +
            '<th>借阅日期</th>' +
            '<th>应还日期</th>' +
            '<th>续借</th>' +
            '</thead>' +
            '<tbody>';
        var i;
        var list = obj.borrowList;
        for (i = 0; i < list.length; i++) {
          table += '<tr>' +
              '<td>' + list[i].bookName + '</td>' +
              '<td>' + list[i].borrowDate + '</td>' +
              '<td>' + list[i].expectDate + '</td>' +
              '<td><button class="btn-renew btn xf-btn" onclick="renew(' + list[i].renewHref + ')">续借</button></td>' +
              '</tr>'
        }
        table += '</tbody></table>';
        $("#main-info").html(table);
      },
      error: function () {
        alert("网络错误")
      }
    })
  })
</script>

</body>
</html>
