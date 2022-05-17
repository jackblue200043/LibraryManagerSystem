<%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/25/2019
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>添加账户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/info.css"%>" rel="stylesheet" type="text/css" />
    <link href="<%=path+"/css/addBookAndUser.css"%>" rel="stylesheet" type="text/css" />
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
    <html:nav></html:nav>
    <div style="width: 965px; margin: 0 auto">
        <div id="info">
            <h2 align="center">注册须知</h2>
            <ol>
                <li>此页面用于添加图书馆借阅庄户, 并对正确性其进行验证；</li>
                <li>账号: 要6-12位, 并以字母开头;</li>
                <li>用户名: 要以4-10位的中文字符 数字 或字母(大小写);</li>
                <li>密码: 要以6-20位数字字母组成</li>
                <li>电话:要以正常格式书写, 地址不为空;</li>
            </ol>
        </div>
        <div id="main">
            <form action = "<%=path+"/administrator/doRegister"%>" method="post" onsubmit="return checkform()">
                <table id="table">
                    <tr>
                        <td>账号:</td>
                        <td><input type="text" name="userName" /></td>
                    </tr>
                    <tr>
                        <td>用户名:</td>
                        <td><input type="text" name="nikName" /></td>
                    </tr>
                    <tr>
                        <td>密码:</td>
                        <td><input type="text" name="passWord" value="123456" /></td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td>男<input checked type="radio" name="sex" value="男">
                        女<input type="radio" name="sex" value="女">
                        保密<input type="radio" name="sex" value="保密"></td>
                    </tr>
                    <tr>
                        <td>地址:</td>
                        <td><input type="text" name="address"/></td>
                    </tr>
                    <tr>
                        <td>电话:</td>
                        <td><input type="text" name="phoneNumber" /></td>
                    </tr>
                    <tr>
                        <td>权限</td>
                        <td>
                            normal<input type="radio" checked  name="status" value="normal"/>
                            root<input type="radio" name="status" value="root"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
            <div id="jsPoint">
            </div>
        </div>
    </div>
    <script src="<%=path+"/js/addNodeByP.js"%>" type="text/javascript" charset="utf-8" ></script>
    <script src="<%=path+"/js/formCheckByUser.js"%>" type="text/javascript" charset="utf-8" ></script>
    </body>
</html>
