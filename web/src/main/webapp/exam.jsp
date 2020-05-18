<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 09.04.2020
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Страница для абитуриентов</title>
    <link rel="stylesheet" type="text/css" href="resources/css/backgroundStyle.css">
</head>
<body>
<center>
    <h2 style="color: green">Добро пожаловать, абитуриент!</h2>
    <p>Вам предлагается выбрать один из трех факультетов для поступления на который, Вам необходимо будет пройти элементарное тестирование!</p>
    <p>Пожалуйста, выберите предмет, по которому хотите проти тест</p>
    <form action="${pageContext.request.contextPath}/faculty" method="post">

        <label for="exam"></label>
        <input id="exam" type="text" name="exam" placeholder="Choice" list="examinations" />
        <datalist id="examinations">
            <option value="Programming">
            <option value="Mathematics">
            <option value="Physics">
        </datalist>
        <input type="submit" name="exam" value="Start" />

    </form>
</center>
</body>
</html>
