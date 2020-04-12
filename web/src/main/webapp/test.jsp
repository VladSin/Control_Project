<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 11.04.2020
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Тест</title>
</head>

<body onload="var v_date_init = new Date(); tm_init = v_date_init.getTime();">

<div class="ui-widget">
    <div class="mys_total">
        <div class="mys_top_space">&nbsp;</div>

        <h1 class="breadcrumb">Тест начался! Удачи!</h1>

        <div class="clear_both"></div>
        <form action=${pageContext.request.contextPath}/exam" method="post">
            <div>
                <c:if test="${exam.faculty == 'Programming'}">
                    <div class="question">
                        <span class="question_n">Programming 1</span>. Вопрос по программированию 1
                    </div>
                    <ul>
                        <li><input type="radio" name="test[0]" value="0" id="test_0_0"  /> <label for="test_0_0">Java</label></li>
                        <li><input type="radio" name="test[0]" value="1" id="test_0_1"  /> <label for="test_0_1">C++</label></li>
                        <li><input type="radio" name="test[0]" value="2" id="test_0_2"  /> <label for="test_0_2">Pascal</label></li>
                        <li><input type="radio" name="test[0]" value="3" id="test_0_3"  /> <label for="test_0_3">Fortran</label></li>
                        <li><input type="radio" name="test[0]" value="4" id="test_0_4"  /> <label for="test_0_4">Python</label></li>
                    </ul>
                    <div class="question">
                        <span class="question_n">Programming 2</span>. Вопрос по программированию 2
                    </div>
                    <ul>
                        <li><input type="radio" name="test[1]" value="0" id="test_1_0"  /> <label for="test_1_0">True</label></li>
                        <li><input type="radio" name="test[1]" value="1" id="test_1_1"  /> <label for="test_1_1">False</label></li>
                        <li><input type="radio" name="test[1]" value="2" id="test_1_2"  /> <label for="test_1_2">NULL</label></li>
                        <li><input type="radio" name="test[1]" value="3" id="test_1_3"  /> <label for="test_1_3">ERROR</label></li>
                    </ul>
                    <div class="question">
                        <span class="question_n">Programming 3</span>. Вопрос по программированию 3
                    </div>
                    <ul>
                        <li><input type="radio" name="test[2]" value="0" id="test_2_0"  /> <label for="test_2_0">21</label></li>
                        <li><input type="radio" name="test[2]" value="1" id="test_2_1"  /> <label for="test_2_1">13</label></li>
                        <li><input type="radio" name="test[2]" value="2" id="test_2_2"  /> <label for="test_2_2">32</label></li>
                        <li><input type="radio" name="test[2]" value="3" id="test_2_3"  /> <label for="test_2_3">63</label></li>
                        <li><input type="radio" name="test[2]" value="4" id="test_2_4"  /> <label for="test_2_4">58</label></li>
                    </ul>
                </c:if>
                <c:if test="${exam.faculty == 'Mathematics'}">
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
                </c:if>
                <c:if test="${exam.faculty == 'Physics'}">
                    <div class="question">
                        <span class="question_n">Physics 1</span>.  Вопрос по физике 1
                    </div>
                    <ul>
                        <li><input type="radio" name="test[6]" value="0" id="test_6_0"  /> <label for="test_6_0">True</label></li>
                        <li><input type="radio" name="test[6]" value="1" id="test_6_1"  /> <label for="test_6_1">False</label></li>
                    </ul>
                    <div class="question">
                        <span class="question_n">Physics 2</span>. Вопрос по физике 2
                    </div>
                    <ul>
                        <li><input type="radio" name="test[7]" value="0" id="test_7_0"  /> <label for="test_7_0">81</label></li>
                        <li><input type="radio" name="test[7]" value="1" id="test_7_1"  /> <label for="test_7_1">83</label></li>
                        <li><input type="radio" name="test[7]" value="2" id="test_7_2"  /> <label for="test_7_2">85</label></li>
                        <li><input type="radio" name="test[7]" value="3" id="test_7_3"  /> <label for="test_7_3">87</label></li>
                    </ul>
                    <div class="question">
                        <span class="question_n">Physics 3</span>. Вопрос по физике 3
                    </div>
                    <ul>
                        <li><input type="radio" name="test[8]" value="0" id="test_8_0"  /> <label for="test_8_0">Einstein</label></li>
                        <li><input type="radio" name="test[8]" value="1" id="test_8_1"  /> <label for="test_8_1">Ampere</label></li>
                        <li><input type="radio" name="test[8]" value="2" id="test_8_2"  /> <label for="test_8_2">Tesla</label></li>
                        <li><input type="radio" name="test[8]" value="3" id="test_8_3"  /> <label for="test_8_3">Edison</label></li>
                        <li><input type="radio" name="test[8]" value="4" id="test_8_4"  /> <label for="test_8_4">Poincare</label></li>
                    </ul>
                </c:if>
                <input type="submit" name="input_submit" value="Узнать результат" />
            </div>
        </form>
    </div>
</div>
</body>
</html>

