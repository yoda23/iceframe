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
<meta http-equiv="Cache-Control" content="no-cache" />

<title>登陆超时</title>
</head>
<body>
	<section class="container-fluid page-404 minWP text-c">
		<p class="error-title">
			<i class="Hui-iconfont va-m" style="font-size:80px">&#xe688;</i><span
				class="va-m">TIME OUT</span>
		</p>
		<p class="error-description">登陆超时，重新登录</p>
	</section>
	<script type="text/javascript">
		$(function() {
			layer.alert('用户登录超时，请重新登录', {
				icon : 0,
				skin : 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
			}, function() {
				top.location.href = '${ctx}/login';
			});
		});
	</script>
</body>
</html>