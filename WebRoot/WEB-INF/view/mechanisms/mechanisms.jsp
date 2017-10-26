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


    <title>地市管理</title>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
		</span>系统管理<span class="c-gray en">&gt;</span>地市管理
    <a class="btn btn-success radius r" style="line-height:1.6em; margin-top:3px"
       href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="formControls row cl col-sm-2">
    <div class="mt-15 mb-15 ml-15 c-hs c_h-850">
        <input type="hidden" class="input-text" value="" placeholder=""
               id="mechanismsId" name="mechanismsId" readonly="readonly">
        <ul id="mechanismsTree" class="ztree"></ul>
    </div>
</div>
<div class="page-container  col-sm-10">
    <div class="cus_hei_30">
        <shiro:hasPermission name='301'>
            <button name="" id="mechanismsAdd" class="btn btn-primary radius"
                    type="button">
                <i class="Hui-iconfont">&#xe600;</i>&nbsp;添加地市
            </button>
        </shiro:hasPermission>
        <span class="f-r">
            <input type="text" id="mechanismsName" placeholder="地市名称" name="mechanismsName"
                   class="input-text cus_wid_250 radius"/>
				<button name="" id="mechanismsSearch" class="btn btn-success radius" type="button">
					<i class="Hui-iconfont">&#xe665;</i>&nbsp;查询
                </button>
        </span>
    </div>
    <div class="mt-20">
        <table id="tableList"
               class="table table-border table-striped table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="40">序号</th>
                <th width="80">地市Id</th>
                <th width="80">地市名称</th>
                <th width="80">上级地市名称</th>
                <th width="80">地市简称</th>
                <th width="80">负责人</th>
                <th width="80">电话号码</th>
                <th width="80">地市编码</th>
                <th width="40">添加人</th>
                <th width="40">添加时间</th>
                <th width="120">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/mechanisms/mechanisms.js"></script>
<script type="text/javascript">
    function initMechanismsTable() {
        mechanismsTable = $('#tableList')
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
                        "url": ctx
                        + "/mechanisms/getListMechanismByCondition"
                    },
                    "columns": [{
                        "data": null,
                        "targets": 0
                    }, {
                        "data": "id",
                        "visible": false
                    }, {
                        "data": "name"
                    }, {
                        "data": "parentName"
                    }, {
                        "data": "simpleName"
                    }, {
                        "data": "principal"
                    }, {
                        "data": "phone"
                    }, {
                        "data": "code"
                    }, {
                        "data": "userNameDisplay"
                    }, {
                        "data": "addTime"
                    }],
                    "columnDefs": [{
                        "targets": [10],
                        "data": "id",
                        "render": function (data, type, full) {
                            var updateMechanisms =
                                "<shiro:hasPermission name='302'><a href='javascript:void(0)' onClick=toShowUpdateMechanisms('"
                                + data
                                + "')>修改</a>&nbsp;&nbsp;</shiro:hasPermission> ";
                            var deleteMechanisms =
                                "<shiro:hasPermission name='303'><a href='javascript:void(0)' onClick=deleteMechanisms('"
                                + full.id
                                + "')>删除</a></shiro:hasPermission>";
                            return updateMechanisms + deleteMechanisms;
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
