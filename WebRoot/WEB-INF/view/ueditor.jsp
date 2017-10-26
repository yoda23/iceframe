<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript"
	src="${ctx}/ui/lib/ueditor/1.4.3/ueditor.config.js"></script>
<meta http-equiv="Cache-Control" content="no-cache" />
<script type="text/javascript"
	src="${ctx}/ui/lib/ueditor/1.4.3/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/js/ue/ue.js"></script>
<title>ueditor演示</title>
</head>
<body>
	<!-- 加载编辑器的容器 -->
	<script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    </script>
</body>
</html>