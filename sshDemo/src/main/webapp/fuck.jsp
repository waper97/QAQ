<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/29
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <script>
        document.write("<table border='1'>");
        for(var i = 1; i<=9;i++){
            document.write("<tr>")
            for(var j = 1;j<=i; j++){
                if(j<=i){
                    document.write("<td>"+i+"*"+j+"="+i*j+"</td>");
                }else{
                    document.write("<td>&nbsp;</td>")
                }
            }
            document.write("</tr>")
        }
        document.write("</table>");


        function validate(data){
           alert(data.form.email.value)
            alert("fucks");
        }
        function clickMe(){

            alert("fucks");
        }
    </script>
</head>
<body>

<% out.println("<h1>fuck</h1>"); %>
<%
    final  String FUCK="fuck";
    String str = "天上掉下了个林妹妹";
    out.println("<h1>"+FUCK+"</h1>");
%>
<%!
%>
      <a href="#" onclick="clickMe()">中文:<%=FUCK %></a>
      <form action="" method="post" name="form" onsubmit="validate(this)">
          email: <input type="text" name="email"/>
          <input type="submit" value="提交">
      </form>

        <table border="1" width="100%">
            <%
                int rows = 9;
                int cols = 9;
                for(int x = 0; x<= rows; x++){

            %>
            <tr>
                <%
                    for(int y = 0;y<cols;y++){
                %>
                <td><%=(x*y)%></td>
                <%
                    }
                %>
                <%
                    }
                %>
            </tr>
        </table>

</body>
</html>
