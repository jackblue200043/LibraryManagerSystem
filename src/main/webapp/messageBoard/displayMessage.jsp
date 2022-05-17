<%@ page import="povo.MessageBoard" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.doIt.UsersDao" %>
<%@ page import="dao.factory.FactoryDao" %>
<%@ page import="povo.Users" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/21/2019
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <title>$Title$</title>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/message.css"%>" rel="stylesheet" type="text/css"/>
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
<html>
<head>
    <title>评论</title>
</head>
<body>
<html:nav></html:nav>
<%
    List<MessageBoard> list = (List<MessageBoard>) request.getAttribute("messageList");
%>
<%
    //获取用户权限, 如果是root用户, 生成一个删除留言的按钮, 并使用servlet删除
    boolean flag = false;
    Users nowUser = (Users)session.getAttribute("userId");
    if (nowUser != null) {
        if (nowUser.getStatus().equals("root"))
            flag = true;
    }
    //获取bookId
    String bookId = request.getParameter("bookId");
%>
<%for(int i = 0; list != null && i < list.size(); i++) {%>
    <div id="message">
        <div class="text">
            <%=list.get(i).getMessage()%>
        </div>
        <div class="footer">
            <%
                //根据留言的javaBean获取数据, 然后, 查询用户名
                UsersDao userDao = FactoryDao.getUserDao();
                Users user = userDao.queryByUserName(list.get(i).getUserName());
                String userName = user.getNikName();
            %>
            WRITE BY <span><%=userName%></span>
        </div>
        <%if (flag) {%>
            <div id="delete"><a href="deleteMessage?num=<%=list.get(i).getNum()%>&bookId=<%=list.get(0).getBookId()%>">删</a></div>
        <%}%>
    </div>
<%}%>
<div id="write">
    <a href="#" onclick="window.open('messageBoard/writeMessage.jsp?bookId=<%=bookId%>', '-top', 'height=400, width=500, top=200,left=500,resizable=no,location=no')">写</a>
</div>

</body>
</html>
