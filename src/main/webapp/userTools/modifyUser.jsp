<%@ taglib prefix="tags-tools"  tagdir="/WEB-INF/tags/tools" %>
<%@ page import="povo.Users" %><%--
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
    <title>修改账户信息</title>
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
        Users user = (Users) session.getAttribute("userId");
        if (user == null) {
            flag = false;
        }
    %>

<%if(!flag) {%>
    <script>alert("请登入")</script>
    <%response.sendRedirect("index.jsp");%>
<%} else {%>
    <form action="<%=path+"/modifyUser"%>" method="post">
        <table id="table">
            <tr>
                <td>ID/usrName: </td>
                <td><input type="text"  readonly="readonly" name="userName" value="<%=user.getUserName()%>"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" name="passWord" value="<%=user.getPassWord()%>"></td>
            </tr>
            <tr>
                <td>名称:</td>
                <td><input type="text" name="nikName" value="<%=user.getNikName()%>"/></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td><input type="text"  name="sex" value="<%=user.getSex()%>" /></td>
            </tr>
            <tr>
                <td>地址:</td>
                <td><input type="text" name="address" value="<%=user.getAddress()%>" /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" name="phoneNumber" value="<%=user.getPhoneNumber()%>"/></td>
            </tr>
            <tr style="display: none">
                <td>状态:</td>
                <td><input readonly="readonly" type="text" name="status" value="<%=user.getStatus()%>"/></td>
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
