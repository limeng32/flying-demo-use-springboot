package indi.demo.flying.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.CartCommodityMapper;
import indi.demo.flying.pojo.Cart;
import indi.demo.flying.pojo.CartCommodity;
import indi.demo.flying.pojo.Commodity;

@Service
public class CartCommodityService implements CartCommodityMapper {

	@Autowired
	private CartCommodityMapper mapper;

	@Override
	public CartCommodity selectForAssociation(Object id) {
		return mapper.selectForAssociation(id);
	}

	@Override
	public CartCommodity mySelect(Object id) {
		return mapper.mySelect(id);
	}

	@Override
	public Collection<CartCommodity> mySelectAll(CartCommodity t) {
		return mapper.mySelectAll(t);
	}

	@Override
	public CartCommodity mySelectOne(CartCommodity t) {
		return mapper.mySelectOne(t);
	}

	@Override
	public void myInsert(CartCommodity t) {
		mapper.myInsert(t);
	}

	@Override
	public int myUpdate(CartCommodity t) {
		return mapper.myUpdate(t);
	}

	@Override
	public int myUpdatePersistent(CartCommodity t) {
		return mapper.myUpdatePersistent(t);
	}

	@Override
	public int myDelete(CartCommodity t) {
		return mapper.myDelete(t);
	}

	@Override
	public int myCount(CartCommodity t) {
		return mapper.myCount(t);
	}

	@Override
	public void loadCommodity(Commodity commodity, CartCommodity cartCommodity) {
		commodity.removeAllCartCommodity();
		cartCommodity.setCommodity(commodity);
		commodity.setCartCommodity(mapper.mySelectAll(cartCommodity));
	}

	@Override
	public void loadCart(Cart cart, CartCommodity cartCommodity) {
		cart.removeAllCartCommodity();
		cartCommodity.setCart(cart);
		cart.setCartCommodity(mapper.mySelectAll(cartCommodity));
	}

}
