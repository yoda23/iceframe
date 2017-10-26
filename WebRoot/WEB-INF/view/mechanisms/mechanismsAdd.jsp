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
    <title></title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="mechanismsAddForm">
        <div id="treeScroll" class="col-sm-4 style_tree_scroll">
            <ul id="mechanismsTree" class="ztree"></ul>
        </div>
        <div class="col-sm-8">
            <div class="row cl">
                <label class="form-label col-sm-3"><span class="c-red">*</span>所属地市：</label>
                <div class="formControls col-sm-9">
                    <input type="text" class="style_input-disabled  radius" value="" placeholder="所属地市" id="parentName"
                           name="parentName" readonly="readonly"/>
                    <input type="hidden" class="" value="" placeholder="所属地市" id="parentId" name="parentId"/>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-sm-3"><span class="c-red">*</span>地市名称：</label>
                <div class="formControls col-sm-9">
                    <input type="text" class="input-text  radius" value=""
                           placeholder="地市名称" id="name" name="name">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label  col-sm-3"><span class="c-red">*</span>地市编码：</label>
                <div class="formControls  col-sm-9">
                    <input type="text" class="input-text  radius" value=""
                           placeholder="地市编码" id="code" name="code">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label  col-sm-3">地市简称：</label>
                <div class="formControls  col-sm-9">
                    <input type="text" class="input-text  radius" value=""
                           placeholder="地市简称" id="simpleName" name="simpleName">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label  col-sm-3">负责人：</label>
                <div class="formControls  col-sm-9">
                    <input type="text" class="input-text  radius" value=""
                           placeholder="地市负责人" id=principal name="principal">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label  col-sm-3">电话号码：</label>
                <div class="formControls col-sm-9">
                    <input type="text" class="input-text  radius" value=""
                           placeholder="电话号码" id="phone" name="phone">
                </div>
            </div>
            <div class="row cl">
                <div class=" col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input id="saveRoleAdd" class="btn btn-primary cus_wid_70 radius"
                           type="submit" value="提&nbsp;交">
                </div>
            </div>
        </div>
    </form>
</article>
<script type="text/javascript" src="${ctx}/js/mechanisms/mechanisms.add.js"></script>
</body>
</html>