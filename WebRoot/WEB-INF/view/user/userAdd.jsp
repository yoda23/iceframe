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
    <title></title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="userAddForm">
        <%-- <input type="text" name="token" value="${TOKEN}" /> --%>
        <div class="col-sm-4 style_tree_scroll">
            <ul id="userTree" class="ztree"></ul>
            <input id="menuAll" class="btn btn-primary cus_wid_70 radius"
                           type="button" value="全&nbsp;选">
            <input id="menuCancelAll" class="btn btn-primary cus_wid_70 radius"
                           type="button" value="取&nbsp;消">
        </div>
        <input type="hidden" id="mechanismsIdCheck" name="mechanismsIdCheck" value="">
        <div class="col-sm-8">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>登录账号：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" value=""
                           placeholder="登陆账号" id="userAddLoginId" name="loginId">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>初始密码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="password" class="input-text radius"
                           autocomplete="off" value="" placeholder="密码" id="userAddPassword"
                           name="loginPassword">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>确认密码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="password" class="input-text radius"
                           autocomplete="off" placeholder="确认新密码" id="userAddPassword2"
                           name="userAddPassword2">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>姓名：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" value=""
                           placeholder="姓名" id="userAddName" name="name">
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
                        class="c-red">*</span>帐号权限：</label>
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
                        class="c-red">*</span>所属地市：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="style_input-disabled radius" value=""
                           placeholder="所属地市" id="parentName" name="parentName"
                           readonly="readonly"> <input type="hidden"
                                                       class="style_input-disabled radius" value="" placeholder="所属地市"
                                                       id="mechanismsId" name="mechanismsId">
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input id="saveUserAdd" class="btn btn-primary cus_wid_70 radius"
                           type="submit" value="提&nbsp;交">
                </div>
            </div>
        </div>
    </form>
</article>
<script type="text/javascript" src="${ctx}/js/user/user.add.js"></script>
</body>
</html>
