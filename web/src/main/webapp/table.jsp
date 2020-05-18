<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 13.04.2020
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Таблица абитуриентов</title>
    <link rel="stylesheet" type="text/css" href="resources/css/backgroundStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/tableStyle.css">
</head>
<body>
<center>
    <h1 style="color: darkslateblue">Список сдачи тестирования</h1>
    <table>
        <tr><th>Имя</th><th>Фамилия</th><th>Балл за тест</th></tr>
        <c:if test="${applicants != null}">
            <c:forEach items="${applicants}" var="applicant">
                <c:forEach items="${users}" var="user">
                    <c:if test="${applicant.userId == user.id}">
                        <c:if test="${applicant.mark >= 8}">
                            <tr>
                                <td style="color: green">${user.name}</td>
                                <td style="color: green">${user.surname}</td>
                                <td style="color: green">${applicant.mark}</td>
                            </tr>
                        </c:if>
                        <c:if test="${applicant.mark < 8}">
                            <tr>
                                <td style="color: red">${user.name}</td>
                                <td style="color: red">${user.surname}</td>
                                <td style="color: red">${applicant.mark}</td>
                            </tr>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </c:if>
    </table>

    <a href="${pageContext.request.contextPath}/student">Students</a>
    <a href="${pageContext.request.contextPath}/index.jsp">Return</a>
</center>
</body>
</html>