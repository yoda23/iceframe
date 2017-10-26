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

<title></title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
		</span>系统管理<span class="c-gray en">&gt;</span>号段管理<a
			class="btn btn-success radius r"
			style="line-height:1.6em;
	margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		<div class="cus_hei_30">
			<span class="f-r">
				<input type="text" id="phoneHeader" placeholder="号码前七位" class="input-text cus_wid_250 radius">
				<button name="" id="search" class="btn btn-success radius" type="button">
					<i class="Hui-iconfont">&#xe665;</i>&nbsp;查询
				</button>
			</span>
		</div>
		<div class="mt-20">
			<table id="tableList"
				class="table table-striped table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="80">序号</th>
						<th width="">id</th>
						<th width="80">号码前缀</th>
						<th width="80">号码类型</th>
						<th width="80">号段识别码</th>
						<th width="80">省份</th>
						<th width="80">城市</th>
						<th width="150">电话区号</th>
						<th width="150">邮编</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/phoneLocation/phoneLocation.js"></script>
	<script type="text/javascript">
		function initTable() {
			phoneLocationTable = $('#tableList')
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
									"url" : ctx + "/phoneLocation/getPhoneLocationByCondition",
								},
								"columns" : [ {
									"data" : null,
									"targets" : 0
								}, {
									"data" : "id",
									"visible" : false
								}, {
									"data" : "prefix"
								}, {
									"data" : "mobileType"
								}, {
									"data" : "phoneHeader"
								}, {
									"data" : "province"
								}, {
									"data" : "city"
								}, {
									"data" : "areaCode"
								}, {
									"data" : "postCode"
								}, ],
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