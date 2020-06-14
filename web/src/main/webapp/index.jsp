<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 13.04.2020
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Начальная страница</title>
    <link rel="stylesheet" type="text/css" href="resources/css/backgroundStyle.css">
<%--    <link rel="stylesheet" type="text/css" href="resources/css/topTextStyle.css">--%>
</head>
<body>
<center>
    <h1 style="color: green">Мы рады приветствовать Вас на нашем сайте!!!</h1>

    <a href="${pageContext.request.contextPath}/login">Войти на сайт</a>
</center>
</body>
</html>
