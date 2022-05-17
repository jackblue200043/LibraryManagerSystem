<%@ taglib prefix="tags-tools"  tagdir="/WEB-INF/tags/tools" %>
<%@ page import="povo.Users" %>
<%@ page import="povo.Books" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/22/2019
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <title>修改图书信息</title>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/table.css"%>" rel="stylesheet" type="text/css"/>
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
    <style>
        #table {
            margin-top: 50px;
        }
        #table tr{
            height: 39px;
        }
        #table input[type="submit"] {
            width: 80px;
            height: 25px;
        }
    </style>
</head>
<body>
<tags-tools:nav></tags-tools:nav>
<%
    boolean flag = true;
    Books book = (Books) request.getAttribute("bookPo");
    if (book == null) {
        flag = false;
    }
%>

<%if(!flag) {%>
<script>alert("请登入")</script>
<%response.sendRedirect(path+"/index.jsp");%>
<%} else {%>
<form action="<%=path+"/administrator/ModifyBook"%>" method="post">
    <table id="table">
        <tr>
            <td>书号: </td>
            <td><input type="text"  readonly="readonly" name="bookId" value="<%=book.getBookId()%>"/></td>
        </tr>
        <tr>
            <td>书名:</td>
            <td><input type="text" name="bookName" value="<%=book.getBookName()%>" /> </td>
        </tr>
        <tr>
            <td>类型:</td>
            <td><input type="text" name="type" value="<%=book.getType()%>"/></td>
        </tr>
        <tr>
            <td>出版社:</td>
            <td><input type="text"  name="publisher" value="<%=book.getPublisher()%>" /></td>
        </tr>
        <tr>
            <td>作者:</td>
            <td><input type="text" name="author" value="<%=book.getAuthor()%>" /></td>
        </tr>
        <tr>
            <td>位置:</td>
            <td><input type="text" name="place" value="<%=book.getPlace()%>"/></td>
        </tr>
        <tr>
            <td>可借时间:</td>
            <td><input type="text" name="time" value="<%=book.getTime()%>"/></td>
        </tr>
        <tr>
            <td>状态:</td>
            <td><input readonly="readonly" type="text" name="status" value="<%=book.getStatus() > 0 ? "存在" : "借出"%>"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
<%}%>
</body>
</html>
