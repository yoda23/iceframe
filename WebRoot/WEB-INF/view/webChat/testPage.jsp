<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/31
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>

    <title>投票</title>
</head>
<html>
<body>
    <div>
        <c:forEach items="${tpList}" var="tp" >
            <input type="radio" name="test" value="${tp.id}" > ${tp.description} 票数 ：${tp.count}<br>
        </c:forEach>
        <input type="button" value="投票" id="toupiao">
    </div>
<script>
    $("#toupiao").click(function () {
        $.ajax({
            type : "POST",
            url : ctx + '/tp/incCount',
            data : {
                id:$("input[name='test']:checked").val()
            },
            cache : false,
            async : false,
            success : function(data) {
                result = eval("(" + data + ")");
                if (result.success) {
                    window.location.reload();
                } else {
                    alert(result.message);
                }
            }
        });


    });
</script>
</body>
</html>
