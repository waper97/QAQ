<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/30
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script src="js/jquery-1.10.min.js"></script>
<script>
    $(function () {
        let data =
            {
                "menuType":1,
                "dishName":"小野鸡炖蘑菇二代",
                "rsDate":"2019-03-28 00:00:00",
                "cookerName":"王鹏","qty":"1",
                "rsUser":"王鹏","temperature":90,
                "rsTime":"2019-03-28 19:00:00",
                "rsEndTime":"2019-03-28 19:00:00","conductor":"王鹏",
                 attachmentList:[{thumbnailUrl: "thumbnailUrl", picUrl:"123"},{thumbnailUrl: "thumbnailUrl", picUrl:"123"}]
            }
        $.post("http://localhost:8604/sample/school/addSave",data)
    })
</script>
</body>
</html>
