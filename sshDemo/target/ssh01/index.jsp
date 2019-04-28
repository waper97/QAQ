<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="rescource/css/bootstrap.css">
</head>
<body>

    <div class="input-group-text">
        书名:
        <input type="text" id="bookName" class="form-control;" placeholder="请输入名称"  aria-describedby="emailHelp" style="width: 250px;"/>
        <a class="btn btn-success"  id="query">查询</a>
    </div>
    <div class="tab tab-content ">
        <form action="getBookById" method="get">
            <table width="500px"  class="table table-bordered" >
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col" >书名</th>
                    <th scope="col">页数</th>
                    <th scope="col">出版时间</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <s:iterator value="#list">
                    <tbody>
                    <tr>
                        <th>${id}</th>
                        <th>${name}</th>
                        <th>${page}</th>
                        <th>${publishdate}</th>
                        <th>
                            <a class="btn btn-danger" type="submit" href="delBookById?book.id=${id}">删除</a>
                            <button class="btn btn-primary">修改</button>
                        </th>
                    </tr>
                    </tbody>
                </s:iterator>

            </table>
        </form>
    </div>


<script src="rescource/js/jquery-3.4.0.js"></script>
<script>
    $(function(){
        $("#query").click(function () {
            var name =  $("#bookName").val()
            console.log(name)
            $.ajax({
                url:'getJsonData?book.name='+name,
                method:'get',
                success:function (data) {
                    console.log(data)
                }
            })
        })
    })
</script>
</body>
</html>
