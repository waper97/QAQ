<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/29
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("name","张三");
%>
<%
    String name = (String)pageContext.getAttribute("name");
%>
<h1>获取到的page对象:<%=name%></h1>
</body>
</html>
