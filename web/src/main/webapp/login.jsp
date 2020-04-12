<%--
  Created by IntelliJ IDEA.
  it_academy.control_project.data.User: dell
  Date: 26.03.2020
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Страница для абитуриентов</title>
</head>
<body>
<center>
<h2 style="color: green">Введите Ваш Login</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="login"></label>
    <input id="login" type="text" name="login" placeholder="Login"> <br/>

    <label for="password"></label>
    <input id="password" type="text" name="password" placeholder="Password"><br/>

    <input type="submit">
</form>
<p style="color: red">${error}</p>
</center>
</body>
</html>

