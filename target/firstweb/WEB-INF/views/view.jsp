<%--
  Created by IntelliJ IDEA.
  User: chlwn
  Date: 2019-01-09
  Time: 오후 4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="view.css" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="view.css">
    <title>view</title>
    <style>
        #container {padding : 15px 0  10px 0; padding-right: 10%; padding-left: 10%;}
        #view-top{height: 50px;border-bottom: 2px solid gray;}
        #view-title {font-size: 2.0em; /* 40px/16=2.5em */height: 50px;}
        #view-date {font-size: 1.0em; /* 40px/16=2.5em */height: 50px;line-height: 50px;color: gray;}
        #view-writer {font-size: 1.0em; /* 40px/16=2.5em */height: 50px;line-height: 50px;color: gray;}
        #content{margin : 10px 5px  10px 5px;height: 70%;border-bottom: double gray;}
        #view-bottom > button{}
    </style>
</head>
<body>
<div id = "container">
    <div id = "view-top" class="page-header">
        <div id = "view-title" class="col-md-6 col-xs-6">${board.title}</div>
        <div id = "view-date" class="col-md-2 col-md-offset-2 col-xs-2 col-xs-offset-2">${board.regdate}</div>
        <div id = "view-writer" class="col-md-2 col-xs-2">${board.nickname}</div>
    </div>
    <div id = "content">
        ${board.content}
    </div>
    <div id = "view-bottom">
                <c:if test="${signedId == board.userId}">
                    <button type="button" class="btn btn-default col-md-2 col-md-offset-6 col-xs-2 col-xs-offset-4" id = "btn-update" onClick="location.href='/update?id=${board.id}'">수 정</button>
                    <button type="button" class="btn btn-default col-md-2 col-xs-2" id = "btn-delete"onClick="location.href='/delete?id=${board.id}'" >삭 제</button>

                </c:if>
                <button type="button" class="btn btn-default col-md-2 col-xs-2" id = "btn-delete"onClick="location.href='/reply?id=${board.id}'" >답 글</button>

        <button type="button" class="btn btn-default col-md-2 col-xs-2" id = "btn-list" onClick="location.href='/board'">목 록</button>
    </div>
</div>

</body>
</html>
