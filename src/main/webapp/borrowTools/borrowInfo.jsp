<%@ page import="povo.Users" %>
<%@ page import="dao.doIt.BorrowDao" %>
<%@ page import="dao.factory.FactoryDao" %>
<%@ page import="povo.Borrow" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.doIt.BooksDao" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/22/2019
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <title>借阅信息</title>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/table.css"%>" rel="stylesheet" type="text/css"/>
    <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
</head>
<body>
<html:nav></html:nav>
    <%
        //获取Users对象
        Users user = (Users)session.getAttribute("userId");
        //查询数据库, 找到借阅信息
        BorrowDao boDao = FactoryDao.getBorrowDao();
        List<Borrow> list = new ArrayList<Borrow>();
        //表头
        String title = "无借阅信息";
        if (user != null) {
            list = boDao.queryByUserName(user.getUserName());
            if (list != null&& list.size() >= 1) {
                title = "借阅信息如下";
            }

        }
        System.out.println(list.size());
        //获取书本dao, 使用书号查书名;
        BooksDao bookDao = FactoryDao.getBookDao();
    %>
    <table id="table" style="margin-top: 50px">
        <caption><%=title%></caption>
        <tr>
            <th>用户名</th><th>书名</th><th>开始日期</th><th>剩余日期</th><th>是否归还</th>
        </tr>

        <%for(int i = 0; list != null && i < list.size();i++) {%>
            <tr>
                <td><%=list.get(i).getUserName()%></td>
                <td><%=bookDao.queryByBookId(list.get(i).getBookId()).getBookName()%></td>
                <td><%=list.get(i).getStart()%></td>
                <td><%=list.get(i).getTimeLeft()%></td>
<%--这里flag标记, 当还了书, 剩余时间不在递减, 将flag设置为1;--%>
                <td><%=list.get(i).getFlag() > 0 ? "归还" : "未还"%></td>
            </tr>
        <%}%>
    </table>
</body>
</html>
