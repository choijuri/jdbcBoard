<%--
  Created by IntelliJ IDEA.
  User: chlwn
  Date: 2019-01-20
  Time: 오후 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateform</title>
    <meta charset="utf-8">
    <meta nickName="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="modifyform.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h2>글 수정하기</h2>
    </div>
    <div class="content">
        <form action="/update" method="post">
            <input type="hidden" name="id" value="${board.id}">
            <div class="form-group">
                <label for="title">제목:</label>
                <input type="text" class="form-control" id="title"  name="title" value = "${board.title}">
            </div>
            <div class="form-group">
                <label for="content">내용:</label>
                <textarea class="form-control" rows="12" id="content" name="content"> ${board.content} </textarea>
            </div>
            <div class="buttons" style="float: right;">
                <button type="button" class="btn btn-default" onClick=" location.href='/board'">취소</button>
                <button type="submit" class="btn btn-default">등록</button>
            </div>
        </form>
    </div>
</div>


</body>
</html>
