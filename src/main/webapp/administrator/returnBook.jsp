<%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/27/2019
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>还书</title>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/info.css"%>" rel="stylesheet" type="text/css" />
    <link href="<%=path+"/css/addBookAndUser.css"%>" rel="stylesheet" type="text/css" />
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
<%--
    1. 通过账户名, 使用Ajax通过UserName搜索账户名,
       使用账户名搜索借阅的未还纪录, 使用下拉框显示;
    2. 如果逾期, 在未还书中显示 (借阅编号:)逾A0000001;
    3. 提交, 更改图书记录, 显示已还;
    >如何使用js生成下拉框;
    1. 给Select生成option节点, 给节点
--%>

<html:nav></html:nav>
<div style="width: 965px; margin: 0 auto">
    <div id="info">
        <h2 align="center">还书须知</h2>
        <ol>
            <li>输入用户名</li>
            <li>输入用户名之后会出现你未还图书的记录;</li>
            <li>选择要还书的编号;</li>
            <li>点击提交</li>
        </ol>
    </div>
    <div id="main">
        <form action="<%=path+"/administrator/ReturnBook"%>" method="post" onsubmit="return checkform()">
            <table id="table">
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="userName" /></td>
                </tr>
                <tr>
                    <td>借书记录:</td>
                    <td><select style="width: 245px; height: 25px" name="borrowNum" id="borrowNum">
                    </select></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button style="margin-left: 105px" type="submit" >还书</button>
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
<script src="<%=path+"/js/returnBook.js"%>" type="text/javascript" charset="utf-8" ></script>
</body>
</html>
