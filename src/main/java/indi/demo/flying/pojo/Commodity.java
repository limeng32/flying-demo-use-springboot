package indi.demo.flying.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

@Table(name = "COMMODITY")
public class Commodity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 商品名称
	 * 
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 价格，以分为单位
	 * 
	 */
	@Column(name = "PRICE")
	private Integer price;

	private java.util.Collection<CartCommodity> cartCommodity;

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPriceStr() {
		if (price != null) {
			return "RMB " + price / 100 + "." + price % 100 + " YUAN";
		} else {
			return null;
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
			newCartCommodity.setCommodity(this);
		} else {
			for (CartCommodity temp : this.cartCommodity) {
				if (newCartCommodity.equals(temp)) {
					if (temp != newCartCommodity) {
						removeCartCommodity(temp);
						this.cartCommodity.add(newCartCommodity);
						newCartCommodity.setCommodity(this);
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
							temp.setCommodity(null);
						}
						break;
					}
				}
				this.cartCommodity.remove(oldCartCommodity);
				oldCartCommodity.setCommodity(null);
			}
		}
	}

	public void removeAllCartCommodity() {
		if (cartCommodity != null) {
			CartCommodity oldCartCommodity;
			for (java.util.Iterator<CartCommodity> iter = getIteratorCartCommodity(); iter.hasNext();) {
				oldCartCommodity = (CartCommodity) iter.next();
				iter.remove();
				oldCartCommodity.setCommodity(null);
			}
			cartCommodity.clear();
		}
	}
}
