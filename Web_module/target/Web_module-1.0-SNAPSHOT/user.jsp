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
<h2>Добро пожаловать!</h2>
<c:if test="${users != null}">
    <table>
        <tr>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Телефон</td>
            <td>Email</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%= request.getAttribute("users")%>