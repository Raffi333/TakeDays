<%--
  Created by IntelliJ IDEA.
  User: raffi
  Date: 11.01.22
  Time: 11:36
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>confirm your account</title>
</head>

<body>
<h1>Welcome ${name}</h1>

<h3>please confirm your account</h3>

<h3>${confirm}</h3>

<g:link url="http://localhost:8080${createLink(controller: 'authorization', action: 'confirm', params: [code: confirm])}">
    Click ME
</g:link>

</body>
</html>