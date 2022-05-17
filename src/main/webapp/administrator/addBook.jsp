<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<% String path = request.getContextPath(); %>
<html>
	<head>
		<meta charset="utf-8">
		<title>添加图书</title>
		<link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
		<link href="<%=path+"/css/info.css"%>" rel="stylesheet" type="text/css" />
		<link href="<%=path+"/css/addBookAndUser.css"%>" rel="stylesheet" type="text/css" />
		<link rel="icon"  href="<%=path+"/img/title.ico"%>" />
	</head>
	<body>
	<html:nav></html:nav>
	<div style="width: 965px; margin: 0 auto">
		<div id="info">
			<h2 align="center">添加图书须知</h2>
			<ol>
				<li>添加书本并对内容进行验证;</li>
				<li>书号: 以大写字母开头, 并由8位数字</li>
				<li>位置: 以A-D开头代表4个楼层, 后由2位数字代表书架号,然后以字母D-G开头, 后以数字代表编号</li>
				<li>其他: 请根据真是情况书写, 其中<span style="color:red;">库存与可借天数</span>不为空;</li>
			</ol>
		</div>
		<div id="main">
			<form action = "<%=path+"/administrator/doAddBook"%>" method="post" onsubmit="return checkform()">
				<table id="table">
					<tr>
						<td>书号</td>
						<td><input type="text" name="bookId" /></td>
					</tr>
					<tr>
						<td>书名</td>
						<td><input type="text" name="bookName" /></td>
					</tr>
					<tr>
						<td>上架类型</td>
						<td><input type="text" name="type" /></td>
					</tr>
					<tr>
						<td>出版社</td>
						<td><input type="text" name="publisher"></td>
					</tr>
					<tr>
						<td>作者</td>
						<td><input type="text" name="author"/></td>
					</tr>
					<tr>
						<td>位置</td>
						<td><input type="text" name="place" /></td>
					</tr>
					<tr>
						<td>可借天数</td>
						<td><input type="number" name="time" min="1" value="30" max="60"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit">提交</button>
						</td>
					</tr>
				</table>
			</form>
				<div id="jsPoint">
				<div/>
		</div>
		</div>
	</div>
<%--	通过给id位jsPoint的节点添加p标签节点;--%>
	<script src="<%=path+"/js/addNodeByP.js"%>" type="text/javascript" charset="utf-8" ></script>
	<script src="<%=path+"/js/formCheckByAddBook.js"%>" type="text/javascript" charset="utf-8" ></script>
	</body>
</html>
