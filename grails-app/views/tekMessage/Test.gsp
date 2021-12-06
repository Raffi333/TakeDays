<%--
  Created by IntelliJ IDEA.
  User: raffi
  Date: 02.12.21
  Time: 14:54
--%>

<%@ page import="java.util.stream.Collectors; tekdays.TekMessage" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
   <meta name="layout" content="main">
</head>

<body>

<div id="messageList">
    <g:messageThread messages="${tekMessageInstanceList}" />
</div>

<div id="a"></div>
</body>
</html>