<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/20
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="sources/bootstrap-4.3.1-dist/css/bootstrap.css">
</head>
<body>
    <div class="container">
        <%--<form action="/upload" enctype="multipart/form-data" method="post" class="container-fluid">--%>
            <%--<input type="file" name="file" class="input input-group-text"/>--%>
            <%--<input type="submit" value="提交" class="btn btn-dark">--%>
        <%--</form>--%>
            ${name}
            ${age}

        <form action="/login" class="form">
            <div class="form-group">
                <label for="userName">用户名</label>
                <input type="text" name="userName" id="userName"/>
            </div>
             <div class="form-group">
                 <label for="password">密&emsp;码</label>
                 <input type="text" name="password" id="password"/>
             </div>
            <div class="group">
                <input type="submit" value="登陆" class="btn btn-dark"/>
            </div>
        </form>
    </div>
<script src="sources/jquery-3.3.1.js"></script>
<script src="sources/bootstrap-4.3.1-dist/js/bootstrap.js"></script>
</body>
</html>
