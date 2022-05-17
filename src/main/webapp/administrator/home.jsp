<%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/22/2019
  Time: 8:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <title>管理员</title>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/nav.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/info.css"%>" rel="stylesheet" type="text/css" />
    <link href="<%=path+"/css/home.css"%>" rel="stylesheet" type="text/css" />
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
<html:nav></html:nav>
<div style="width: 965px; margin: 0 auto;">
<div id="info">
    <h2 align="center">管理须知</h2>
    <ol>
        <li>此页面用于管理图书，与借阅图书， 还有账户信息的管理；</li>
        <li>添加账号与删除账号, 还有统一的账号管理，可以帮助用户找回密码等；</li>
        <li>请严格按照操作手册进行,书籍的登记，书籍的借阅；</li>
    </ol>
</div>
<div id="main">
    <div class="left1"><a href="addUser.jsp">添加账号</a></div>
    <div class="left2"><a href="returnBook.jsp">还书</a></div>
    <div class="right1"><a href="addBook.jsp">添加书籍</a></div>
    <div class="right2"><a href="borrowBook.jsp">外借图书</a></div>
</div></div>
</body>
</html>
