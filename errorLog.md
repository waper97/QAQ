# MarkdownPad2.5 注册码 [地址](https://www.jianshu.com/p/a927e97ca664)
 		`邮箱：`Soar360@live.com`
 		`授权秘钥`：GBPduHjWfJU1mZqcPM3BikjYKF6xKhlKIys3i1MU2eJHqWGImDHzWdD6xhMNLGVpbP2M5SN6bnxn2kSE8qHqNY5QaaRxmO3YSMHxlv2EYpjdwLcPwfeTG7kUdnhKE0vVy4RidP6Y2wZ0q74f47fzsZo45JE2hfQBFi2O9Jldjp1mW8HUpTtLA2a5/sQytXJUQl/QKO0jUQY4pa5CCx20sV1ClOTZtAGngSOJtIOFXK599sBr5aIEFyH0K7H4BoNMiiDMnxt1rD8Vb/ikJdhGMMQr0R4B+L3nWU97eaVPTRKfWGDE8/eAgKzpGwrQQoDh+nzX1xoVQ8NAuH+s4UcSeQ==
# 2019年3月20日 springboot支持jsp时遇到的问题 
	###### 添加springboot支持jsp的jar包
	<dependency>
	    <groupId>org.apache.tomcat.embed</groupId>
	    <artifactId>tomcat-embed-jasper</artifactId>
	    <!--<scope>provided</scope>-->
	</dependency>
		<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>javax.servlet-api</artifactId>
	    <scope>provided</scope>
	</dependency>
	<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>jstl</artifactId>
	</dependency>
	手动在`project Structure`点击`Modul`添加一个web
	然后在main路径下创建一个webapp，最后启动的时候用右侧的mave里的
	`Plugins`=>`spring-boot` =>`spring-boot:run`启动就可以访问到jsp，
	我也不知道是什么情况，网上找到一篇文章看到的：[链接](https://www.cnblogs.com/sxdcgaq8080/p/7712874.html)


# 在本地maven仓库里添加jar包
###      [oracle驱动官方下载地址](https://www.oracle.com/technetwork/cn/articles/oem/jdbc-112010-094555-zhs.html)
```
		oracle 驱动放入本仓库 mvn install:install-file -Dfile=你的jar包位置 -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
```
# springboot遇到的坑
	 springboot项目设置数据源的时候 spring.datasource.data-username 会报错 ：invalid username/password; logon denied；
	 正确的是 ：spring.datasource.username=system

# @RequestBody注解
@RequestBody：用户处理不是默认常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
，比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
 用 `实体类` 的时候就可 @RequestBody User user

    @RequestMapping("/addUser")
    public Object addUser(@RequestBody Users user){
        if(user.getAddress() == null||user.getAge() == null || user.getPassword()== null ||
        user.getUsername() == null){
            return new WebExecptionResolve(false,"必填信息不能为空",400);
        }
        user.setStatus(0);
        user.setId(CommUtil.getUUid());
        int result = userService.insert(user);
        if(result > 0){
            return new WebExecptionResolve(true,"添加成功",200);
        }
        return new WebExecptionResolve(false,"添加失败",2333);
    }

# 公司电脑配置	
	电脑型号	技嘉 B360 M AORUS PRO 台式电脑  (扫描时间：2019年03月28日)
	操作系统	Windows 10 64位 ( DirectX 12 )
		
	处理器	英特尔 Core i5-8400 @ 2.80GHz 六核
	主板	技嘉 B360 M AORUS PRO-CF ( CannonLake - A308 )
	内存	8 GB ( 芝奇 DDR4 2666MHz )
	主硬盘	三星 SSD 970 EVO 250GB ( 250 GB / 固态硬盘 )
	显卡	Nvidia GeForce GT 710 ( 2 GB / 微星 )
	显示器	冠捷 AOC2401 24B1W ( 23.5 英寸  )
	声卡	瑞昱 ALC892 @ 英特尔 High Definition Audio 控制器
	网卡	英特尔 Ethernet Connection  I219-V / 技嘉

# 解决vue路由不起作用的解决方案？
###   [link](https://blog.csdn.net/weixin_39289234/article/details/80868897) 	
 	最新学习vue js,发现路由配置后不起作用，每次都是重定向到首页，多次怀疑是路由写错了，后来发现vue默认情况下,访问子组件需要在uri后面添加#再加路由path值才可以，后来发现需要在路由文件里面设置history就没问题了。

我的疑问：
	之前没有看这篇文章的时候，就在想为毛每次输入地址，都重新定向到默认的首页去了，后面网上查了一下，才知道访问其他的子组件
	,要在之前url后面的#号后面加path就可以访问到了，如果不想要在#号后面加path，想直接url后面加path的话，就在路由文件里
	，加上`mode:'history'就可以了	

#mybatis遇到的问题：
###     ①    There is no getter for property named 'companyId' in 'class java.lang.String'
				原因是mapper接口的`参数`没有映射上,可以用@param 注解来解决 即：
				Staff getPersonnelStatisticsByOrgId(@Param("orgid") String orgid);
###	②myabtis 里resultMap 如果对应实体实体没有设置getter 和settter会报红报错2019年4月15日	
[图片](img-storage)

# 调用支付接口（官方demo）出现的问题：“支付宝调试错误，请回到请求来源地，重新发起请求”（2019年4月16日）
	原因是支付宝沙箱里的网关没有填导致的
# ssh(20190425) ①	框架遇到：org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
	   原因是：直接通过sessionFactory.getCurrentSession getCurrentSession是从当前上下文中获取Session并且会绑定到当前线程，第一次调用时会创建一个Session实例，如果该Session未关闭，后续多次获取的是同一个Session实例；事务提交或者回滚时会自动关闭Sesison,无需人工关闭
	   [参考链接](https://www.cnblogs.com/chyu/p/4817291.html)
# ② org.hibernate.UnknownEntityTypeException: Unable to locate persister: com.xxx.xxx.xx
	原因是:在hibernate 配置中没有设置实体位置
	  <property name="packagesToScan">
                    <list>
                        <value>com.waper.entity</value>
                    </list>
                </property>
	   


