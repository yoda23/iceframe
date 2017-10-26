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

    <title>用户管理</title>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
		</span>系统管理<span class="c-gray en">&gt;</span>用户管理<a
        class="btn btn-success radius r"
        style="line-height:1.6em;
	margin-top:3px"
        href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i> </a>
</nav>
<div class="page-container">
    <div class="cus_hei_30">
        <shiro:hasPermission name='101'>
            <button name="" id="userAdd" class="btn btn-primary radius"
                    type="button">
                <i class="Hui-iconfont">&#xe600;</i>&nbsp;添加用户
            </button>
        </shiro:hasPermission>
        <shiro:hasPermission name='106'>
            <button name="" id="userDustbin" class="btn btn-success"
                    type="button">
                <i class="Hui-iconfont">&#xe600;</i>恢复删除用户
            </button>
        </shiro:hasPermission>
        <span class="f-r"> <input type="text" name="searchUserName"
                                  id="searchUserName" placeholder="用户姓名"
                                  class="input-text cus_wid_250 radius">
				<button name="" id="userSearch" class="btn btn-success radius"
                        type="button">
					<i class="Hui-iconfont">&#xe665;</i>&nbsp;查询
				</button>
			</span>
    </div>
    <div class="mt-20">
        <table id="tableList"
               class="table table-striped table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="30">序号</th>
                <th width="80">id</th>
                <th width="40">账号</th>
                <th width="40">姓名</th>
                <th width="40">地市名称</th>
                <th width="40">角色名称</th>
                <th width="50">用户状态</th>
                <th width="40">添加人</th>
                <th width="40">添加时间</th>
                <th width="120">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/user/user.js"></script>
<script type="text/javascript">
    function initUserTable() {
        function handleAjaxError() {
            console.info("123")
        }

        userTable = $('#tableList')
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
                        "url": ctx + "/user/getUserByConditon",
                        "data": {
                            deleteFlag: 2
                        }
                    },
                    "columns": [{
                        "data": null,
                        "targets": 0
                    }, {
                        "data": "id",
                        "visible": false
                    }, {
                        "data": "loginId"
                    }, {
                        "data": "name"
                    }, {
                        "data": "mechanismsName"
                    }, {
                        "data": "userRoleName"
                    }, {
                        "data": "activeDisplay"
                    }, {
                        "data": "addUser"
                    }, {
                        "data": "addTime"
                    },],
                    "createdRow": function (row, data, index) {
                        if (data.activeDisplay == "启用") {
                            $('td', row).eq(5).html(
                                '<span class=\"label label-primary radius\">'
                                + data.activeDisplay
                                + '</span>');
                        } else {
                            $('td', row).eq(5).html(
                                '<span class=\"label label-danger radius\">'
                                + data.activeDisplay
                                + '</span>');
                        }

                    },
                    "columnDefs": [{
                        "targets": [9],
                        "data": "id",
                        "render": function (data, type, full) {
                            var toShowUpdateUser = "";
                            var deleteUser = "";
                            var updateState = "";
                            var resetLoginPassword = "";
                            toShowUpdateUser = "<shiro:hasPermission name='102'><a href='javascript:void(0)' onClick=toShowUpdateUser('"
                                + data
                                + "')>修改</a>&nbsp;&nbsp;</shiro:hasPermission>";
                            deleteUser = "<shiro:hasPermission name='103'><a href='javascript:void(0)'onClick=deleteUser('"
                                + data
                                + "')>删除</a>&nbsp;&nbsp;</shiro:hasPermission>";
                            if ("启用" == full.activeDisplay) {
                                updateState = "<shiro:hasPermission name='104'><a href='javascript:void(0)' onClick=updateState('"
                                    + data
                                    + "','1')>禁用</a>&nbsp;&nbsp;</shiro:hasPermission>";
                            } else {
                                updateState = "<shiro:hasPermission name='104'><a href='javascript:void(0)' onClick=updateState('"
                                    + data
                                    + "','2')>启用</a>&nbsp;&nbsp;</shiro:hasPermission>";
                            }
                            resetLoginPassword = "<shiro:hasPermission name='105'><a href='javascript:void(0)' onClick=resetLoginPassword('"
                                + data
                                + "')>重置密码</a></shiro:hasPermission>";

                            return toShowUpdateUser + deleteUser
                                + updateState
                                + resetLoginPassword;
                        }
                    }],
                    "fnDrawCallback": function () {
                        var api = this.api();
                        var startIndex = api.context[0]._iDisplayStart;// 获取到本页开始的条数
                        api.column(0).nodes().each(
                            function (cell, i) {
                                cell.innerHTML = startIndex + i + 1;
                            });
                    }
                });
    }
</script>
</body>
</html>