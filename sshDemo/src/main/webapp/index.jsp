<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body>
<form action="getBookById" method="get">
    <table width="500px"  class="table" >
        <tr>
            <td>ID</td>
            <td>书名</td>
            <td>页数</td>
            <td>出版时间</td>
            <td>操作</td>
        </tr>

        <s:iterator value="#list">
        <tr>
                <td>${id}</td>
                <td>${name}</td>
                <td>${page}</td>
                <td>${publishdate}</td>
                <td>
                    <a class="btn-danger" type="submit" href="delBookById?book.id=${id}">删除</a>
                    <button class="btn-success">修改</button>
                </td>
        </tr>
        </s:iterator>

    </table>
</form>
<script src="jquery-3.4.0.js"></script>
</body>
</html>
