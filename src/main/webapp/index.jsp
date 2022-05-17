<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="povo.Books" %>
<%@ page import="java.util.List" %>
<%@ page import="povo.Users" %>
<%@ page import="service.BookStatus" %><%--
  Created by IntelliJ IDEA.
  User: 19146
  Date: 12/15/2019
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/tools" %>
<%
    Users user = (Users)session.getAttribute("userId");
    String path = request.getContextPath();
%>
<html>
  <head>
    <title>图书管理系统</title>
    <link href="<%=path+"/css/all.css"%>" rel="stylesheet" type="text/css"/>
      <link href="<%=path+"/css/table.css"%>" rel="stylesheet" type="text/css"/>
      <link rel="icon"  href="<%=path+"/img/title.ico"%>" />
  </head>
  <body>
    <html:nav></html:nav>
    <%
        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.add("书名");fieldList.add("书本编号");fieldList.add("类型");
        fieldList.add("出版社");fieldList.add("作者");
    %>
  <html:search isMuHu="yes" page="BooksPageDisplay" methodValue="post"
               fieldList="<%=fieldList%>">
  </html:search>

<%--   要求: 使用搜索框进行查看书籍信息, 然后显示出来, 在每一页显示合适的条数;
        控制:
            >需要在初始状态下, 没有数据, 不显示搜索内容(控制servlet过来的);
            使用BooksPageDisplayServlet.java重定向到本页面, 有
                List<Books> list: 代表搜索结果的list集合;
                Boolean isHas: 显示是否搜索到了结果;
        可能的情况:
            >如果第一次(没有通过BooksPageDisplayServlet进入), 那么isHas 为null;
            >如果数据库中, 没有搜索到数据, 那么List<Books>中是没有数据的, size为0;
             将isHas设置为false;
            >如果数据库中查询到了数据, 那么List<Books> list, 有数据, size不为0;
             将isHas设置为 true;
         如何控制分页:
           >
--%>
  <%
     //用于显示查询表头
      String title = "Not Date";
      //用于标记是否第一次进入页面, 或不是通过servlet进入页面;
      boolean bool = false;
      //或取isHas的值
      if (request.getAttribute("isHas") != null) {
          bool = (boolean)request.getAttribute("isHas");
      } else {

      }
      //获取list的值, 获取查询列表, hasList看list是否为空;
      Boolean hasList = false;
      List<Books> list = null;
      if (request.getAttribute("BookList") != null)
      {
          list = (List<Books>) request.getAttribute("BookList");
          if (list.size() > 0) {
              title = "结果";
              hasList = true;
          }
      }
  %>
<%--    生成表头--%>
    <%
        //是否通过servlet跳入的页面
        if (request.getAttribute("isHas") != null) {
    %>

        <table id="table">
            <caption><%=title%></caption>
            <tr>
                <th>书本编号</th><th>书名</th><th>类型</th><th>出版社</th><th>作者</th>
                <th>位置</th><th>状态</th><th>可借阅天数</th><th>书评</th>
                <%=(user != null) && user.getStatus().equals("root") ? "<th>管理</th>" : ""%>
            </tr>

            <%
                //如果是从servlet进入的, 那么就判断是否有信息, list是否为空, 或为null;
                if (hasList == true) {
            %>
                <%
                    //输出搜索结果
                    for(int i = 0; i < list.size(); i++) {
                        Books book = list.get(i);
                %>
                    <tr>
                        <td><%=book.getBookId()%></td>
                        <td><%=book.getBookName()%></td>
                        <td><%=book.getType()%></td>
                        <td><%=book.getPublisher()%></td>
                        <td><%=book.getAuthor()%></td>
                        <td><%=book.getPlace()%></td>
                        <%=book.getStatus() > 0 ? "<td>存在</td>": "<td style=\"color:red\">借出</td>"%>
                        <td><%=book.getTime()%></td>
                        <td><a href="MessageBoard?bookId=<%=book.getBookId()%>">书评</a></td>
                        <%String url = "<a href="+ "\""+ path + "/administrator/BookMenage?bookId="+book.getBookId()+"\""+">" +"管理"+ "</a>";%>
                        <%=user != null && user.getStatus().equals("root") ? "<td>"+url+"</td>" : ""%>
                    </tr>
                    <%
                    }
                    %>
            <tr>
<%--                显示分页按钮--%>
                <td colspan="9"><a href="<%="BooksPageDisplay?page="+(Integer.parseInt(request.getParameter("page"))-1)%>">上一页</a>
                <a href="<%="BooksPageDisplay?page="+(Integer.parseInt(request.getParameter("page"))+1)%>">下一页</a></td>
            </tr>
            <%
                }
            %>
    </table>
    <%
        }
    %>
<%--  在获取isHas的值时, 不为null, 打印表格信息--%>
  </body>
</html>