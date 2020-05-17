<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 26.03.2020
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Страница для абитуриентов</title>
    <link rel="stylesheet" type="text/css" href="resources/css/backgroundStyle.css">
</head>
<body>
<center>
<h2 style="color: green">Введите Ваш Login</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="login"></label>
    <input id="login" type="text" name="login" placeholder="Login"> <br/>

    <label for="password"></label>
    <input id="password" type="password" name="password" placeholder="Password"><br/>

    <input type="submit" value="Submit">
</form>
<p style="color: red">${error}</p>
</center>
</body>
</html>

