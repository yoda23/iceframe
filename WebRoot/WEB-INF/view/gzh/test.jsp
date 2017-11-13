﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web Socket JavaScript Echo Client</title>
    <script src="${ctx}/ui/lib/sockjs/sockjs.min.js"></script>
    <script language="javascript" type="text/javascript">
        var echo_websocket;
        function init() {
            output = document.getElementById("output");
            echo_websocket = new SockJS("http://localhost:8080/iceframe/springws/websocket") ;   //初始化 websocket
            echo_websocket.onopen = function () {
                console.log('Info: connection opened.');
            };

            echo_websocket.onmessage = function (event) {
                console.log('Received: ' + event.data); //处理服务端返回消息
            };

            echo_websocket.onclose = function (event) {
                console.log('Info: connection closed.');
                console.log(event);
            };


        }


        function doSend(message) {
            echo_websocket.send(message);
            writeToScreen("Sent message: " + message);
        }
        function writeToScreen(message) {
            var pre = document.createElement("p");
            pre.style.wordWrap = "break-word";
            pre.innerHTML = message;
            output.appendChild(pre);
        }
        window.addEventListener("load", init, false);
    </script>
</head>
<body>
<h1>Echo Server</h1>
<div style="text-align: left;">
    <form action="">
        <input onclick="doSend('adsfsdfsdf')" value="send websocket request" type="button">
        <input id="textID" name="message" value="Hello world, Web Sockets" type="text">
        <br>
    </form>
</div>
<div id="output"></div>
</body>
</html>