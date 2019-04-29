<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/29
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/msword;charset=UTF-8" language="java" %>
<html>
<head>
    <title>指定文件为doc</title>
</head>
<body>
    <TABLE BORDER="1">
        <%
//            指定下在后的名称
            response.setHeader("Content-Disposition","attachment;filename=fuck.doc");
        %>
        <TR>
            <TD>hello </TD>

        </TR>
        <TR>
            <TD>  fuckyou </TD>
        </TR>
    </TABLE>

</body>
</html>
