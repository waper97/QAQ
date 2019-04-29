<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/29
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>forwardDemo</title>
</head>
<body>
<%
    String userName = "王鹏";
%>
<jsp:forward page="forwardDemo02.jsp">
    <jsp:param name="name" value="<%=userName%>"/>
    <jsp:param name="main" value="www.baidu.com"/>
</jsp:forward>
</body>
</html>
