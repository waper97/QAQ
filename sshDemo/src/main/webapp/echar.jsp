<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/28
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <div id="main" style="width: 600px;height: 400px">

    </div>

<script src="rescource/js/jquery-3.4.0.js"></script>
<script src="rescource/js/echarts.js"></script>
<script>
    //初始化echar
    var echarts = echarts.init(document.getElementById("main"))
    //指定图表的配置与数据
    var option = {
            title:{
                text:'Echar入门实例'
            }, xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            tooltip: {},
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        }
    echarts.setOption(option)
    //处理点击事件并调到相应的页面
    echarts.on('click',function (params) {
        console.log(params)
        // window.open('www.baidu.com/s?wd='+params.name)
    })
    $(function () {
       $.ajax({
           method:'get',
           url:'getJsonData',
           success:function (data) {
               console.log(data)
           }
       })
    })
</script>
</body>
</html>
