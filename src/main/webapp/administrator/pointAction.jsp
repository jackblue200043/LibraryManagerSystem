<%@ page import="util.characterUtil.StringUtil" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/31/2019
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<%
    String tishi = (String)request.getParameter("tishi");
    String urlStr = (String)request.getParameter("urlStr");
%>
<html>
<head>
    <title>提示</title>
    <link href="<%=path+"/css/pointAction.css"%>" rel="stylesheet" type="text/css"/>
</head>
<body>
<section class="wrapper">

    <div class="container">

        <div id="scene" class="scene" data-hover-only="false">


            <div class="circle" data-depth="1.2"></div>

            <div class="one" data-depth="0.9">
                <div class="content">
                    <span class="piece"></span>
                    <span class="piece"></span>
                    <span class="piece"></span>
                </div>
            </div>

            <div class="two" data-depth="0.60">
                <div class="content">
                    <span class="piece"></span>
                    <span class="piece"></span>
                    <span class="piece"></span>
                </div>
            </div>

            <div class="three" data-depth="0.40">
                <div class="content">
                    <span class="piece"></span>
                    <span class="piece"></span>
                    <span class="piece"></span>
                </div>
            </div>
            <p class="p404" data-depth="0.50"><%=tishi%></p>
            <p class="p404" data-depth="0.10"><%=tishi%></p>

        </div>

        <div class="text">
            <article>
                <p><br>即将返回</p>
            </article>
        </div>

    </div>
</section>
<script >
    window.setTimeout("location.href='<%=urlStr%>'", 3000)
</script>
</body>
</html>
