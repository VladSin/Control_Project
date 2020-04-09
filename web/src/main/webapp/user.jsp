<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 26.03.2020
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Ввод данных пользователя</title>
</head>

<body>
<a href="${pageContext.request.contextPath}/logout"></a>

    <h2 style="color: green">Введите свои данные</h2>
    <form action="${pageContext.request.contextPath}/user" method="post">
        <c:set var="progress" value="${0}"/>

        <label for="name"></label>
        <input id="name" type="text" name="name" placeholder="Name"><br/>
        <c:if test="${1 != null}">
            <c:set var="progress" value="${100}"/>
        </c:if>

        <label for="surname"></label>
        <input id="surname" type="text" name="surname" placeholder="Surname"><br/>
        <c:if test="${1 != null}">
            <c:set var="progress" value="${100}"/>
        </c:if>

        <label for="phone"></label>
        <input id="phone" type="text" name="phone" placeholder="Phone"><br/>
        <c:if test="${1 != null}">
            <c:set var="progress" value="${100}"/>
        </c:if>

        <label for="email"></label>
        <input id="email" type="text" name="email" placeholder="Email"><br/>
        <c:if test="${1 != null}">
            <c:set var="progress" value="${100}"/>
        </c:if>


        <progress max="100" value="${progress}"></progress><br/>
        <input type="submit">
    </form>
</body>
</html>
