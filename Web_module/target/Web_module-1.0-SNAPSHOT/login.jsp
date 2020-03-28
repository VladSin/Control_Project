<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 26.03.2020
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>Login</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="login">login</label>
    <input id="login" type="text" name="login"> <br/>

    <label for="password">password</label>
    <input id="password" type="password" name="password"><br/>
    <input type="submit">
</form>

<p style="color: red">${error}</p>
