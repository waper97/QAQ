<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/29
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show</title>
</head>
<body>
<%!
    public static final String DRIVERNAME ="oracle.jdbc.driver.OracleDriver";
    public static final String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USERNAME ="system";
    public static final String PWD ="123456";
%>
<%
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
%>
<%
    try {
        Class.forName(DRIVERNAME);
        con = DriverManager.getConnection(URL,USERNAME,PWD);
        String sql = "select * from emp";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

%>

    <table border="1" width="100%">
        <tr>
            <td>雇员编号</td>
            <td>雇员名称</td>
            <td>雇员工作</td>
            <td>雇员工资</td>
            <td>雇佣日期</td>
        </tr>
        <%
            while (rs.next()){
                int emptno = rs.getInt(1);
                String empname = rs.getString(2);
                String job = rs.getString(3);
                Date date = rs.getDate(4);
                float sal = rs.getFloat(5);

        %>
        <tr>
            <td><%=emptno%></td>
            <td><%=empname%></td>
            <td><%=job%></td>
            <td><%=date%></td>
            <td><%=sal%></td>

        </tr>
        <%
            }
        %>
        <%
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //关闭资源
                rs.close();
                pst.close();
                con.close();
            }
        %>
    </table>
</body>
</html>
