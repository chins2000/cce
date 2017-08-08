<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/common/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>index</title>

<%@include file="WEB-INF/jsp/common/head.jsp"%>
<!-- 引入js,css,jsp -->
<style>
.maxheight {
	height: 100%;
}

.maxwidth {
	width: 100%;
}

html, body {
	height: 100%;
}
.menus{
	border: 1px solid #ddd;
	background-color:#efe;
	text-align: center;
}
</style>
</head>
<body class="maxheight">
	<div class="container maxheight">
		<div class="row" style="height: 20%;">
			<h1 align="center">权限管理系统<small><small>&nbsp;&nbsp;&nbsp;--&nbsp;软开1431.陈诗衡</small></small></h1>
		</div>
		<div class="row" style="height: 80%;">
			<div class="col-md-2" style="border-right: 1px solid #000;">
				<ul class="nav nav-pills nav-stacked">
					<c:forEach items="${menus }" var="menu">
						<li><a class="menus" href="<%=path %>/${menu.url }?permissionIdMark=${menu.permissionId}" target="mainiframe">${menu.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-10 maxheight">
				<iframe name="mainiframe" src="" class="maxheight maxwidth" frameborder="0"
					scrolling="no"> </iframe>
			</div>
		</div>
	</div>
	<div style="width: 30px; position: absolute; right: 10px;top: 10px;">
		<a href="<%=basePath %>user/logout">退出</a>
	</div>
	<!-- 引入js,css,jsp -->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script
		src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- zDialog弹出窗插件 -->
	<script src="/permission_manager/resources/zDialog/zDialog.js"></script>
	<script src="/permission_manager/resources/zDialog/zDrag.js"></script>
	<script type="text/javascript">
		$("li").each(function() {
			$(this).on("click", function() {
				$("li").each(function() {
					$(this).removeClass("active");
				});
				$(this).addClass("active");
			});
		});
		var diag = new Dialog();
		function openDialog(url){
			diag.URL = url;
			diag.ShowButtonRow=false;
			diag.show();
		}
		function closeDialog(){
			diag.close();
		}
	</script>
</body>
</html>