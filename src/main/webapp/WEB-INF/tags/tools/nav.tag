<%@ tag import="povo.Users" %>
<%String path = request.getContextPath();%>
<link href="<%=path + "/css/index2.css"%>" rel="stylesheet" type="text/css"/>
<%@taglib prefix="tool" tagdir="/WEB-INF/tags/tools" %>
<%@ tag pageEncoding="utf-8"%>

<div id="mai">
		<div class="main_left ">
			<img src="<%=path+"/img/logo.jpg"%>" class="img">
		</div>
		<div class="main_right">
			<ul class="level">
				<li><a href="<%=path+"/index.jsp"%>">首页</a></li>
				<li><a href="<%=path+"/borrowTools/borrowInfo.jsp"%>">借阅信息</a></li>
				<li><a href="<%=path+"/displayFile/displayFile"%>">文件管理</a></li>
				<li>
					<a href="<%=path+"/administrator/home.jsp"%>">管理员</a>
					<ul class="dropdown">
						<li><a href="<%=path+"/administrator/addUser.jsp"%>">添加账号</a></li>
						<li><a href="<%=path+"/administrator/addBook.jsp"%>">添加书籍</a></li>
						<li><a href="<%=path+"/administrator/borrowBook.jsp"%>">外借图书</a></li>
						<li><a href="<%=path+"/administrator/returnBook.jsp"%>">归还图书</a></li>
					</ul>
				</li>
				<li>
				<%
					Users user = (Users)session.getAttribute("userId");
					if ( user == null) {%>
						<a href="<%=path+"/login.jsp"%>">登录</a>
					<%}else{%>
						<a href="<%=path + "/userTools/modifyUser.jsp"%>">欢迎<%=user.getNikName()%></a>
						<ul class="dropdown" >
						<li><a href="<%=path+"/logout"%>">注销</a></li>
						</ul>
					<%}%>
				</li>
			</ul>
		</div>
	</div>
	<div id="img">	
</div>


