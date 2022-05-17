<%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/24/2019
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <title>上传</title>
    <link href="<%=path+"/css/upload.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
    <div id="upload">
        <form action="<%=path+"/displayFile/upload"%>" method="post" ENCTYPE="multipart/form-data" accept-charset="UTF-8">
            <input type="file" name="file1">
            <input type="file" name="file2">
            <input type="file" name="file3">
            <input type="file" name="file4">
            <input type="file" name="file5">
            <input type="file" name="file6">
            <input type="submit" value="上传">
        </form>
    </div>
</body>
</html>
