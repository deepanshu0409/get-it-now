package com.getitnow.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cart_Product_Table")
public class CartProductEntity {
	@Id
	private Integer cartProductId;
	
	private Integer cartId;
	private Integer productId;
	private Integer quantity;
	
	public Integer getCartProductId() {
		return cartProductId;
	}
	public void setCartProductId(Integer cartProductId) {
		this.cartProductId = cartProductId;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
