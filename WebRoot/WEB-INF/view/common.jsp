<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="pageLength" value="20"/>

<!-- 公用的css -->
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/static/h-ui/css/H-ui.min.css"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/static/h-ui.admin/css/H-ui.admin.css"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/static/h-ui.admin/css/H-ui.login.css"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/lib/Hui-iconfont/1.0.7/iconfont.css"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/lib/icheck/icheck.css"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/plugin/admin/css/css.css"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/static/h-ui.admin/skin/default/skin.css" id="default"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/static/h-ui.admin/css/style-1.1.0.css"/>
<link rel="stylesheet" type="text/css"
      href="${ctx}/ui/lib/ztree/3.5.29/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet"
      href="${ctx}/ui/lib/bootstrap-select/1.7.2/css/bootstrap-select.css">
<!-- 公用的js -->
<script type="text/javascript"
        src="${ctx}/ui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/ui/lib/layer/3.0.3/layer.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/jquery.validation/1.15.0/additional-methods.min.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/jquery.validation/1.15.0/messages_zh.min.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/ui/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/ztree/3.5.29/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="${ctx}/ui/lib/sockjs/sockjs.min.js"></script>
<script type="text/javascript"
        src="${ctx}/ui/lib/bootstrap-select/1.7.2/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
