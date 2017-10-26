<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../common.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>

    <title></title>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
		</span>系统管理<span class="c-gray en">&gt;</span>运营商管理<a
        class="btn btn-success radius r"
        style="line-height:1.6em;
	margin-top:3px"
        href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i> </a>
</nav>
<div class="page-container">
    <div class="cus_hei_30">
        <shiro:hasPermission name='401'>
            <button id="phoneOperatorAdd" class="btn btn-primary radius"
                    type="button">
                <i class="Hui-iconfont">&#xe600;</i>&nbsp;添加运营商
            </button>
        </shiro:hasPermission>

        <span class="f-r">
			运营商名称：
				<span class="select-box radius cus_wid_100">
					<select class="select" id="searchOperator" name="searchOperator" size="1">
							<option value="">全部</option>
							<option value="1">中国联通</option>
							<option value="2">中国移动</option>
							<option value="3">中国电信</option>
					</select>
				</span>
				<button name="" id="phoneOperatorSearch" class="btn btn-success radius" type="button">
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
                <th width="80">手机号码前缀</th>
                <th width="150">运营商名称</th>
                <th width="150">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/phoneOperator/phoneOperator.js"></script>
<script type="text/javascript">
    function initTable() {
        phoneOperatorTable = $('#tableList')
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
                        "url": ctx + "/phoneOperator/getPhoneOperatorByCondition"
                    },
                    "columns": [{
                        "data": null,
                        "targets": 0
                    }, {
                        "data": "id",
                        "visible": false
                    }, {
                        "data": "prefix"
                    }, {
                        "data": "operatorName"
                    }],
                    "columnDefs": [{
                        "targets": [4],
                        "data": "id",
                        "render": function (data, type, full) {
                            var toUpdatePhoneOperator = "<shiro:hasPermission name='402'>"
                                + "<a href='javascript:void(0)' onClick=toUpdatePhoneOperator('"
                                + data
                                + "')>修改</a>&nbsp;&nbsp;</shiro:hasPermission>";
                            var deletePhoneOperator = "<shiro:hasPermission name='403'>"
                                + "<a href='javascript:void(0)'onClick=deletePhoneOperator('"
                                + data
                                + "')>删除</a>&nbsp;&nbsp;</shiro:hasPermission>";

                            return toUpdatePhoneOperator + deletePhoneOperator;
                        }
                    }],
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
</body>
</html>