<%@ page import="util.characterUtil.StringUtil" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/22/2019
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>

<html>
	<head>
		<meta charset="utf-8" />
		<title>用户登录</title>
		<link href="<%=path+"/css/login.css"%>" rel="stylesheet" type="text/css" />
		<link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
	<%
		String title;
		title = (String)request.getParameter("point");
		if (title == null) {
			title = "登入你的账号";
		} else {
			if (title.equals("1")) title = "密码错误";
			if (title.equals("2")) title = "账户不存在";
		}
	%>
	<form action="loginS" method="post">
		<div id="login">
			<div class="con">
				<p>欢迎登录</p>
				<input type="text" name="userName"   placeholder="<%=title%>"/>
				<input type="password" name="passWord"  placeholder="密码"/>
				<input type="submit"   value="登录" />
			</div>
		</div>
	</form>
</body>
</html>
