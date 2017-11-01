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
    <form class="form form-horizontal" id="tpForm">

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
                        class="c-red">*</span>投票项：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text radius"
                           autocomplete="off" placeholder="填写投票项" id="description"
                           name="description">
                </div>
             </div>
             <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input id="saveTpAdd" class="btn btn-primary cus_wid_70 radius"
                       type="submit" value="提&nbsp;交">
            </div>
        </div>
    </form>
</article>
<script type="text/javascript" src="${ctx}/js/tp/tp.add.js"></script>
</body>
</html>
