<%@ tag pageEncoding="UTF-8" %>
<%@attribute name="isMuHu"%>
<%@ attribute name="methodValue"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="fieldList" required="true" type="java.util.ArrayList" %>
<link href="css/search.css" rel="stylesheet" type="text/css"/>
<div id="search">
<form id="form"  action="<%=page%>" method="<%=methodValue%>" >
    <select name="field">
        <%
            for (int i = 0; i < fieldList.size(); i++) {
        %>
        <option value="<%=((String)fieldList.get(i)).toString()%>"><%=((String)fieldList.get(i)).toString()%></option>
        <%}%>
    </select>
    <%if(isMuHu != null && !isMuHu.isEmpty()) {%>
    <input type="checkbox" value="yes" checked="checked" name="flag"/><span id="mohu">模糊搜索</span>
    <%}%>
    <input type="text" name="fieldValue" />
    <input type="submit" value="搜索">
</form>
</div>