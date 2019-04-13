package indi.demo.flying.mapper;

import java.util.Collection;

import indi.demo.flying.pojo.Cart;
import indi.demo.flying.pojo.CartCommodity;
import indi.demo.flying.pojo.Commodity;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Cart.class, Commodity.class }, TriggerClass = { CartCommodity.class })
public interface CartCommodityMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public CartCommodity selectForAssociation(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public CartCommodity mySelect(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<CartCommodity> mySelectAll(CartCommodity t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public CartCommodity mySelectOne(CartCommodity t);

	public void myInsert(CartCommodity t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdate(CartCommodity t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdatePersistent(CartCommodity t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myDelete(CartCommodity t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int myCount(CartCommodity t);

	public void loadCommodity(Commodity commodity, CartCommodity cartCommodity);

	public void loadCart(Cart cart, CartCommodity cartCommodity);
}
