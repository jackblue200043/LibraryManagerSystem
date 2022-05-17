<%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/22/2019
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <title>写留言</title>
    <link href="<%=path+"/css/writeMessage.css"%>" rel="stylesheet" type="text/css"/>
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
    <div id="writeMessage">
        <form method="post" action="../writeMessage">
            <textarea name="messageText"></textarea>
            <input type="text" name="bookId" value="<%=request.getParameter("bookId")%>"/>
            <input type="submit" value="提交"/>
        </form>
    </div>
</body>
</html>
