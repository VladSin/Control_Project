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
    <h3 style="color: darkslateblue">Страница на доработке((( Приносим свои извинения! </h3>
    <%--<jsp:useBean id="applicants" scope="request" type="it_academy.control_project.web.servlet.TableServlet"/>
    <c:if test="${applicants != null}">
        <table>
            <tr>
                <th>ID абитуриента</th>
                <th>Оцека</th>
            </tr>
        </table>
    </c:if>
    <jsp:useBean id="applicants" scope="request" type="java.util.List"/>
    <c:forEach items="${applicants}" var="applicant">
        <tr>
            <td>${applicants.id}</td>
            <td>${applicants.mark}</td>
        </tr>
    </c:forEach>--%>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться на начальную страницу</a>
</center>
</body>
</html>