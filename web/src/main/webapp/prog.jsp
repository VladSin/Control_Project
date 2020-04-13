<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 12.04.2020
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тест</title>
</head>
<body onload="var v_date_init = new Date(); tm_init = v_date_init.getTime();">

<h1 class="breadcrumb">Тест начался! Удачи!</h1>
<form action="${pageContext.request.contextPath}/prog" method="post">
    <div>
        <div class="question">
            <span class="question_n">Programming: question 1: </span> Expect the first high-level programming language
        </div>
        <ul>
            <li><input type="radio" name="test[0]" value="0" id="test_0_0"  /> <label for="test_0_0">Java</label></li>
            <li><input type="radio" name="test[0]" value="1" id="test_0_1"  /> <label for="test_0_1">C++</label></li>
            <li><input type="radio" name="test[0]" value="2" id="test_0_2"  /> <label for="test_0_2">Pascal</label></li>
            <li><input type="radio" name="test[0]" value="3" id="test_0_3"  /> <label for="test_0_3">Fortran</label></li>
            <li><input type="radio" name="test[0]" value="4" id="test_0_4"  /> <label for="test_0_4">Python</label></li>
        </ul>
        <div class="question">
            <span class="question_n">Programming: question 2: </span> True||False
        </div>
        <ul>
            <li><input type="radio" name="test[1]" value="0" id="test_1_0"  /> <label for="test_1_0">True</label></li>
            <li><input type="radio" name="test[1]" value="1" id="test_1_1"  /> <label for="test_1_1">False</label></li>
            <li><input type="radio" name="test[1]" value="2" id="test_1_2"  /> <label for="test_1_2">NULL</label></li>
            <li><input type="radio" name="test[1]" value="3" id="test_1_3"  /> <label for="test_1_3">ERROR</label></li>
        </ul>
        <div class="question">
            <span class="question_n">Programming: question 3: </span> 18, 46, 94, ?, 52, 61
        </div>
        <ul>
            <li><input type="radio" name="test[2]" value="0" id="test_2_0"  /> <label for="test_2_0">21</label></li>
            <li><input type="radio" name="test[2]" value="1" id="test_2_1"  /> <label for="test_2_1">36</label></li>
            <li><input type="radio" name="test[2]" value="2" id="test_2_2"  /> <label for="test_2_2">54</label></li>
            <li><input type="radio" name="test[2]" value="3" id="test_2_3"  /> <label for="test_2_3">63</label></li>
            <li><input type="radio" name="test[2]" value="4" id="test_2_4"  /> <label for="test_2_4">58</label></li>
        </ul>

        <input type="submit" name="input_submit" value="Узнать результат" />
    </div>
</form>

</body>
</html>
