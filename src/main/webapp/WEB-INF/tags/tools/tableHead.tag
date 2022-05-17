<%--
    1. 显示表头
        >传入表头List<String>集合thList;
    2.
--%>
<%@attribute name="thList" type="java.util.ArrayList"%>
<%@attribute name="captionData"%>
<caption><%=captionData%>></caption>
<%if (thList != null && thList.size() != 0) {%>
    <th>
        <%for (int i = 0; i < thList.size(); i++){%>
            <td><%=((String)thList.get(i)).toString()%></td>
        <%}%>
    </th>
<%}%>





