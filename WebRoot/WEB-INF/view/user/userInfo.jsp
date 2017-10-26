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

<title>基本设置</title>
</head>
<body>
	<div class="page-container">
		<div id="tab-system"
			class="HuiTab cus_wid_90b cus_mar-left_35 cus_mar-top_15">
			<div class="tabBar cl">
				<span>基本信息</span><span>修改密码</span>
			</div>
			<form class="form form-horizontal" id="userInFoForm">
			<input type="hidden" name="id" value="${user.id}">
				<div class="tabCon">
					<div class="row cl col-xs-10">
						<label class="form-label col-xs-3 col-sm-3 cus_text-align_r">账号：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="text" disabled="disabled" id="loginId"
								value="${user.loginId}" class="input-text">
						</div>
					</div>
					<div class="row cl col-xs-10">
						<label class="form-label col-xs-3 col-sm-3 cus_text-align_r"><span
							class="c-red">*</span>姓名：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="text" id="name" name="name" value="${user.name}"
								placeholder="姓名" class="input-text">
						</div>
					</div>
					<div class="row cl col-xs-10">
						<div class="col-xs-8 col-sm-12 col-xs-offset-3 col-sm-offset-2">
							<input class="btn btn-primary radius"
								type="submit" value="保存" /> <input id="userInFoClose"
								class="btn btn-default radius" type="button" value="关闭">
						</div>
					</div>
				</div>
			</form>
			<form class="form form-horizontal" id="updatePasswordForm">
			<input type="hidden" name="id" value="${user.id}">
				<div class="tabCon">
					<div class="row cl col-xs-10">
						<label class="form-label col-xs-3 col-sm-2 cus_text-align_r">账号：</label>
						<div class="formControls col-xs-8 col-sm-7">
							<input type="text" disabled="disabled" id="loginId"
								value="${user.loginId}" class="input-text">
						</div>
					</div>
					<div class="row cl col-xs-10">
						<label class="form-label col-xs-3 col-sm-2 cus_text-align_r"><span
							class="c-red">*</span>旧密码：</label>
						<div class="formControls col-xs-8 col-sm-7">
							<input type="password" class="input-text" id="oldPassword"
								name="oldPassword" value="">
						</div>
					</div>
					<div class="row cl col-xs-10">
						<label class="form-label col-xs-3 col-sm-2 cus_text-align_r"><span
							class="c-red">*</span>新密码：</label>
						<div class="formControls col-xs-8 col-sm-7">
							<input type="password" class="input-text" id="loginPassword"
								name="loginPassword" autocomplete="off" value="">
						</div>
					</div>
					<div class="row cl col-xs-10">
						<label class="form-label col-xs-3 col-sm-2 cus_text-align_r"><span
							class="c-red">*</span>确认密码：</label>
						<div class="formControls col-xs-8 col-sm-7">
							<input type="password" id="confirmPassword"
								name="confirmPassword" autocomplete="off" value=""
								class="input-text">
						</div>
					</div>
					<div class="row cl col-xs-10">
						<div class="col-xs-6 col-sm-9 col-xs-offset-3 col-sm-offset-2">
							<input class="btn btn-primary radius" type="submit" value="保存" />
							<input id="updatePasswordClose" class="btn btn-default radius"
								type="button" value="关闭" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript">
$(function() {
	$("#userInFoForm").validate({
		rules : {
			name : {
				required : true,
			},
		},
		messages:{
			name:"用户名不能为空",
		},
		submitHandler : function(form) {
			updateUserInfo();
		}

	});
	$("#updatePasswordForm").validate({
		rules : {
			oldPassword : {
				required : true,
			},
			loginPassword : {
				required : true,
			},
			confirmPassword : {
				required : true,
				equalTo : "#loginPassword"
			},
		},
		submitHandler : function(form) {
			updatePassword();
		}

	});
	$("#userInFoClose").click(function() {
		layer_close();
	});
	$("#updatePasswordClose").click(function() {
		layer_close();
	});
	$('.skin-minimal input').iCheck({
		checkboxClass : 'icheckbox-blue',
		radioClass : 'iradio-blue',
		increaseArea : '20%'
	});
	$.Huitab("#tab-system .tabBar span", "#tab-system .tabCon",
			"current", "click", "0");
});
//修改用户名称
function updateUserInfo() {
	$.ajax({
		type : "POST",
		url : '${ctx }/user/updateUserInfo',
		data : $("#userInFoForm").serialize(),
		cache : false,
		async : false,
		success : function(data) {
			result = $.parseJSON(data);
			console.info(result);
			if (result.success) {
				layer_close();
			} else {
				layer.alert(result.message);
				return;
			}
		}
	});
}
//修改用户密码
function updatePassword() {
	$.ajax({
		type : "POST",
		url : '${ctx }/user/updatePassword',
		data :$("#updatePasswordForm").serialize(),
		cache : false,
		async : false,
		success : function(data) {
			result = $.parseJSON(data);
			console.info(result);
			if (result.success) {
				//window.location.href = "${ctx}/index";
				layer_close();
			} else {
				layer.alert(result.message);
				return;
			}
		}
	});
}
</script>
</body>
</html>