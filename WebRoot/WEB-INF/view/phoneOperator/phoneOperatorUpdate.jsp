<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link rel="stylesheet" href="${ctx}/ui/lib/bootstrap/3.3.4/css/bootstrap.css">
<title></title>
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="updateForm">
		<input type="hidden" name="id" value="${phoneOperator.id}">
			<div class="col-sm-8">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
						class="c-red">*</span>号码前缀：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="prefix" class="input-text radius" value="${phoneOperator.prefix}" placeholder="手机号码前缀" name="prefix">
					</div>
				</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3 cus_font-normal"><span
						class="c-red">*</span>运营商名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<select class="selectpicker cus_wid_100b" data-live-search="false" name="operator" size="1">
							<option value="1" <c:if test="${phoneOperator.operator=='1'}">selected</c:if>>中国联通</option>
							<option value="2" <c:if test="${phoneOperator.operator=='2'}">selected</c:if>>中国移动</option>
							<option value="3" <c:if test="${phoneOperator.operator=='3'}">selected</c:if>>中国电信</option>
						</select>
					</div>
				</div>

				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input class="btn btn-primary cus_wid_70 radius"
							type="submit" value="提&nbsp;交">
					</div>
				</div>
			</div>
		</form>
	</article>
	<script type="text/javascript" src="${ctx}/js/phoneOperator/phoneOperator.update.js"></script>

</body>
</html>