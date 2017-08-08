<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>add</title>

<%@ include file="../common/head.jsp"%>
<!-- 引入js,css,jsp -->
<style>
.container {
	margin-top: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<form class="form-horizontal">
			<div class="form-group">
				<div class="col-xs-3">属性名</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">属性名</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3 col-xs-offset-2">
					<button class="btn btn-primary">保存</button>
				</div>
				<div class="col-xs-3 col-xs-offset-2">
					<button class="btn btn-danger">取消</button>
				</div>
			</div>
		</form>
	</div>

	<!-- 引入js,css,jsp -->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script
		src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>