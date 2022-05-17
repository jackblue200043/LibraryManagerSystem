<%@ tag import="povo.Users" %>
<%@tag pageEncoding="UTF-8" %>

<%
    Users user = (Users)session.getAttribute("userId");
    boolean flag = false;
    if (user != null) {
        flag = true;
    }
%>
<%String path = request.getContextPath();%>
<div id="ShowBynav">
    <div class="top">
        <%= flag ? "欢迎"+ "  " +user.getNikName() : "请登入"%>
    </div>
    <div class="button">
        <%if (flag) {%>
            <a href="<%=path + "/userTools/modifyUser.jsp"%>">修改信息</a>
            <a href="<%=path+"/logout"%>">注销</a>
        <%}else {%>
           <a href=<%=path + "/login.jsp"%>>登入</a>
        <%}%>

    </div>
</div>






