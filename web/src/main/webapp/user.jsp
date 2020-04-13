<%--
  Created by IntelliJ IDEA.
  it_academy.control_project.data.User: dell
  Date: 26.03.2020
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Ввод данных пользователя</title>
</head>

<body>
<a href="${pageContext.request.contextPath}/logout"></a>
<center>
    <h2 style="color: green">Введите свои данные</h2>
    <form action="${pageContext.request.contextPath}/user" method="post">

        <label for="name"></label>
        <input id="name" type="text" name="name" placeholder="Name"><br/>

        <label for="surname"></label>
        <input id="surname" type="text" name="surname" placeholder="Surname"><br/>

        <label for="phone"></label>
        <input id="phone" type="text" name="phone" placeholder="Phone"><br/>

        <label for="email"></label>
        <input id="email" type="text" name="email" placeholder="Email"><br/>

        <input type="submit">
    </form>
    <p style="color: red">${error}</p>
</center>
</body>
</html>
