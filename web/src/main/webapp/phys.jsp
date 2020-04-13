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
    <title>Тест</title>
</head>
<body onload="var v_date_init = new Date(); tm_init = v_date_init.getTime();">

<h1 class="breadcrumb">Тест начался! Удачи!</h1>
<form action="${pageContext.request.contextPath}/prog" method="post">
    <div>

        <div class="question">
            <span class="question_n">Physics: question 1: </span> Angle of incidence is equal to angle of reflection
        </div>
        <ul>
            <li><input type="radio" name="test[6]" value="0" id="test_6_0"  /> <label for="test_6_0">True</label></li>
            <li><input type="radio" name="test[6]" value="1" id="test_6_1"  /> <label for="test_6_1">False</label></li>
        </ul>
        <div class="question">
            <span class="question_n">Physics: question 2: </span> 123, 117, 108, 99, ?
        </div>
        <ul>
            <li><input type="radio" name="test[7]" value="0" id="test_7_0"  /> <label for="test_7_0">81</label></li>
            <li><input type="radio" name="test[7]" value="1" id="test_7_1"  /> <label for="test_7_1">83</label></li>
            <li><input type="radio" name="test[7]" value="2" id="test_7_2"  /> <label for="test_7_2">85</label></li>
            <li><input type="radio" name="test[7]" value="3" id="test_7_3"  /> <label for="test_7_3">87</label></li>
        </ul>
        <div class="question">
            <span class="question_n">Physics: question 3: </span> Who was the first author of the theory of relativity?
        </div>
        <ul>
            <li><input type="radio" name="test[8]" value="0" id="test_8_0"  /> <label for="test_8_0">Einstein</label></li>
            <li><input type="radio" name="test[8]" value="1" id="test_8_1"  /> <label for="test_8_1">Ampere</label></li>
            <li><input type="radio" name="test[8]" value="2" id="test_8_2"  /> <label for="test_8_2">Tesla</label></li>
            <li><input type="radio" name="test[8]" value="3" id="test_8_3"  /> <label for="test_8_3">Edison</label></li>
            <li><input type="radio" name="test[8]" value="4" id="test_8_4"  /> <label for="test_8_4">Poincare</label></li>
        </ul>

        <input type="submit" name="input_submit" value="Узнать результат" />
    </div>
</form>

</body>
</html>
