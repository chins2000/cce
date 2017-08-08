<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>list</title>

<%@ include file="../common/head.jsp"%>
<!-- 引入js,css,jsp -->
<style>
.row {
	margin-top: 20px;
}

.glyphicon {
	cursor: pointer;
	margin-left: 5px;
}

.mycheckbox {
	width: 17px;
	height: 17px;
	float: left;
}
</style>
</head>
<body>
	<div class="container">
		<form class="form-horizontal">
			<!-- 搜索栏 -->
			<div class="row">
				<div class="col-xs-3 col-xs-offset-6">
					<input class="form-control" type="text" placeholder="输入搜索条件"
						maxlength="50" />
				</div>
				<div class="col-xs-2">
					<button class="btn form-control btn-info">搜索</button>
				</div>
			</div>

			<!-- 字段名 -->
			<div class="row">
				<div class="col-xs-3">
					<label><input type="checkbox"
						class="form-control mycheckbox" id="maincheck" /><span>全选</span></label>
				</div>
				<div class="col-xs-3">
					<p>用户名</p>
				</div>
				<div class="col-xs-3">
					<p>密码</p>
				</div>
				<div class="col-xs-3">
					<span class="glyphicon glyphicon-plus" title="添加"></span> <span
						class="glyphicon glyphicon-trash" title="删除"></span>
				</div>
			</div>

			<!-- 字段值 -->
			<div class="row">
				<div class="col-xs-3">
					<label><input type="checkbox"
						class="form-control mycheckbox" /><span>选择</span></label>
				</div>
				<div class="col-xs-3">
					<p>username</p>
				</div>
				<div class="col-xs-3">
					<p>password</p>
				</div>
				<div class="col-xs-3">
					<span class="glyphicon glyphicon-pencil" title="修改"></span> <span
						class="glyphicon glyphicon-trash" title="删除"></span>
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
	<script type="text/javascript">
		$("#maincheck").on("click", function() {
			$maincheck = $(this);
			$(":checkbox").each(function() {
				$(this).prop("checked", $maincheck.prop("checked"));
			});
		});
	</script>
</body>
</html>