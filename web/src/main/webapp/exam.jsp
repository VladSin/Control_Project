<%--
  Created by IntelliJ IDEA.
  it_academy.control_project.data.User: dell
  Date: 09.04.2020
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница для абитуриентов</title>
</head>
<body>
<center>
<h2 style="color: green">Добро пожаловать, абитуриент!</h2>
<p>Пожалуйста, выберите предмет, по которому хотите проти тест</p>
<label for="exam"></label>
<input id="exam" type="text" list="examinations" />
<datalist id="examinations">
    <option value="Programming">
    <option value="Mathematics">
    <option value="Physics">
</datalist>
    <input type="submit" name="input_submit" value="Начать" />
</center>
</body>
</html>
