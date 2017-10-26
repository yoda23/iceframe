<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>初始化</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

</head>

<body>
<!-- <div id="loginform" class="loginBox admin_css_bg">
<div class="admin_css_divlogo"></div> -->
<form id="confirmForm" class="form form-horizontal"
      action="" method="post">
    <div class="row cl">
        <label class="form-label col-xs-2"></label>
        <div class="formControls col-xs-9">
            <input id="initPassword" name="initPassword" type="password" placeholder="密码">
        </div>
    </div>

    <div class="row cl">
        <label class="form-label col-xs-2"></label>
        <div class="formControls col-xs-9 ">
            <input type="submit" value="&nbsp;确&nbsp;&nbsp;&nbsp;&nbsp;定&nbsp;">
        </div>
    </div>
</form>
<script type="text/javascript">
    $(function () {
        $("#confirmForm").validate({
            rules: {
                initPassword: "required"
            },
            messages: {
                initPassword: "密码不能为空"
            },
            submitHandler: function (form) {
                confirm();
            }


        });
    });
    function confirm() {
        layer.confirm('确定要初始化数据么？', {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            $.ajax({
                type: "POST",
                url: '${ctx }/init',
                data: $("#confirmForm").serialize(),
                cache: false,
                async: true,
                beforeSend: function (XHR) {
                    layer.msg('正在初始化数据，请稍后', {
                        icon: 16,
                        shade: 0.01,
                        time: 0,
                        offset: '200px'
                    });
                },
                success: function (data) {
                    var result = $.parseJSON(data);
                    if (result.success) {
                        layer.alert(result.message, function () {
                            window.location.href = "${ctx}/login";
                        });
                    } else {
                        layer.alert(result.message);
                    }
                }
            });
        });
    }
</script>
</body>
</html>
