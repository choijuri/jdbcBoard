<%--
  Created by IntelliJ IDEA.
  User: chlwn
  Date: 2019-01-09
  Time: 오후 5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>

    <title>writeform</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="modifyform.css">

</head>
<body>

<div class="container">
    <form action="/write" method="post">
    <div class="page-header">
        <h2>글쓰기</h2>
    </div>
    <div class="content">
        <div class="row">


        <div class="form-group">
            <label for="title">제목:</label>
            <input type="text" id="title" class="form-control" placeholder="Enter Title" name="title">
        </div>
        <div class="form-group">
            <label for="content">내용:</label>
            <textarea class="form-control" rows="12" id="content" name="content"></textarea>
        </div>
        <div class="buttons" style="float: right;">
            <button type="button" class="btn btn-default" onClick="location.href='/board'">취소</button>
            <input type="submit" class="btn btn-default" value="등록" >
        </div>
        </form>
    </div>
</div>
</div>


</body>
</html>
