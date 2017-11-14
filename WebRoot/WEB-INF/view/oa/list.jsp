<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="../common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/ui/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/ui/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${ctx}/ui/static/h-ui.admin/css/style-1.1.0.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>订单查询</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>给媳妇用的<span class="c-gray en">&gt;</span>打卡列表
		<a	class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"	href="javascript:location.replace(location.href);" title="刷新">
		<i class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		<div class="cus_hei_30">
			<span class="f-l">

				姓名:<input type="text" name="signInName" id="signInName" placeholder="姓名" class="input-text c_w-180 radius">
				 接收时间：
                    <input type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\');}',dateFmt:'yyyy-MM-dd'})" id="begindate" name="begindate" class="input-text Wdate" style="width:120px;">
                                         至&nbsp;&nbsp;
                    <input type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begindate\');}',dateFmt:'yyyy-MM-dd'})" id="enddate" name="enddate" class="input-text Wdate" style="width:120px;">
				状态:
				<span class="select-box radius c_w-120">
					<select class="select inline" id="singInState" name="singInState">
						<option value="0">正常上班</option>
						<option value="1">迟到</option>
						<option value="2">请假</option>
						<option value="3">休息</option>
						<option value="4">加班</option>
					</select>
				</span>
				<button name="" id="orderbatchSearch" class="btn btn-success radius" type="button">
					<i class="Hui-iconfont">&#xe665;</i>&nbsp;查询
				</button>
				<button name="" id="outputButton" class="btn btn-success radius" type="button"><i class="Hui-iconfont">&#xe644;</i>&nbsp;导出</button>
			</span>
			<span class="f-r">

				<button name="" id="add" class="btn btn-success radius" type="button">
					<i class="Hui-iconfont">&#xe600;</i>&nbsp;添加
				</button>
			</span>

		</div>
		<div class="mt-20">
			<table id="tableList"
				class="table table-striped table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="30">序号</th>
						<th width="30">ID</th>
						<th width="60">姓名</th>
						<th width="260">上班时间</th>
						<th width="340">下班时间</th>
						<th width="200">状态</th>
						<th width="260">迟到（分钟）</th>
						<th width="340">请假（分钟）</th>
						<th width="260">加班（分钟）</th>
						<th width="80">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/oa/oalist.js"></script>
<script type="text/javascript">
    function initTable() {
        tableList = $('#tableList')
            .dataTable(
                {
                    "searching": false,
                    "lengthChange": false,
                    "processing": true,
                    "ordering": false,
                    "serverSide": true,
                    "pageLength": ${pageLength},
                    "pagingType": "full_numbers",
                    "ajax": {
                        "type": "post",
                        "url": ctx + "/oa/getSignInfoByCondition"
                    },
                    "columns": [{
                        "data": null,
                        "targets": 0
                    }, {
                        "data": "id",
                        "visible":false
                    }, {
                        "data": "signInName"
                    }, {
                        "data": "startDate"
                    }, {
                        "data": "endDate"
                    },{
                        "data": "signInState"
                    } ,{
                        "data": "lateMinutes"
                    } ,{
                        "data": "addMinutes"
                    } ,{
                        "data": "leaveMinutes"
                    } ,{
                        "data": "remark"
                    }],
                    "columnDefs" : [ {
                        "targets" : [ 9 ],
                        "data" : "id",
                        "render" : function(data, type, full) {
                            return "<a href='javascript:void(0)' onClick=update('" + full.appId + "')>修改</a>&nbsp;&nbsp;";
                        }
                    } ],
                    "fnDrawCallback": function () {
                        var api = this.api();
                        var startIndex = api.context[0]._iDisplayStart;// 获取到本页开始的条数
                        api.column(0).nodes().each(
                            function (cell, i) {
                                cell.innerHTML = startIndex + i
                                    + 1;
                            });
                    }
                });
    }
</script>
</html>