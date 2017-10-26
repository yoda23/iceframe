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
        <div class="col-sm-4 row cl style_tree_scroll">
            <ul id="mechanismsTree" class="ztree"></ul>
        </div>
        <div class="col-sm-8">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span
                        class="c-red">*</span>所属地市：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="hidden" class="input-text" value="${mechanisms.id}"
                           placeholder="所属地市" id="id" name="id">
                    <input type="text" class="style_input-disabled radius" value="${mechanisms.parentName}"
                           placeholder="所属地市" id="parentName" name="parentName" readonly="readonly">
                    <input type="hidden" class="input-text" value="${mechanisms.parentId}" placeholder="所属地市"
                           id="parentId" name="parentId">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span
                        class="c-red">*</span>地市名称：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" value="${mechanisms.name}" placeholder="地市名称" id="name"
                           name="name">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>地市编码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" value="${mechanisms.code}" placeholder="地市编码" id="code"
                           name="code">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3">地市简称：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius"
                           value="${mechanisms.simpleName}" placeholder="地市简称" id="simpleName" name="simpleName">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3">负责人：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius"
                           value="${mechanisms.principal}" placeholder="地市负责人" id=principal name="principal">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3">电话号码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius" value="${mechanisms.phone}" placeholder="电话号码"
                           id="phone" name="phone">
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input id="update" class="btn btn-primary radius" type="submit" value="提&nbsp;交">
                </div>
            </div>
        </div>
    </form>
</article>
<script type="text/javascript" src="${ctx}/js/mechanisms/mechanisms.update.js"></script>
</body>
</html>