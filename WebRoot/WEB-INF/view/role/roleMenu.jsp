<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title></title>
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="roleMenuForm">
			<input type="hidden" id="id" name="id" value="${role.id}">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>角色名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="style_input-disabled radius cus_wid_400" placeholder="角色名称" id="name"
						name="name" value="${role.name}" disabled="disabled">
				</div>
			</div>
			<input type="hidden" id="menuId" name="menuId" value="${role.menuId}">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">菜单管理：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box cus_wid_400 radius">
						<ul id="roleAddMenu" class="ztree"></ul>
					</span>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input id="updateMenuForMenu" class="btn btn-primary radius"
						type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"> <input
						id="close" class="btn btn-primary radius" type="button"
						value="&nbsp;&nbsp;关闭&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>
<script type="text/javascript" src="${ctx}/js/role/role.menu.js"></script>
<script type="text/javascript">
function createTree(){
	$.ajax({
		url : '${ctx}/menu/getListMenu',
		type : 'POST',
		success : function(data) {
			jQuery.fn.zTree.init(jQuery("#roleAddMenu"), setting,
					eval('(' + data + ')'));
			var treeObj = jQuery.fn.zTree.getZTreeObj("roleAddMenu");
			treeObj.expandAll(true);
			var menuId = "${role.menuId}";
			var arrys = menuId.split(",");
			if (menuId != "") {
				for (var i = 0; i < arrys.length; i++) {
					var node = treeObj.getNodeByParam("id", arrys[i],
							null);
					treeObj.checkNode(node, true, false, true);
				}
			}

		},
		error : function(msg) {
			layer.alert(msg);
		}
	});
}
</script>
</body>
</html>