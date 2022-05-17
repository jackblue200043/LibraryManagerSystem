<%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/25/2019
  Time: 7:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <% String path = request.getContextPath();%>
    <html>
    <head>
        <title>显示图片</title>
        <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
        <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
    </head>
    <body>
    <%
        String imgSrc = (String)request.getAttribute("imgSrc");
    %>
    <div style="text-align: center; margin-top: 100px">
    <img style="max-width: 600px; max-height: 500px" src="<%="../tempDir/"+imgSrc%>"/>
</div>

</body>
</html>
