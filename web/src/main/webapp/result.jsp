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
        <tr>
        <br>
            <th>Ваш результат: </th>
            <th><c:out value="${applicant.mark}"/></th>
        </tr>

        <tr>
            <th>Требования факультета: </th>
            <th><c:out value="${faculty.mark}"/></th>
        </tr>
    </table>
    <h3 style="color: blue">Ответы на Ваш экзамен</h3>
    <table>
        <c:if test="${exams != null}">
            <c:forEach items="${exams}" var="exam">
                <c:if test="${exam.facultyId == applicant.facultyId}">
                    <table>
                        <tr>
                            <td>Вопрос: ${exam.question}</td>
                            <td>Ответ: ${exam.answer}</td>
                        </tr>
                    </table>
                </c:if>
            </c:forEach>
        </c:if>
    </table>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться на начальную страницу</a>
</center>
</body>
</html>
