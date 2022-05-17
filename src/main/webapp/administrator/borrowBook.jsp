<%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/26/2019
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>外借图书</title>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/info.css"%>" rel="stylesheet" type="text/css" />
    <link href="<%=path+"/css/addBookAndUser.css"%>" rel="stylesheet" type="text/css" />
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
<html:nav></html:nav>
<div style="width: 965px; margin: 0 auto">
    <div id="info">
        <h2 align="center">外借须知</h2>
        <ol>
            <li>输入用户名</li>
            <li>输入书号</li>
            <li>下方会出现用户名相关的信息</li>
            <li>下方出现书本信息</li>
            <li>点击提交即可借书</li>
        </ol>
    </div>
    <div id="main">
        <form action = "<%=path+"/administrator/BorrowBook"%>" method="post" onsubmit="return checkform()">
            <table id="table">
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="userName" /></td>
                </tr>
                <tr>
                    <td>书号:</td>
                    <td><input type="text" name="bookId" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button style="margin-left: 105px" type="submit">提交</button>
                    </td>
                </tr>
            </table>
        </form>
        <div id="jsPoint"><div/>
        </div>
    </div>
</div>
<%--	通过给id位jsPoint的节点添加p标签节点;--%>
<script src="<%=path+"/js/addNodeByP.js"%>" type="text/javascript" charset="utf-8" ></script>
<script src="<%=path+"/js/formCheckByBorrow.js"%>" type="text/javascript" charset="utf-8" ></script>
</body>
</html>
