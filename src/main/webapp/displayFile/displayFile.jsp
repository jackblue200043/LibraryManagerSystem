<%@ page import="povo.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/23/2019
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>文件列表</title>
    <link href="<%=path+"/css/table.css"%>" rel="stylesheet" type="text/css" >
    <link href="<%=path+"/css/displayFile.css"%>" rel="stylesheet" type="text/css" >
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
<html:nav></html:nav>
<%
    boolean flag = false;
    List<File> list = (List<File>)request.getAttribute("fileList");
    if (list != null) {
        flag = true;
    }
%>
    <table id="table">
        <caption>文件列表</caption>
        <tr>
            <th>序号</th><th>文件名</th><th>文件类型</th><th>大小</th><th>查看</th><th>下载</th><th>删除</th>
        </tr>
        <%
            for (int i = 0; list != null && i < list.size(); i++) {
            File file = list.get(i);
        %>
        <%-- 输出文件列表并输出下载链接--%>
        <tr>
            <td><%=i%></td>
            <td><%=file.getFileName()%></td>
            <td><%=file.getFileType()%></td>
            <td><%=file.getSize()%></td>
            <%
                //生成查看地址, 将fileId传给servlet
                String url = "<a " + "href=" + "\"" + path + "/displayFile/viewOneFileByTxtImg" + "?fileId=" + file.getFileId() + "\" " + "target=\"_blank\"" + ">" + "查看" + "</a>";
            %>
            <td><%="bmp,jpg,png,tif,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp".indexOf(file.getFileType()) > -1 ? url : "无法查看"%></td>
            <td><a href="<%=path+"/displayFile/downloadFile?fileId="+file.getFileId()%>">下载</a></td>
            <td><a href="<%=path+"/displayFile/deleteFile?fileId="+file.getFileId()%>">删除</a></td>
        </tr>
        <%}%>
    </table>
<div id="download">
    <a href="#" onclick="window.open('<%=path+"/displayFile/upload.jsp"%>', '-top', 'height=400, width=500, top=200,left=500,resizable=no,location=no')">传</a>
</div>
</body>
</html>
