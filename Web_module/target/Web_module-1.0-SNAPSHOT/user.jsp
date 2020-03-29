<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 26.03.2020
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="${pageContext.request.contextPath}/logout"></a>
    <h2 style="color: green">Введите свои данные</h2>
    <form action="${pageContext.request.contextPath}/user" method="post">
        <label for="name">имя</label>
        <input id="name" type="text" name="name"><br/>

        <label for="surname">фамилия</label>
        <input id="surname" type="text" name="surname"><br/>

        <label for="phone">phone</label>
        <input id="phone" type="text" name="phone"><br/>

        <label for="email">email</label>
        <input id="email" type="text" name="email"><br/>

        <input type="submit">
    </form>

<%= request.getAttribute("users")%>