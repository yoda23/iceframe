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

<title>行政区域</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
		</span>系统管理<span class="c-gray en">&gt;</span>行政区域<a
			class="btn btn-success radius r"
			style="line-height:1.6em;
	margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		<div class="mt-20">
			<table id="tableList"
				class="table table-striped table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="30">序号</th>
						<th width="40">id</th>
						<th width="120">省份</th>
						<th width="120">城市</th>
						<th width="120">地区</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/region/region.js"></script>
	<script type="text/javascript">
		function initRegionTable() {
			regionTable = $('#tableList').dataTable({
				"searching" : false,
				"lengthChange" : false,
				"processing" : true,
				"ordering" : false,
				"serverSide" : true,
				"pageLength" : 50,
				"pagingType" : "full_numbers",
				"ajax" : {
					"type" : "post",
					"url" : ctx + "/region/getRegionByCondition",
					"data" : {

					},
				},
				"columns" : [ {
					"data" : null,
					"targets" : 0
				}, {
					"data" : "id",
					"visible" : false
				}, {
					"data" : "province"
				}, {
					"data" : "city"
				}, {
					"data" : "area"
				}, ],
				"fnDrawCallback" : function() {
					var api = this.api();
					var startIndex = api.context[0]._iDisplayStart;// 获取到本页开始的条数
					api.column(0).nodes().each(function(cell, i) {
						cell.innerHTML = startIndex + i + 1;
					});
				}
			});
		}
	</script>
</body>
</html>