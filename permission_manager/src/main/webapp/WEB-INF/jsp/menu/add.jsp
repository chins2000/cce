<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
.col-xs-3{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" action="<%=path%>/menu/add"
			method="post">
			<div class="form-group">
				<div class="col-xs-3">链接:</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="url" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">名称:</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="name" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">上级菜单:</div>
				<div class="col-xs-9">
					<input placeholder="点击选择上级菜单" class="form-control" id="menuName" type="text" readonly value="" onclick="showMenu_m();"  />
					<input id="menuId" type="hidden" name="pid" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">所需权限:</div>
				<div class="col-xs-9">
					<input placeholder="点击选择权限" class="form-control" id="permissionName" type="text" readonly value="" onclick="showMenu_p();"  />
					<input id="permissionId" type="hidden" name="permissionId" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3 col-xs-offset-2">
					<button id="save" class="btn btn-primary">保存</button>
				</div>
				<div class="col-xs-3 col-xs-offset-2">
					<button id="close" class="btn btn-danger">取消</button>
				</div>
			</div>
		</form>
		<div id="menuContent_p" class="menuContent"
			style="display: none; position: absolute; background-color: #fff;">
			<ul id="tree_p" class="ztree"
				style="margin-top: 0; width: 180px; height: 300px;"></ul>
		</div>
		<div id="menuContent_m" class="menuContent"
			style="display: none; position: absolute; background-color: #fff;">
			<ul id="tree_m" class="ztree"
				style="margin-top: 0; width: 180px; height: 300px;"></ul>
		</div>
	</div>

	<!-- 引入js,css,jsp -->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<link rel="stylesheet"
		href="/permission_manager/resources/zTree_v3-master/css/zTreeStyle/zTreeStyle.css"
		type="text/css">
	<script
		src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="/permission_manager/resources/zTree_v3-master/js/jquery.ztree.core.js"></script>
	<script type="text/javascript"
		src="/permission_manager/resources/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript">
		$("#save").on("click", function() {
			$($(".form-horizontal")[0]).submit();
		});
		$("#close").on("click", function() {
			parentDialog.close();
		});
		
		//-------------permission下拉列表树
		var setting_p = {
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onClick : onClick_p
			}
		};
		var zNodes_p = ${permissionNodes};

		function onClick_p(e, treeId, treeNode) {
			if(!treeNode.isParent){
				var id = treeNode["id"];
				var name = treeNode["name"];
				var permissionNameObj = $("#permissionName");
				var permissionIdObj = $("#permissionId");
				permissionIdObj.attr("value", id);
				permissionNameObj.attr("value",name);
			}
		}

		function showMenu_p() {
			var permissionNameObj = $("#permissionName");
			var cityOffset = $("#permissionName").offset();
			$("#menuContent_p").css({
				left : cityOffset.left + "px",
				top : cityOffset.top + permissionNameObj.outerHeight() + "px"
			}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown_p);
		}
		function hideMenu_p() {
			$("#menuContent_p").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown_p);
		}
		function onBodyDown_p(event) {
			if (!(event.target.id == "permissionName"
					|| event.target.id == "menuContent_p" || $(event.target)
					.parents("#menuContent_p").length > 0)) {
				hideMenu_p();
			}
		}
		//-------------menu下拉列表树
		var setting_m = {
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onClick : onClick_m
			}
		};
		var zNodes_m = ${menuNodes};

		function onClick_m(e, treeId, treeNode) {
			var id = treeNode["id"];
			var name = treeNode["name"];
			var menuNameObj = $("#menuName");
			var menuIdObj = $("#menuId");
			menuIdObj.attr("value", id);
			menuNameObj.attr("value",name);
		}

		function showMenu_m() {
			var menuNameObj = $("#menuName");
			var cityOffset = $("#menuName").offset();
			$("#menuContent_m").css({
				left : cityOffset.left + "px",
				top : cityOffset.top + menuNameObj.outerHeight() + "px"
			}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown_m);
		}
		function hideMenu_m() {
			$("#menuContent_m").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown_m);
		}
		function onBodyDown_m(event) {
			if (!(event.target.id == "menuName"
					|| event.target.id == "menuContent_m" || $(event.target)
					.parents("#menuContent_m").length > 0)) {
				hideMenu_m();
			}
		}
		//-------------初始化zTree
		$(document).ready(function() {
			$.fn.zTree.init($("#tree_p"), setting_p, zNodes_p);
			$.fn.zTree.init($("#tree_m"), setting_m, zNodes_m);
		});
	</script>
</body>
</html>