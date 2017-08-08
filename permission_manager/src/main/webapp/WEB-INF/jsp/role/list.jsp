<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>list</title>

<%@ include file="../common/head.jsp"%>
<!-- 引入js,css,jsp -->
<style>
.row {
	padding-top: 15px;
	padding-bottom: 5px;
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
#pageContains div{
	border: 1px solid #bbb;
	margin: 0 10px;
}
</style>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" action="" method="get">
			<input type="hidden" value="${permissionIdMark }" name="permissionIdMark">
			<!-- 搜索栏 -->
			<div class="row">
				<div class="col-xs-3 col-xs-offset-6">
					<input class="form-control" name="search" type="text" placeholder="输入搜索条件"
						maxlength="50" />
				</div>
				<div class="col-xs-2">
					<button class="btn form-control btn-info" id="searchbtn">搜索</button>
				</div>
			</div>

			<!-- 字段名 -->
			<div class="row" style="border: ">
				<div class="col-xs-3">
					<label><input type="checkbox"
						class="form-control mycheckbox" id="maincheck" /><span>全选</span></label>
				</div>
				<div class="col-xs-3">
					<p>名称</p>
				</div>
				<div class="col-xs-3">
					<p>分组</p>
				</div>
				<div class="col-xs-3">
					<span class="glyphicon glyphicon-plus" title="添加"></span>
					<span class="glyphicon glyphicon-trash" title="删除选中项"></span>
				</div>
			</div>
			<c:forEach items="${list }" var="item" varStatus="status">
			
			<!-- 字段值 -->
			<div id="values" class="row" ${(status.index mod 2) eq 0?'style="background-color:#eee;"':'' } >
				<div class="col-xs-3">
					<label><input type="checkbox"
						class="form-control mycheckbox" data-id="${item.id }"/><span>选择</span></label>
				</div>
				<div class="col-xs-3">
					<p>${item.name }</p>
				</div>
				<div class="col-xs-3">
					<p>${item.group }</p>
				</div>
				<div class="col-xs-3">
					<span class="glyphicon glyphicon-pencil" title="修改" data-id="${item.id }"></span>
					<span class="glyphicon glyphicon-trash" title="删除" data-id="${item.id }"></span>
				</div>
			</div>
			</c:forEach>
			
			<!-- 翻页 -->
			<div class="row" id="pageContains">
				<div class="col-xs-1 btn ${page.currentPage eq 1?'disabled':'' }" ${page.currentPage eq page.totalpage?'disabled':'' } data-page="${page.currentPage-1 }" >上一页</div>
				<c:forEach items="${page.showpages }" var="status" >
					<div class="col-xs-1 btn" data-page="${status }">${status }</div>
				</c:forEach>
				<div class="col-xs-1 btn ${page.currentPage eq page.totalpage?'disabled':'' }" ${page.currentPage eq page.totalpage?'disabled':'' } data-page="${page.currentPage+1 }">下一页</div>
				<input class="col-xs-1" id="pagenumber" type = "number" />
				<div class="btn btn-info btn-sm col-xs-1" style="margin-left: 3px;border: none;">Go</div>
			</div>
			<input type="hidden" name="totalNumber" value="${page.totalNumber }"/>
			<input type="hidden" name="pageNumber" value="${page.pageNumber }"/>
			<input type="hidden" name="totalpage" value="${page.totalpage }"/>
			<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage }"/>
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
		$("#searchbtn").on("click", function() {
			$thefrom = $($(".form-horizontal")[0]);
			$thefrom.attr("action","<%=path%>/role/search");
			$thefrom.submit();
		});
		$("#pageContains > div").each(function(){
			$(this).on("click", function() {
				if($(this).attr("disabled")=="disabled")
					return false;
				$thefrom = $($(".form-horizontal")[0]);
				$thefrom.attr("action","<%=path%>/role/searchpage");
				if($(this).html() && $(this).html()=="Go"){
					$("#currentPage").val($("#pagenumber").val()!=""?$("#pagenumber").val():0);
				}else{
					$("#currentPage").val($(this).attr("data-page"));
				}
				$thefrom.submit();
			});
		});
		$("span[title='添加']").on("click", function(){
			top.openDialog("<%=path%>/role/toadd");
		});
		$("span[title='修改']").on("click", function(){
			top.openDialog("<%=path%>/role/toupdate?id="+$(this).attr('data-id'));
		});
		$("span[title='删除']").on("click", function(){
			$.post("<%=path%>/role/delete",{"id":$(this).attr("data-id")});
		});
		$("span[title='删除选中项']").on("click", function(){
			var ids="";
			$("input:checked").each(function(){
				ids=ids+$(this).attr("data-id")+",";
			});
			alert(ids);
			ids=ids.substring(0, ids.length-1);
			$.post("<%=path%>/role/deleteMore",{"ids":ids});
		});
		
		
	</script>
</body>
</html>