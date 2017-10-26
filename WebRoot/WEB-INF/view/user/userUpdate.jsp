<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../common.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <link rel="stylesheet"
          href="${ctx}/ui/lib/bootstrap/3.3.4/css/bootstrap.css">
    <title>修改用户 -用户管理管理</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="userUpdateForm">
        <div class="col-sm-4 style_tree_scroll">
            <ul id="userTree" class="ztree"></ul>
            <input id="menuAll" class="btn btn-primary cus_wid_70 radius"
                   type="button" value="全&nbsp;选">
            <input id="menuCancelAll" class="btn btn-primary cus_wid_70 radius"
                   type="button" value="取&nbsp;消">
        </div>
        <div class="col-sm-8">
            <div class="row cl">
                <input type="hidden" id="id" name="id" value="${user.id }">
                <input type="hidden" id="mechanismsIdCheck" name="mechanismsIdCheck" value="${mechanismsIdCheck}">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>登录账号：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="style_input-disabled radius"
                           value="${user.loginId }" placeholder="" readonly="readonly"
                           id="userUpdateLoginId" name="loginId">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>姓名：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" placeholder=""
                           id="userUpdateName" name="name" value="${user.name }">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>账号权限：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select id="find" class="selectpicker cus_wid_100b"
                            data-live-search="false" name="find" size="1">
                        <option value="1">管理员</option>
                        <option value="2">普通员工</option>
                    </select>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>角色：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select id="roleSelect" class="selectpicker cus_wid_100b"
                            data-live-search="true" name="adminRole" size="1"></select>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>所属地市：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="style_input-disabled radius"
                           value="${user.mechanisms.name }" placeholder="所属地市"
                           id="parentName" name="parentName" readonly="readonly"> <input type="hidden"
                                                                                         class="style_input-disabled radius"
                                                                                         value="${user.mechanisms.id}"
                                                                                         placeholder="所属地市"
                                                                                         id="mechanismsId"
                                                                                         name="mechanismsId">
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input id="saveUserUpdate"
                           class="btn btn-primary cus_wid_70 radius" type="submit"
                           value="提&nbsp;交">
                </div>
            </div>
        </div>
    </form>
</article>

<script type="text/javascript" src="${ctx}/js/user/user.update.js"></script>
<script type="text/javascript">
    $(function () {
        $("#roleSelect option[value='${user.roleId}']").attr("selected",
            true);
        $("#find option[value='${user.find}']").attr("selected", true);
        var mechanismsIdCheck = "${mechanismsIdCheck}";
    })
    function createTree() {
        $.ajax({
            url: ctx + '/mechanisms/getMechanismsForUserMenuTree',
            type: 'POST',
            success: function (data) {
                jQuery.fn.zTree.init(jQuery("#userTree"), setting, eval('('
                    + data + ')'));
                var treeObj = jQuery.fn.zTree.getZTreeObj("userTree");
                treeObj.expandAll(true);
                var mechanismsIdCheck = "${mechanismsIdCheck}";
                var arrys = mechanismsIdCheck.split(",");
                if (mechanismsIdCheck !== "") {
                    for (var i = 0; i < arrys.length; i++) {
                        var node = treeObj.getNodeByParam("id", arrys[i],
                            null);
                        treeObj.checkNode(node, true, true);
                    }
                }
            },
            error: function (msg) {
                layer.alert(msg);
            }
        });
    }
</script>
</body>
</html>
