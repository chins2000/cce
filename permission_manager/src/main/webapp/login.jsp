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
<title>login page</title>
<%@include file="WEB-INF/jsp/common/head.jsp"%>
<!-- 引入js,css,jsp -->
<style type="text/css">
.container {
	margin-top: 10%;
}

.row {
	margin: 5%;
}

.col-md-4 {
	padding-top: 8px;
}
.alert{
	display: none;
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form class="form-horizontal" action="" onsubmit="return checking()">
					<div class="row">
						<div class="col-md-4">
							<span>用户名:</span>
						</div>
						<div class="col-md-8">
							<input class="form-control" type="text" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<span>密码:</span>
						</div>
						<div class="col-md-8">
							<input class="form-control" type="password" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<a href="#" style="display: none;"><b>注册账号</b></a>
						</div>
						<div class="col-md-8">
							<button class="form-control btn btn-primary" id="login">登录</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="alert alert-info" role="alert">请输入完整的信息</div>
	<div class="alert alert-danger" role="alert">对不起，您输入的信息有误</div>
	<div class="alert alert-danger" id="msg" role="alert"></div>
	<!-- 引入js,css,jsp -->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script
		src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- jQuery兼容插件 -->
	<!-- <script src="http://cdn.bootcss.com/jquery-migrate/1.4.0/jquery-migrate.js"></script> -->
	<script type="text/javascript">
		function checking($username, $password) {
			if (!$username || $username.lenght < 1) {
				return false;
			}
			if (!$password || $password.lenght < 1) {
				return false;
			}
			return true;
		}

		$("#login").on("click", function() {
			var $username = $($(":text")[0]).val();
			var $password = $($(":password")[0]).val();
			if (checking($username, $password)) {
				$.post("<%=path%>/user/login", {
					"username" : $username,
					"password" : $password
				}, function(data) {
					if (data.result == "success") {
						window.location = "<%= basePath%>user/toindex";
					} else {
						if(data.errorMsg!=null){
							$info = $($("#msg"));
							$info.html(data.errorMsg);
							$info.slideDown("slow");
							setTimeout(function(){
								if($info){
									$info.slideUp("slow");
									$info=null;
								}
							}, 2000);
						}else{
							$info = $($(".alert-danger")[0]);
							$info.slideDown("slow");
							setTimeout(function(){
								if($info){
									$info.slideUp("slow");
									$info=null;
								}
							}, 2000);
						}
					}
				});
			} else {
				$info = $($(".alert-info")[0]);
				$info.slideDown("slow");
				setTimeout(function(){
					if($info){
						$info.slideUp("slow");
						$info=null;
					}
				}, 2000);
			}
			return false;
		});
	</script>
</body>
</html>