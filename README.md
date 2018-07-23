# mybatis.flying 基于 spring-boot 的 demo

（对应当前最新版 flying 的分支为 `use-flying-0.9.4` ）

How to play？

1、获取代码（clone or fork），搭建成 maven 项目。

2、以 maven 命令执行 spring-boot:run

3、以下是初始化时的添加的数据源 dataSource：
```
<dataset>
	<ACCOUNT_ ID="1" NAME="ada"  PASSWORD="ada1ada"   ACTIVATED="1" OPLOCK="1"/>
	<ACCOUNT_ ID="2" NAME="bob"  PASSWORD="bob2bob"   ACTIVATED="1" OPLOCK="3"/>
	<ACCOUNT_ ID="3" NAME="carl" PASSWORD="carl3carl" ACTIVATED="1" OPLOCK="5"/>
	<ACCOUNT_ ID="4" NAME="dude" PASSWORD="dude4dude" ACTIVATED="1" OPLOCK="7"/>
	<ACCOUNT_ ID="5" NAME="eli"  PASSWORD="eli5eli"   ACTIVATED="1" OPLOCK="9"/>
</dataset>
```
现在，在浏览器中输入以下 url 可以看到效果：

查看一个帐号的详情：			http://localhost:8080/getAccount?id=${账号的id}

查看两个帐号的详情：			http://localhost:8080/getAccountById1OrId2?id1=${账号1的id}&id2=${账号2的id}
