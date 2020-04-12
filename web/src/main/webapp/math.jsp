<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 12.04.2020
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тест</title>
</head>
<body onload="var v_date_init = new Date(); tm_init = v_date_init.getTime();">

<h1 class="breadcrumb">Тест начался! Удачи!</h1>
<form action="${pageContext.request.contextPath}/prog" method="post">
    <div>

        <div class="question">
            <span class="question_n">Mathematics 1</span>. Вопрос по математике 1
        </div>
        <ul>
            <li><input type="radio" name="test[3]" value="0" id="test_3_0"  /> <label for="test_3_0">44</label></li>
            <li><input type="radio" name="test[3]" value="1" id="test_3_1"  /> <label for="test_3_1">14</label></li>
            <li><input type="radio" name="test[3]" value="2" id="test_3_2"  /> <label for="test_3_2">50</label></li>
            <li><input type="radio" name="test[3]" value="3" id="test_3_3"  /> <label for="test_3_3">49</label></li>
            <li><input type="radio" name="test[3]" value="4" id="test_3_4"  /> <label for="test_3_4">28</label></li>
        </ul>
        <div class="question">
            <span class="question_n">Mathematics 2</span>.  Вопрос по математике 2
        </div>
        <ul>
            <li><input type="radio" name="test[4]" value="0" id="test_4_0"  /> <label for="test_4_0">81</label></li>
            <li><input type="radio" name="test[4]" value="1" id="test_4_1"  /> <label for="test_4_1">97</label></li>
            <li><input type="radio" name="test[4]" value="2" id="test_4_2"  /> <label for="test_4_2">99</label></li>
            <li><input type="radio" name="test[4]" value="3" id="test_4_3"  /> <label for="test_4_3">121</label></li>
            <li><input type="radio" name="test[4]" value="4" id="test_4_4"  /> <label for="test_4_4">169</label></li>
        </ul>
        <div class="question">
            <span class="question_n">Mathematics 3</span>.  Вопрос по математике 3
        </div>
        <ul>
            <li><input type="radio" name="test[5]" value="0" id="test_5_0"  /> <label for="test_5_0">Descartes</label></li>
            <li><input type="radio" name="test[5]" value="1" id="test_5_1"  /> <label for="test_5_1">Fibonacci</label></li>
            <li><input type="radio" name="test[5]" value="2" id="test_5_2"  /> <label for="test_5_2">Newton</label></li>
            <li><input type="radio" name="test[5]" value="3" id="test_5_3"  /> <label for="test_5_3">Pythagoras</label></li>
            <li><input type="radio" name="test[5]" value="4" id="test_5_4"  /> <label for="test_5_4">Lagrange</label></li>
        </ul>

        <input type="submit" name="input_submit" value="Узнать результат" />
    </div>
</form>

</body>
</html>