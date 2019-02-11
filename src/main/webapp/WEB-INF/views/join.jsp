<%--
  Created by IntelliJ IDEA.
  User: chlwn
  Date: 2019-01-15
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>join</title>

    <link rel="stylesheet" type="text/css" href="join.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700&amp;subset=korean" rel="stylesheet">

</head>
<body>
<!-- ---------------------form------------------------------->
<div class="info">
    <h2>< 회원 정보 입력 ></h2>

    <div class="bord"></div>

    <form action="/join" method="post" onsubmit="return tocheckpw()">

        <fieldset id="sec">
            <legend>정보</legend>
            <ul>
                <li><label class="userId" for="name">이름</label>
                    <input type="text" id="name" name="name" placeholder="이름 입력" autofocus required>

                </li>

                <li><label class="userId" for="nickname">닉네임</label>
                    <input type="text" id="nickname" name="nickname" placeholder="닉네임 입력" autofocus required>

                </li>

                <li><label for="userPWD" class="labelStyle">비밀번호</label>
                    <input type="password" id="userPWD" name="userPWD" placeholder="영문 / 숫자 8~12자입력해주세요."  maxLength="12" required />
                </li>

                <li><label for="userPWD1" class="labelStyle">비밀번호 확인</label>
                    <input type="password" id="userPWD1" name="userPWD_" placeholder="영문 / 숫자 8~12자입력해주세요."  maxLength="12" required /> &nbsp; <span id = "check" style="color:red;"></span>
                </li>

                <li><label class="email" for="email">이메일</label>
                    <input type="text" id="email" name="email" placeholder="이메일을 입력해주세요." required>
                </li>

            </ul>
        </fieldset>

        <div class="bord"></div>



        <!----------------------버튼------------------------------------------>
        <div class="bt">

            <div class="before button"><a href="/board">이전</a></div>
            <input type="submit" value="가입" id="btnjoin"/>



        </div>


    </form>
</div>


<script type="text/javascript">
    function tocheckpw() {
        var userPWD = document.getElementById("userPWD").value;
        var userPWD1 = document.getElementById("userPWD1").value;

        if (userPWD != userPWD1) {
            document.getElementById('check').innerHTML = '비밀번호가 틀렸습니다. 다시 입력해 주세요';
            return false;
        }
    }
</script>

</body>
</html>
