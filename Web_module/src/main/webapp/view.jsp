<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 29.03.2020
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>View</title>
</head>
<body>
<h4>Здравствуйте!</h4>
<p>Здесь Вы с легкостью увидите, как уходит Ваша зарплата)))</p>

<form action="${pageContext.request.contextPath}/view.jsp" method="post">
    <label for="salary">Ваша зарплата: </label>
    <input id="salary" type="text" name="salary"><br/>

    <input type="submit">

</form>
<%= request.getAttribute("salaries")%>

<p>Результат вычислений: </p>

<%--@elvariable id="salaries" type="CalculatorSalaryServlet"--%>
<c:if test="${salaries != null}">
    <table>
        <tr>
            <th>Отчисления в белгострах</th>
            <th>Подоходный налог</th>
            <th>Профсоюзные отчисления</th>
            <th>Итого остаток: </th>
        </tr>
        <c:forEach items="${salaries}" var="salary">
            <tr>
                <td>${salary * 0.6 / 100}</td>
                <td>${salary * 13 / 100}</td>
                <td>${salary * 1 / 100}</td>
                <td>${salary - salary * 14.6 / 100}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
