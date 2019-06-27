package com.getitnow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Cart")
@GenericGenerator(name="pkgen",strategy="increment")
public class CartEntity {
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer cartId;
	private Integer customerId;
	private Integer productId;
	private Integer quantity;
	private String productSize;
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Cart_Product_Table",
		joinColumns = @JoinColumn(name = "cartId",referencedColumnName = "cartId"),
		inverseJoinColumns = @JoinColumn(name = "productId",referencedColumnName = "productId"))
	private List<Product> products;*/

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public String getSize() {
		return productSize;
	}

	public void setSize(String size) {
		this.productSize = size;
	}

	/*public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}*/
}
