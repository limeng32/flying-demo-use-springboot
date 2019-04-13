# mybatis.flying 基于 spring-boot 的 demo

（对应当前最新版 flying 的分支为 `use-flying-0.9.9` ）

How to play？

1、获取代码（clone or fork），搭建成 maven 项目。

2、以 maven 命令执行 spring-boot:run

3、以下是初始化时的添加的数据源 dataSource，dataSource 描述了两个购物车和 12 种商品和商品装入购物车以及 3 种会员级别和 3 位用户的情况：
```
<dataset>
	<CART ID="1" DEAL="0" DEAL_TIME=null PERSON_ID="1"/>
	<CART ID="2" DEAL="0" DEAL_TIME=null PERSON_ID="2" />
	
	<COMMODITY ID="1" NAME="牙刷A" PRICE="1200" />
	<COMMODITY ID="2" NAME="牙刷B" PRICE="1850" />
	<COMMODITY ID="3" NAME="牙刷C" PRICE="2100" />
	<COMMODITY ID="4" NAME="佳洁士牙膏" PRICE="1499" />
	<COMMODITY ID="5" NAME="六必治牙膏" PRICE="1999" />
	<COMMODITY ID="6" NAME="云南白药牙膏" PRICE="2499" />
	<COMMODITY ID="7" NAME="潘婷洗发露" PRICE="3500" />
	<COMMODITY ID="8" NAME="多芬洗发露" PRICE="3900" />
	<COMMODITY ID="9" NAME="海飞丝洗发露" PRICE="5100" />
	<COMMODITY ID="10" NAME="浴液-1500ML" PRICE="2800" />
	<COMMODITY ID="11" NAME="浴液-2000ML" PRICE="3200" />
	<COMMODITY ID="12" NAME="浴液-4000ML" PRICE="4900" />
	
	<CART_COMMODITY ID="1" CART_ID="1" COMM_ID="1" AMOUNT="3" />
	<CART_COMMODITY ID="2" CART_ID="1" COMM_ID="5" AMOUNT="4" />
	<CART_COMMODITY ID="3" CART_ID="1" COMM_ID="8" AMOUNT="1" />
	<CART_COMMODITY ID="4" CART_ID="1" COMM_ID="12" AMOUNT="1" />
	<CART_COMMODITY ID="5" CART_ID="2" COMM_ID="2" AMOUNT="2" />
	<CART_COMMODITY ID="6" CART_ID="2" COMM_ID="4" AMOUNT="1" />
	<CART_COMMODITY ID="7" CART_ID="2" COMM_ID="9" AMOUNT="2" />
	<CART_COMMODITY ID="8" CART_ID="2" COMM_ID="11" AMOUNT="1" />
	
	<ROLE ID="1" NAME="普通会员" VALUE="normal" />
	<ROLE ID="2" NAME="银牌会员" VALUE="silver" />
	<ROLE ID="3" NAME="金牌会员" VALUE="gold" />
	
	<PERSON ID="1" NAME="张三" ROLE_ID="1" />
	<PERSON ID="2" NAME="李四" ROLE_ID="2" />
	<PERSON ID="3" NAME="王五" ROLE_ID="3" />
</dataset>
```

现在，在浏览器中输入以下 url 可以看到效果：

查看购物车：			http://localhost:8080/getCart?id=${购物车cart的id}

查看商品：			http://localhost:8080/getCommodity?id=${商品commodity的id}

翻页查看商品（所有条件均为可选）：		http://localhost:8080/getCommodityInPage?pageNum=${页码}&priceOrder=${按价格升序或降序输入asc或desc}&priceFrom=${价格最小值}&priceTo=${价格最大值}

增加新商品：			http://localhost:8080/addCommodity?name=${新商品名称}&price=${新商品价格}

编辑商品：			http://localhost:8080/updateCommodity?id=${商品的id}&name=${商品的名称}&price=${商品的价格}

查看购物车中的商品：	http://localhost:8080/getCommodityByCart?id=${购物车的id}

对购物车进行结账：	http://localhost:8080/dealCart?id=${购物车的id}

取消购物车的结账：	http://localhost:8080/undealCart?id=${购物车的id}

查看用户：			http://localhost:8080/getPerson?id=${用户的id}

查看会员级别：		http://localhost:8080/getRole?id=${会员级别的id}

编辑会员级别：		http://localhost:8080/updateRoleDirectly?id=${会员级别的id}&name=${会员级别的名称}

查询匹配两个级别值（如gold、silver、normal）的会员级别（使用或逻辑特性）：
http://localhost:8080/getRoleValue1OrValue2?value1=${级别值1}&value2=${级别值2}

查询会员级别值（如gold、silver、normal）或用户名称匹配给定值的用户（使用外键或逻辑特性）
http://localhost:8080/getRoleValueOrPersonName?value=${级别值}&name=${用户名称}

向当前购物车加入/删除商品（amount为负数时为删除），并自动处理最终结果（用来展示双向相关算法下处理业务模型的优雅）
http://localhost:8080/addCommodityToCart?cartId=${购物车的id}&commId=${商品的id}&amount=${购买数量}

按两个用户id查询购买的商品详情
http://localhost:8080/getCartCommodityByPersonId1OrId2?id1=${用户id1}&id2=${用户id2}

以上方法的实现代码可见： https://github.com/limeng32/flying-demo-use-springboot/blob/master/src/main/java/indi/demo/flying/controller/MainController.java 

从 `use-flying-0.9.3` 分支开始我们采用双向相关的方式构建 pojo，以求打造一个真实可用的电商前台，关于双向相关的详细信息请见： https://my.oschina.net/u/2280950/blog/1580056

最后，flying 项目介绍请见 [flying-doc.limeng32.com](http://flying-doc.limeng32.com) ，我们为开发最好的 mybatis 插件而努力。