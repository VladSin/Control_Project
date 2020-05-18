<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 18.05.2020
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Таблица студентов</title>
    <link rel="stylesheet" type="text/css" href="resources/css/backgroundStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/tableStyle.css">
</head>
<body>
<center>
    <h1 style="color: darkslateblue">Список Студентов</h1>
    <table>
        <tr><th>ID</th><th>Имя</th><th>Фамилия</th><th>Телефон</th><th>Email</th></tr>
        <c:if test="${students != null}">
            <c:forEach items="${students}" var="student">
                <c:forEach items="${users}" var="user">
                    <c:if test="${student.userId == user.id}">
                        <tr>
                            <td style="color: darkslateblue">${student.id}</td>
                            <td style="color: darkslateblue">${user.name}</td>
                            <td style="color: darkslateblue">${user.surname}</td>
                            <td style="color: darkslateblue">${user.phone}</td>
                            <td style="color: darkslateblue">${user.email}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </c:if>
    </table>

    <a href="${pageContext.request.contextPath}/index.jsp">Applicants</a>
    <a href="${pageContext.request.contextPath}/index.jsp">Return</a>
</center>
</body>
</html>