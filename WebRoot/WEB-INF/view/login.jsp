<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>

    <title>黑龙江一点通-IceFrame基础框架</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value=""/>
<div class="slideshow">
    <div class="slideshow-image"
         style="background-image: url('${ctx}/ui/plugin/admin/img/1.jpg')"></div>
    <%--<div class="slideshow-image"
         style="background-image: url('${ctx}/ui/plugin/admin/img/2.jpg')"></div>
    <div class="slideshow-image"
         style="background-image: url('${ctx}/ui/plugin/admin/img/3.jpg')"></div>--%>
</div>
<div class="loginBox admin_css_bg">
    <div class="admin_css_divlogo"></div>
    <form id="loginForm" class="form form-horizontal" autocomplete="off"
          action="" method="post">
        <div class="row cl">
            <label class="form-label col-xs-2"></label>
            <div class="formControls col-xs-9">
                <input id="loginId" name="loginId" type="text" autocomplete="off"
                       placeholder="账号" class="admin_css_input1">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2"></label>
            <div class="formControls col-xs-9">
                <input id="loginPassword" name="loginPassword" type="password"
                       autocomplete="off" placeholder="密码" class="admin_css_input2">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2"></label>
            <div class="formControls col-xs-9" style="position:relative">
                <input id="validateCode" name="validateCode"
                       class="admin_css_input3" autocomplete="off" type="text"
                       placeholder="验证码" onclick="" value="">
                <div style="position:absolute; top:5px; width:120px; right:100px;">
                    <img id="validateCodeImg" class="cus_f-left"
                         src="${ctx }/getValidateCode"> <a
                        class="Hui-iconfont admin_css_anone" id="changeValidateCode"
                        href="javascript:void(0);">&#xe68f;</a>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2"></label>
            <div class="formControls col-xs-9 ">
                <input id="loginSubmit" name="loginSubmit" type="submit"
                       class="btn btn-success radius size-L admin_css_button"
                       value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
            </div>
        </div>
    </form>
</div>
<div class="footer cus_bor0 cus_size_14"
     style="font-family:微软雅黑;background-color:rgba(0,0,0,0.5); ">Copyright
    黑龙江一点通-IceFrame基础框架 by v4.2
</div>
<script type="text/javascript"
        src="${ctx }/ui/plugin/admin/js/login.js"></script>
<script>
    $(function () {
        $("#loginForm").validate({
            rules: {
                loginId: "required"
            },
            messages: {
                loginId: "用户名不能为空"
            },
            submitHandler: function (form) {
                login();
            },
            invalidHandler: function (form, validator) {

            },

        });
        $("#changeValidateCode").click(
            function () {
                $("#validateCodeImg").attr(
                    "src",
                    "${ctx }/getValidateCode?timestamp="
                    + new Date().getTime());
            });
    });
    function login() {
        $.ajax({
            type: "POST",
            url: '${ctx }/user/login',
            data: $("#loginForm").serialize(),
            cache: false,
            async: false,
            success: function (data) {
                var result = $.parseJSON(data);
                if (result.success) {
                    var loginButton = document
                        .querySelectorAll(".admin_css_button");
                    loginButton[0].value = "登录中...";
                    loginButton[0].disabled = "disabled";
                    loginButton[0].style.backgroundColor = "#999";
                    loginButton[0].style.border = "0";
                    window.location.href = "${ctx}/index";
                } else {
                    layer.alert(result.message);
                }
            }
        });
    }
</script>

</body>
</html>
