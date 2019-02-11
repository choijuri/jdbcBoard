<%--
  Created by IntelliJ IDEA.
  User: chlwn
  Date: 2019-01-16
  Time: 오전 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="login_.css">
</head>
<body>
<form action="/login"  method="post" id="loginForm">
    <p class="ti1">BOARD LOGIN</p>
    <input type="hidden" name="redirect" value="${param.redirect}">
    <fieldset>
        <legend> LOGIN</legend>
        <div id="login">
            <p><label for="userId" class="labelStyle"></label>
                <input type="text" id="userId" name = "email" placeholder="이메일"/>
            </p>

            <p><label for="userPWD" class="labelStyle"></label>
                <input type="password" id="userPWD" name="userPWD" placeholder="비밀번호"/>
            </p>
        </div>
        <input type="submit" value="로그인" id="btnLogin"/>

        <!--
        <p id="btn1">
            <input type="checkbox" id="saveId" value="saveIdYes"/>
            <label for="saveId">아이디저장</label>
            <span class="find"><a href="#">아이디찾기</a> | <a href="#">비밀번호찾기</a> </span>
        </p>
        -->



    </fieldset>
</form>



</body>
</html>
