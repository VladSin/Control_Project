<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 13.04.2020
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Результаты теста</title>
    <link rel="stylesheet" type="text/css" href="resources/css/backgroundStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/tableStyle.css">
</head>
<body>
<br>
<center>
    <c:if test="${applicant.mark ge faculty.mark}">
        <h1 style="color: green">Поздравляем!!!</h1>
    </c:if>

    <c:if test="${applicant.mark lt faculty.mark}">
        <h1 style="color: red">Не расстраивайтесь!!!</h1>
    </c:if>

    <table>
        <tr><th>Ваш результат</th><th>Требования факультета</th></tr>
        <tr><td><c:out value="${applicant.mark}"/></td><td><c:out value="${faculty.mark}"/></tr>
    </table>
    <h2 style="color: blue">Ответы на Ваш экзамен</h2>
    <table>
        <tr><th>Вопросы</th><th>Ответы</th></tr>
        <c:if test="${exams != null}">
            <c:forEach items="${exams}" var="exam">
                <c:if test="${exam.facultyId == applicant.facultyId}">
                    <tr>
                        <td>${exam.question}</td>
                        <td>${exam.answer}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:if>

    </table>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <a href="${pageContext.request.contextPath}/index.jsp">Return</a>
</center>
</body>
</html>
