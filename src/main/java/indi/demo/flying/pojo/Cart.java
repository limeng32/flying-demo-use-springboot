package indi.demo.flying.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.annotation.JSONField;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table(name = "CART")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * deal success?
	 * 
	 */
	@Column(name = "DEAL")
	private Boolean deal;

	/**
	 * 交易时间
	 * 
	 */
	@Column(name = "DEAL_TIME")
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date dealTime;

	/**
	 * 跨数据源关联 person
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "PERSON_ID", jdbcType = JdbcType.VARCHAR, dbCrossedAssociationUniqueKey = "_ID")
	private Person person;

	private java.util.Collection<CartCommodity> cartCommodity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getDeal() {
		return deal;
	}

	public void setDeal(Boolean deal) {
		this.deal = deal;
	}

	public java.util.Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(java.util.Date dealTime) {
		this.dealTime = dealTime;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person newPerson) {
		if (this.person == null || !this.person.equals(newPerson)) {
			if (this.person != null) {
				Person oldPerson = this.person;
				this.person = null;
				oldPerson.removeCart(this);
			}
			if (newPerson != null) {
				this.person = newPerson;
				this.person.addCart(this);
			}
		}
	}

	public java.util.Collection<CartCommodity> getCartCommodity() {
		if (cartCommodity == null) {
			cartCommodity = new java.util.LinkedHashSet<CartCommodity>();
		}
		return cartCommodity;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<CartCommodity> getIteratorCartCommodity() {
		if (cartCommodity == null) {
			cartCommodity = new java.util.LinkedHashSet<CartCommodity>();
		}
		return cartCommodity.iterator();
	}

	public void setCartCommodity(java.util.Collection<CartCommodity> newCartCommodity) {
		removeAllCartCommodity();
		for (java.util.Iterator<CartCommodity> iter = newCartCommodity.iterator(); iter.hasNext();) {
			addCartCommodity((CartCommodity) iter.next());
		}
	}

	public void addCartCommodity(CartCommodity newCartCommodity) {
		if (newCartCommodity == null) {
			return;
		}
		if (this.cartCommodity == null) {
			this.cartCommodity = new java.util.LinkedHashSet<CartCommodity>();
		}
		if (!this.cartCommodity.contains(newCartCommodity)) {
			this.cartCommodity.add(newCartCommodity);
			newCartCommodity.setCart(this);
		} else {
			for (CartCommodity temp : this.cartCommodity) {
				if (newCartCommodity.equals(temp)) {
					if (temp != newCartCommodity) {
						removeCartCommodity(temp);
						this.cartCommodity.add(newCartCommodity);
						newCartCommodity.setCart(this);
					}
					break;
				}
			}
		}
	}

	public void removeCartCommodity(CartCommodity oldCartCommodity) {
		if (oldCartCommodity == null) {
			return;
		}
		if (this.cartCommodity != null) {
			if (this.cartCommodity.contains(oldCartCommodity)) {
				for (CartCommodity temp : this.cartCommodity) {
					if (oldCartCommodity.equals(temp)) {
						if (temp != oldCartCommodity) {
							temp.setCart(null);
						}
						break;
					}
				}
				this.cartCommodity.remove(oldCartCommodity);
				oldCartCommodity.setCart(null);
			}
		}
	}

	public void removeAllCartCommodity() {
		if (cartCommodity != null) {
			CartCommodity oldCartCommodity;
			for (java.util.Iterator<CartCommodity> iter = getIteratorCartCommodity(); iter.hasNext();) {
				oldCartCommodity = (CartCommodity) iter.next();
				iter.remove();
				oldCartCommodity.setCart(null);
			}
			cartCommodity.clear();
		}
	}
}
