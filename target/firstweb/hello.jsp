<%--
  Created by IntelliJ IDEA.
  User: chlwn
  Date: 2019-01-09
  Time: 오전 10:24
  To change this template use File | Settings | File Templates.
--%>
<!--지시문  WAS 에게 보내는 지시문-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
    <h1>hello </h1>

    <!--스크립틀릿-->
    <%
        for(int i=0; i<100; i++){
            out.write(i+"<br>");
        }
    %>

</body>
</html>

<!--
http://localhost8080/hello.jsp
-->