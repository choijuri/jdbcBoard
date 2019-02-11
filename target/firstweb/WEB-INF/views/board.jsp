
<%--
  Created by IntelliJ IDEA.
  User: chlwn
  Date: 2019-01-09
  Time: 오후 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta charset="utf-8">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
    <h1 style="text-align:center; margin-top:50px;">게시판 만들기 </h1>
    <div style="width:50%; margin:0 auto; padding-top:50px;">
        <c:choose>
            <c:when test="${sessionScope.loginuser == null}">
                <input type="button" onclick="location.href='/join'" value="회원가입" class="btn btn-default"  style='width:15%; float:right;'>
                <input type="button" onclick="location.href='/login'" value="로그인" class="btn btn-default"  style='width:15%; float:right;'>
            </c:when>
            <c:when test="${sessionScope.loginuser != null}">
                ${ sessionScope.loginuser.nickname } 님
                <input type="button" onclick="location.href='/write'" value="글쓰기" class="btn btn-default"  style='width:15%; float:right;'>&nbsp;
                <input type="button" onclick="location.href='/logout'" value="로그아웃" class="btn btn-default"  style='width:15%; float:right;'>
            </c:when>
        </c:choose>
    </div>


    <table class = "table table-bordered table-hover" style='width:50%; margin:50px auto;'>
        <thead style="background-color:#ebebeb;">
        <th width="10%">글번호</th>
        <th width="45%">제목</th>
        <th width="15%">작성자</th>
        <th width="15%">날짜</th>
        <th width="15%">조회수</th>
        </thead>
        <tbody>


        <c:forEach items="${boards}" var="board">
            <tr>
                <td>${board.thread}</td>
                <td><a href= "/read?id=${board.id}">${board.title} </a></td>
                <td>${board.nickname}</td>
                <td>${board.regdate}</td>
                <td>${board.readCount}</td>
            </tr>
        </c:forEach>

        </tbody>

    </table>

    <div class="text-center" style="width:50%; margin:0 auto; margin-top:50px;">
        </br>
        <ul class = "pagination">
            <li><a href="#">&laquo;</a></li>
            <li><a href="/board?page=1">1</a></li>
            <li><a href="/board?page=2">2</a></li>
            <li><a href="/board?page=3">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">6</a></li>
            <li><a href="#">7</a></li>
            <li><a href="#">8</a></li>
            <li><a href="#">9</a></li>
            <li><a href="#">10</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>



        <div class="box1" style="width:40%; margin:3% auto 10%;">
            <div class="form-group" style="width:17%; margin-right:2%; float:left;">
                <label class="sr-only" for="sop">sop</label>
                <select name=sop class="form-control">
                    <option value=제목>제목</option>
                    <option value=내용>내용</option>
                    <option value=작성자>작성자</option>
                </select>
            </div>
            <div class="form-group" style="width:60%; float:left;">
                <label class="sr-only" for="stx">stx</label>
                <input name=stx maxlength=15 size=10 itemname="검색어" required value='' class="form-control">
            </div>
            <div class="form-group" >
                <button class="btn btn-default " style="float: right; width:17%;">검색</button>
            </div>
        </div>
    </div>

</body>
</html>





