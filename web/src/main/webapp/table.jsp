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
<body>
<center>
    <h3 style="color: darkslateblue">Список сдачи тестирования</h3>
    <c:if test="${applicants != null}">
        <c:forEach items="${applicants}" var="applicant">
            <table>
                <tr>
                    <th>ID абитуриента: ${applicant.id}</th>
                    <th>Оцека: ${applicant.mark}</th>
                </tr>
            </table>
        </c:forEach>
    </c:if>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться на начальную страницу</a>
</center>
</body>
</html>