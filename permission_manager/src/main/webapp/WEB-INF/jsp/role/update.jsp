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
<title>update</title>

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
		<form class="form-horizontal" action="<%=path%>/role/update"
			method="post">
			<!-- id -->
			<input type="hidden" class="form-control" name="id" value="${role.id }" />
			<div class="form-group">
				<div class="col-xs-3">名称:</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="name" value="${role.name }" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">分组:</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="group" value="${role.group }" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">角色:</div>
				<div class="col-xs-9">
					<input placeholder="点击选择角色" class="form-control" id="permissionNames" type="text" readonly value="${permissionNames }" onclick="showMenu();"  />
					<input id="permissionIds" type="hidden" name="permissionIds" value='${permissionIds }' />
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
		<div id="menuContent" class="menuContent"
			style="display: none; position: absolute; background-color: #fff;">
			<ul id="treeDemo" class="ztree"
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
		var setting = {
			check : {
				enable : true,
				chkboxType : {
					"Y" : "",
					"N" : ""
				}
			},
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				beforeClick : beforeClick,
				onCheck : onCheck
			}
		};

		var zNodes = ${zNodes};
		console.log(zNodes);
		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}

		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree.getCheckedNodes(true), v = "", ids="[";
			for (var i = 0, l = nodes.length; i < l; i++) {
				var childrenTree=null;
				if(nodes[i].isParent){
					var pNode = nodes[i];
				}else{
					continue;
				}
				childrenTree = pNode.children;
				ids +='{"id":"' + pNode.id + '",';
				ids +='"'+childrenTree[0].name+'":' + childrenTree[0].checked + ',';
				ids +='"'+childrenTree[1].name+'":' + childrenTree[1].checked + ',';
				ids +='"'+childrenTree[2].name+'":' + childrenTree[2].checked + ',';
				ids +='"'+childrenTree[3].name+'":' + childrenTree[3].checked + "},";
				
				v += nodes[i].name + ",";
			}
			if (v.length > 0)
				v = v.substring(0, v.length - 1);
			if (ids.length > 1)
				ids = ids.substring(0, ids.length - 1);
			ids+="]";
			console.log(ids);
			var permissionNamesObj = $("#permissionNames");
			var permissionIdsObj = $("#permissionIds");
			permissionIdsObj.attr("value", ids);
			permissionNamesObj.attr("value", v);
		}

		function showMenu() {
			var permissionNamesObj = $("#permissionNames");
			var cityOffset = $("#permissionNames").offset();
			$("#menuContent").css({
				left : cityOffset.left + "px",
				top : cityOffset.top + permissionNamesObj.outerHeight() + "px"
			}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "permissionNames"
					|| event.target.id == "menuContent" || $(event.target)
					.parents("#menuContent").length > 0)) {
				hideMenu();
			}
		}

		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>
</body>
</html>