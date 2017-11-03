﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
		</span>投票管理<span class="c-gray en">&gt;</span>公众号管理<a
        class="btn btn-success radius r"
        style="line-height:1.6em;
	margin-top:3px"
        href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i> </a>
</nav>
<div class="page-container">
    <div class="cus_hei_30">
            <button id="gzhAdd" class="btn btn-primary radius"
                    type="button">
                <i class="Hui-iconfont">&#xe600;</i>&nbsp;添加公众号
            </button>
    </div>
    <div class="mt-20">
        <table id="tableList"
               class="table table-striped table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">序号</th>
                <th width="80">id</th>
                <th width="80">appid</th>
                <th width="150">appsecret</th>
                <th width="150">公众号名称</th>
                <th width="150">描述</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/gzh/gzhList.js"></script>
<script type="text/javascript">
    function initTable() {
        console.log(ctx);
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
                        "url": ctx + "/gzh/getAppInfoByCondition"
                    },
                    "columns": [{
                        "data": null,
                        "targets": 0
                    }, {
                        "data": "id",
                        "visible":true,
                    }, {
                        "data": "appId"
                    }, {
                        "data": "appSecret"
                    }, {
                        "data": "appName"
                    }, {
                        "data": "remark"
                    }, {
                        "data": "createDate"
                    }],
                    "columnDefs" : [ {
                        "targets" : [ 7 ],
                        "data" : "id",
                        "render" : function(data, type, full) {
                            return "<a href='javascript:void(0)' onClick=sendText('"
                                + full.appId
                                + "','"+full.appSecret+"')>群发</a>&nbsp;&nbsp;";

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
</body>
</html>