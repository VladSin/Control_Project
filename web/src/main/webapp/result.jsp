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
<center>
    <h3 style="color: darkslateblue">Страница на доработке((( Приносим свои извинения! </h3>
   <%-- <jsp:useBean id="applicant" scope="request" type="com.sun.tools.javac.code.Scope.CompoundScope"/>
    <jsp:useBean id="faculty" scope="request" type="com.sun.tools.javac.code.Scope.CompoundScope"/>
    <c:if test="${applicant.mark ge faculty.mark}">
        <h1 style="color: green">Поздравляем!!!</h1>
    </c:if>

    <c:if test="${applicant.mark lt faculty.mark}">
        <h1 style="color: red">Не расстраивайтесь!!!</h1>
    </c:if>

    <table>
        <tr>
            <th>Ваш результат:</th>
            <th>Требования факультета:</th>
        </tr>

        <tr>
            <td><c:out value="${applicant.mark}"/></td>
            <td><c:out value="${faculty.mark}"/></td>
        </tr>
    </table>--%>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться на начальную страницу</a>
</center>
</body>
</html>
