# springboot demo
#### `1.生成token`
#### `2.生成图片`

## oracle驱动放入本地maven仓库
######oracle 官方下载地址[https://www.oracle.com/technetwork/cn/articles/oem/jdbc-112010-094555-zhs.html]
    oracle 驱动放入本仓库 mvn install:install-file -Dfile={Path/to/your/ojdbc.jar} -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar