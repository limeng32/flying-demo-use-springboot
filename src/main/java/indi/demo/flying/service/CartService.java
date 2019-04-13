package indi.demo.flying.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.CartMapper;
import indi.demo.flying.pojo.Cart;
import indi.demo.flying.pojo.Person;

@Service
public class CartService implements CartMapper {

	@Autowired
	private CartMapper mapper;

	@Override
	public Cart selectForAssociation(Object id) {
		return mapper.selectForAssociation(id);
	}

	@Override
	public Cart mySelect(Object id) {
		return mapper.mySelect(id);
	}

	@Override
	public Collection<Cart> mySelectAll(Cart t) {
		return mapper.mySelectAll(t);
	}

	@Override
	public Cart mySelectOne(Cart t) {
		return mapper.mySelectOne(t);
	}

	@Override
	public void myInsert(Cart t) {
		mapper.myInsert(t);
	}

	@Override
	public int myUpdate(Cart t) {
		return mapper.myUpdate(t);
	}

	@Override
	public int myUpdatePersistent(Cart t) {
		return mapper.myUpdatePersistent(t);
	}

	@Override
	public int myDelete(Cart t) {
		return mapper.myDelete(t);
	}

	@Override
	public int myCount(Cart t) {
		return mapper.myCount(t);
	}

	@Override
	public void loadPerson(Person person, Cart cart) {
		person.removeAllCart();
		cart.setPerson(person);
		person.setCart(mapper.mySelectAll(cart));
	}

}
