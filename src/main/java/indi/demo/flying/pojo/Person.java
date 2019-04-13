package indi.demo.flying.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.annotation.JSONField;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table(name = "PERSON")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@Id
	@Column(name = "_ID")
	private String id;

	/**
	 * 姓名
	 * 
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 关联角色
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "ROLE_ID", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "ID")
	private Role role;

	private java.util.Collection<Cart> cart;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role newRole) {
		if (this.role == null || !this.role.equals(newRole)) {
			if (this.role != null) {
				Role oldRole = this.role;
				this.role = null;
				oldRole.removePerson(this);
			}
			if (newRole != null) {
				this.role = newRole;
				this.role.addPerson(this);
			}
		}
	}

	public java.util.Collection<Cart> getCart() {
		if (cart == null) {
			cart = new java.util.LinkedHashSet<Cart>();
		}
		return cart;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<Cart> getIteratorCart() {
		if (cart == null) {
			cart = new java.util.LinkedHashSet<Cart>();
		}
		return cart.iterator();
	}

	public void setCart(java.util.Collection<Cart> newCart) {
		removeAllCart();
		for (java.util.Iterator<Cart> iter = newCart.iterator(); iter.hasNext();) {
			addCart((Cart) iter.next());
		}
	}

	public void addCart(Cart newCart) {
		if (newCart == null) {
			return;
		}
		if (this.cart == null) {
			this.cart = new java.util.LinkedHashSet<Cart>();
		}
		if (!this.cart.contains(newCart)) {
			this.cart.add(newCart);
			newCart.setPerson(this);
		} else {
			for (Cart temp : this.cart) {
				if (newCart.equals(temp)) {
					if (temp != newCart) {
						removeCart(temp);
						this.cart.add(newCart);
						newCart.setPerson(this);
					}
					break;
				}
			}
		}
	}

	public void removeCart(Cart oldCart) {
		if (oldCart == null) {
			return;
		}
		if (this.cart != null) {
			if (this.cart.contains(oldCart)) {
				for (Cart temp : this.cart) {
					if (oldCart.equals(temp)) {
						if (temp != oldCart) {
							temp.setPerson(null);
						}
						break;
					}
				}
				this.cart.remove(oldCart);
				oldCart.setPerson(null);
			}
		}
	}

	public void removeAllCart() {
		if (cart != null) {
			Cart oldCart;
			for (java.util.Iterator<Cart> iter = getIteratorCart(); iter.hasNext();) {
				oldCart = (Cart) iter.next();
				iter.remove();
				oldCart.setPerson(null);
			}
			cart.clear();
		}
	}
}
