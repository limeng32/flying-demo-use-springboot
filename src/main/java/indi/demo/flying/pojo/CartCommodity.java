package indi.demo.flying.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table(name = "CART_COMMODITY")
public class CartCommodity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@Id
	@Column(name = "ID")
	private Long id;

	/**
	 * 对应的购物车
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "CART_ID", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "ID")
	private Cart cart;

	/**
	 * associate commodity
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "COMM_ID", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "ID")
	private Commodity commodity;

	/**
	 * commodity amount
	 * 
	 */
	@Column(name = "AMOUNT")
	private Integer amount;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart newCart) {
		if (this.cart == null || !this.cart.equals(newCart)) {
			if (this.cart != null) {
				Cart oldCart = this.cart;
				this.cart = null;
				oldCart.removeCartCommodity(this);
			}
			if (newCart != null) {
				this.cart = newCart;
				this.cart.addCartCommodity(this);
			}
		}
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity newCommodity) {
		if (this.commodity == null || !this.commodity.equals(newCommodity)) {
			if (this.commodity != null) {
				Commodity oldCommodity = this.commodity;
				this.commodity = null;
				oldCommodity.removeCartCommodity(this);
			}
			if (newCommodity != null) {
				this.commodity = newCommodity;
				this.commodity.addCartCommodity(this);
			}
		}
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
