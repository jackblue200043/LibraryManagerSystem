<%@ page import="dao.doIt.BorrowDao" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/31/2019
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("tishi", "成功");
    request.setAttribute("urlStr", "urlStr");
    request.getRequestDispatcher("pointAction.jsp").forward(request, response);
%>
</body>
</html>
