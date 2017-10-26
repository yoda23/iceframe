<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>角色管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
		</span>系统管理<span class="c-gray en">&gt;</span>角色管理<a
			class="btn btn-success radius r"
			style="line-height:1.6em;
	margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		<div class="cus_hei_30">
			<shiro:hasPermission name='201'>
				<button name="" id="roleAdd" class="btn btn-primary radius"
					type="button">
					<i class="Hui-iconfont">&#xe600;</i>&nbsp;添加角色
				</button>
			</shiro:hasPermission>
			<span class="f-r"> <input type="text" id="roleName"
				placeholder="角色名称" name="roleName"
				class="input-text cus_wid_250 radius">
				<button name="" id="roleSearch" class="btn btn-success radius"
					type="button">
					<i class="Hui-iconfont">&#xe665;</i>&nbsp;查询
				</button>
			</span>
		</div>
		<div class="mt-20">
			<table id="tableList"
				class="table table-border table-striped table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="20">序号</th>
						<th width="80">roleId</th>
						<th width="80">角色名称</th>
						<th width="150">角色描述</th>
						<th width="40">添加人</th>
						<th width="40">添加时间</th>
						<th width="120">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/role/role.js"></script>
	<script type="text/javascript">
		function initroleTable() {
			roleTable = $('#tableList')
					.dataTable(
							{
								"searching" : false,
								"lengthChange" : false,
								"processing" : true,
								"ordering" : false,
								"serverSide" : true,
								"pageLength" : ${pageLength},
								"pagingType" : "full_numbers",
								"ajax" : {
									"type" : "post",
									"url" : ctx + "/role/getRoleByCondition",
								},
								"columns" : [ {
									"data" : null,
									"targets" : 0
								}, {
									"data" : "id",
									"visible" : false
								}, {
									"data" : "name"
								}, {
									"data" : "reMark"
								}, {
									"data" : "addUser_display"
								}, {
									"data" : "addTime"
								} ],
								"columnDefs" : [ {
									"targets" : [ 6 ],
									"data" : "id",
									"render" : function(data, type, full) {
										return "<shiro:hasPermission name='202'><a href='javascript:void(0)' onClick=toUpdateRole('"
												+ data
												+ "')>修改</a>&nbsp;&nbsp;</shiro:hasPermission>"
												+ "<shiro:hasPermission name='203'><a href='javascript:void(0)' onClick=deleteRole('"
												+ data
												+ "')>删除</a>&nbsp;&nbsp;</shiro:hasPermission>"
												+ "<shiro:hasPermission name='204'><a href='javascript:void(0)' onClick=toUpdateMenu('"
												+ data
												+ "')>分配菜单</a>&nbsp;&nbsp;</shiro:hasPermission>"
												+ "<shiro:hasPermission name='205'><a href='javascript:void(0)' onClick=toUpdateRights('"
												+ data
												+ "')>分配权限</a></shiro:hasPermission>";
									}
								} ],
								"fnDrawCallback" : function() {
									var api = this.api();
									var startIndex = api.context[0]._iDisplayStart;// 获取到本页开始的条数
									api.column(0).nodes().each(
											function(cell, i) {
												cell.innerHTML = startIndex + i
														+ 1;
											});
								}
							});
		}
	</script>
</body>
</html>