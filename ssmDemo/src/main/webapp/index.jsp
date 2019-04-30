<%@ page import="com.waper.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
    <link rel="stylesheet" type="text/css" href="resource/css/bootstrap.min.css" >
</head>
<body>

    <table class="table">
        <tr>
            <td>ID</td>
            <td>书名</td>
            <td>页码</td>
            <td>出版时间</td>
            <td>操作</td>
        </tr>
        <c:forEach  items="${list}" var="list" >
            <tr>
                <td>${list.id}</td>
                <td>${list.name}</td>
                <td>${list.page}</td>
                <td>${list.publishDate}</td>
                <td>
                    <button class="btn btn-danger" id="del">删除</button>
                    <button class="btn btn-success" id="upd" >修改</button>
                </td>
            </tr>
        </c:forEach>
    </table>
<script src="resource/js/jquery-3.4.0.js"></script>
<script>
    $(function () {

        $("#del").click(function () {
            alert()
            $.ajax({
                url:'deleteById?id=1',
                method:'post',
                success:function (data) {
                    if(data.success){
                        alert("ok")
                    }
                }
            })
        })
    })
</script>
</body>
</html>
