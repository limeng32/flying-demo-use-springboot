package indi.demo.flying.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import indi.demo.flying.condition.AccountCondition;
import indi.demo.flying.condition.CartCondition;
import indi.demo.flying.condition.CommodityCondition;
import indi.demo.flying.condition.RoleCondition;
import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojo.Cart;
import indi.demo.flying.pojo.CartCommodity;
import indi.demo.flying.pojo.Commodity;
import indi.demo.flying.pojo.Person;
import indi.demo.flying.pojo.Role;
import indi.demo.flying.pojo.RoleEnum;
import indi.demo.flying.service.AccountService;
import indi.demo.flying.service.CartCommodityService;
import indi.demo.flying.service.CartService;
import indi.demo.flying.service.CommodityService;
import indi.demo.flying.service.PersonService;
import indi.demo.flying.service.RoleService;
import indi.mybatis.flying.models.Conditionable;
import indi.mybatis.flying.pagination.Order;
import indi.mybatis.flying.pagination.Page;
import indi.mybatis.flying.pagination.PageParam;
import indi.mybatis.flying.pagination.SortParam;

@RestController
@EnableAutoConfiguration
@ComponentScan({ "indi.demo.flying.config", "indi.demo.flying.service" })
public class MainController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartCommodityService cartCommodityService;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	public static final String PRODUCE = "application/json;charset=UTF-8";

	@RequestMapping(method = { RequestMethod.GET }, value = "/getAccount", produces = { PRODUCE })
	@ResponseBody
	public Account_ account2(@RequestParam("id") Integer id) {
		Account_ e = accountService.select(id);
		return e;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getAccountById1OrId2", produces = { PRODUCE })
	@ResponseBody
	public Collection<Account_> account2(@RequestParam("id1") Integer id1, @RequestParam("id2") Integer id2) {
		AccountCondition ac = new AccountCondition();
		ac.setIdOr(id1, id2);
		Collection<Account_> c = accountService.selectAll(ac);
		return c;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/error/{errorName}", produces = { PRODUCE })
	public String error(HttpServletRequest request, @PathVariable("errorName") String errorName) {
		return "error:" + errorName;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getCart", produces = { PRODUCE })
	public Cart getCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Cart cart = cartService.mySelect(id);
		return cart;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getCommodity", produces = { PRODUCE })
	public Commodity getCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Commodity commodity = commodityService.mySelect(id);
		return commodity;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getCommodityInPage", produces = { PRODUCE })
	public Object getCommodityInPage(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "priceOrder", required = false) String priceOrder,
			@RequestParam(value = "priceFrom", required = false) Integer priceFrom,
			@RequestParam(value = "priceTo", required = false) Integer priceTo) {
		CommodityCondition commodityCondition = new CommodityCondition();
		if (priceFrom != null) {
			commodityCondition.setPriceFrom(priceFrom);
		}
		if (priceTo != null) {
			commodityCondition.setPriceTo(priceTo);
		}
		Conditionable.Sequence sequence = null;
		try {
			sequence = Conditionable.Sequence.valueOf(priceOrder);
		} catch (Exception e) {
		}
		if (sequence != null) {
			commodityCondition.setSorter(new SortParam(new Order("PRICE", sequence)));
		}
		if (pageNum != null && pageNum >= 0) {
			commodityCondition.setLimiter(new PageParam(pageNum, 10));
		}
		Collection<Commodity> commodityC = commodityService.mySelectAll(commodityCondition);
		if (commodityCondition.getLimiter() == null) {
			return commodityC;
		} else {
			Page<Commodity> page = new Page<>(commodityC, commodityCondition.getLimiter());
			return page;
		}
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/addCommodity", produces = { PRODUCE })
	public Commodity AddCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "name", required = false) String name) {
		Commodity commodity = new Commodity();
		commodity.setName(name);
		commodity.setPrice(price);
		commodityService.myInsert(commodity);
		return commodity;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/updateCommodity", produces = { PRODUCE })
	public Commodity updateCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "name", required = false) String name) {
		Commodity commodity = commodityService.mySelect(id);
		if (commodity != null) {
			if (name != null) {
				commodity.setName(name);
			}
			if (price != null) {
				commodity.setPrice(price);
			}
			commodityService.myUpdate(commodity);
		}
		return commodity;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getCommodityByCart", produces = { PRODUCE })
	public Collection<CartCommodity> getCommodityByCart(HttpServletRequest request, HttpServletResponse response,
			ModelMap mm, @RequestParam(value = "id", required = false) String cartId) {
		Cart cart = cartService.mySelect(cartId);
		cartCommodityService.loadCart(cart, new CartCommodity());
		return cart.getCartCommodity();
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/dealCart", produces = { PRODUCE })
	public Collection<CartCommodity> dealCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String cartId) {
		Cart cart = cartService.mySelect(cartId);
		if (cart != null) {
			cart.setDeal(true);
			cart.setDealTime(Calendar.getInstance().getTime());
			cartService.myUpdate(cart);

			CartCommodity cartCommodityCondition = new CartCommodity();
			cartCommodityCondition.setCart(cart);
			Collection<CartCommodity> cartCommodityC = cartCommodityService.mySelectAll(cartCommodityCondition);
			return cartCommodityC;
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/undealCart", produces = { PRODUCE })
	public Collection<CartCommodity> unDealCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String cartId) {
		Cart cart = cartService.mySelect(cartId);
		if (cart != null) {
			cart.setDeal(false);
			cart.setDealTime(null);
			cartService.myUpdatePersistent(cart);

			CartCommodity cartCommodityCondition = new CartCommodity();
			cartCommodityCondition.setCart(cart);
			Collection<CartCommodity> cartCommodityC = cartCommodityService.mySelectAll(cartCommodityCondition);
			return cartCommodityC;
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getPerson", produces = { PRODUCE })
	public Person getPerson(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Person person = personService.mySelect(id);
		return person;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getCartCommodity", produces = { PRODUCE })
	public CartCommodity getCartCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		CartCommodity c = cartCommodityService.mySelect(id);
		return c;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getRole", produces = { PRODUCE })
	public Role getRole(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Role role = roleService.mySelect(id);
		return role;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/updateRoleDirectly", produces = { PRODUCE })
	public Role updateRoleDirectly(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name) {
		if (id != null) {
			Map<String, Object> m = new HashMap<>();
			m.put("id", id);
			m.put("name", name);
			roleService.updateDirect(m);
		}
		Role role = roleService.mySelect(id);
		return role;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/updateRoleDirectlyWithoutCache", produces = { PRODUCE })
	public Role updateRoleDirectlyWithoutCache(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name) {
		if (id != null) {
			Map<String, Object> m = new HashMap<>();
			m.put("id", id);
			m.put("name", name);
			roleService.updateDirectWithoutCache(m);
		}
		Role role = roleService.mySelect(id);
		return role;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getRoleValue1OrValue2", produces = { PRODUCE })
	public Collection<Role> getRoleValue1OrValue2(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "value1") RoleEnum value1, @RequestParam(value = "value2") RoleEnum value2) {
		RoleCondition roleCondition = new RoleCondition();
		roleCondition.setValue1OrValue2(value1, value2);
		Collection<Role> roleC = roleService.mySelectAll(roleCondition);
		return roleC;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getRoleValueOrPersonName", produces = { PRODUCE })
	public Collection<Person> getRoleValueOrPersonName(HttpServletRequest request, HttpServletResponse response,
			ModelMap mm, @RequestParam(value = "value") RoleEnum value, @RequestParam(value = "name") String name) {
		RoleCondition roleCondition = new RoleCondition();
		roleCondition.setValueOrPersonName(value, name);
		Person p = new Person();
		p.setRole(roleCondition);
		Collection<Person> personC = personService.mySelectAll(p);
		return personC;
	}

	/* 向当前购物车加入/删除商品（amount为负数时为删除），会自动和购物车中的同种商品进行合并操作，若最终数量为负数则等于零 */
	/* 此用例用来展示双向相关算法下处理业务模型的优雅之处 */
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/addCommodityToCart", produces = { PRODUCE })
	public Collection<CartCommodity> addCommodityToCart(HttpServletRequest request, HttpServletResponse response,
			ModelMap mm, @RequestParam(value = "cartId") String cartId, @RequestParam(value = "commId") String commId,
			@RequestParam(value = "amount") Integer amount) {
		Cart cart = cartService.mySelect(cartId);
		if (cart == null) {
			/* 如果cart不存在则返回null */
			return null;
		}

		Commodity commodity = commodityService.mySelect(commId);
		if (commodity != null) {
			/* 先查询购物车中是否有同种商品 */
			CartCommodity cc = new CartCommodity();
			cc.setCart(cart);
			cc.setCommodity(commodity);

			CartCommodity cartCommodity = cartCommodityService.mySelectOne(cc);

			if (cartCommodity == null) {
				/* 如果购物车中没有此项商品，且数量大于0，则增加此项 */
				cartCommodity = new CartCommodity();
				cartCommodity.setCart(cart);
				cartCommodity.setCommodity(commodity);
				cartCommodity.setAmount(amount);
				if (cartCommodity.getAmount() > 0) {
					cartCommodityService.myInsert(cartCommodity);
				}
			} else {
				/* 如果购物车中有此项商品，如果最终数量大于0则更新此项，如果最终数量小于等于0则删除此项 */
				cartCommodity.setCart(cart);
				cartCommodity.setCommodity(commodity);
				cartCommodity.setAmount((cartCommodity.getAmount() == null ? 0 : cartCommodity.getAmount()) + amount);
				if (cartCommodity.getAmount() > 0) {
					cartCommodityService.myUpdate(cartCommodity);
				} else {
					cartCommodityService.myDelete(cartCommodity);
				}
			}
		}
		/* 最后重新查询购物车中的内容 */
		cartCommodityService.loadCart(cart, new CartCommodity());
		return cart.getCartCommodity();
	}

	/* 采用或逻辑的按人员查询商品详情，可选择两个人员 id */
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/getCartCommodityByPersonId1OrId2", produces = { PRODUCE })
	public Collection<Cart> getCartCommodityByPersonId1OrId2(HttpServletRequest request, HttpServletResponse response,
			ModelMap mm, @RequestParam(value = "id1") String id1, @RequestParam(value = "id2") String id2) {
		CartCondition cartCondition = new CartCondition();
		cartCondition.setPersonIdOr(id1, id2);
		Collection<Cart> cartC = cartService.mySelectAll(cartCondition);
		if (cartC != null) {
			for (Cart cart : cartC) {
				cartCommodityService.loadCart(cart, new CartCommodity());
			}
		}
		return cartC;
	}
}