<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/21
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th>Student-ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>e001</td>
        <td>Rammohan </td>
        <td>Reddy</td>
        <td>A+</td>
    </tr>
    </tbody>
</table>
<script src="sources/jquery-3.3.1.js"></script>
<script src="sources/bootstrap-4.3.1-dist/js/bootstrap.js"></script>
<script>
    $(function () {
        $.post("/getUserList", function (a) {
            console.log(a)
        }, "post")

        $.ajax({
            url:"/getUserList",
            method:"post",
            success:function (data) {
                console.log(data)
            }
        })
    })
</script>
</body>
</html>
