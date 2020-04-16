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
</head>
<body style="color:burlywood; background-color:#FFFFFF">
<center>
    <h3 style="color: darkslateblue">Список сдачи тестирования</h3>
    <c:if test="${applicants != null}">
        <c:forEach items="${applicants}" var="applicant">
            <c:forEach items="${users}" var="user">

                <table>
                    <tr>
                        <c:if test="${applicant.userId == user.id}">
                            <c:if test="${applicant.mark >= 8}">
                                <th style="color: green">Абитуриента: ${user.name}</th>
                                <th style="color: green">${user.surname}</th>
                                <th style="color: green">Оцека: ${applicant.mark}</th>
                            </c:if>
                            <c:if test="${applicant.mark < 8}">
                                <th style="color: red">Абитуриента: ${user.name}</th>
                                <th style="color: red">${user.surname}</th>
                                <th style="color: red">Оцека: ${applicant.mark}</th>
                            </c:if>
                        </c:if>
                    </tr>
                </table>

            </c:forEach>
        </c:forEach>
    </c:if>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться на начальную страницу</a>
</center>
</body>
</html>