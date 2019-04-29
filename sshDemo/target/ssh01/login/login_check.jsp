<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/29
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    public static final String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USERNAME = "system";
    public static final String PASSWORD = "123456";
    public static final String DRIVERCLASSNAME = "oracle.jdbc.driver.OracleDriver";
%>
<%
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String userid = null;
    String userName = null;
    boolean flag = false;
%>
<%

    try {
        //加载驱动
        Class.forName(DRIVERCLASSNAME);
        //得到数据库连接
        con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        //执行预编译sql
        String sql = "select * from FUCKUSER where userName = ? and password = ?";
        pst = con.prepareStatement(sql);
        //设置参数
        pst.setString(1,request.getParameter("userName"));
        pst.setString(2,request.getParameter("password"));
        rs = pst.executeQuery();
        if(rs.next()){
            userid = rs.getString(1);
            userName = request.getParameter("userName");
            flag = true;
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        rs.close();
        pst.close();
        con.close();
    }
%>
<%
    if(flag){
%>
    <jsp:forward page="success.jsp">
        <jsp:param name="userid" value="<%=userid%>"/>
        <jsp:param name="userName" value="<%=userName%>"/>
    </jsp:forward>
<%
    }else{
%>
    <jsp:forward page="failed.jsp"/>
<%
    }
%>
</body>
</html>
