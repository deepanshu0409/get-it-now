package com.getitnow.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "Orders")
@GenericGenerator(name="pkgen",strategy="increment")
public class OrderEntity {
	@Id
	@Column(length=6)
	@GeneratedValue(generator = "pkgen")
	private Integer sno;
	private String orderId;
	private Integer customerId;
	private Integer productId;
	private Integer quantity;
	@Column(name="orderDate")
	private Date date;
	
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

/*	@OneToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "Order_Cart_Table", joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "orderId"), inverseJoinColumns = @JoinColumn(name = "cartId", referencedColumnName = "cartId", unique = true))
    private List<CartEntity> cart;*/

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

/*	public List<CartEntity> getCart() {
		return cart;
	}

	public void setCart(List<CartEntity> cart) {
		this.cart = cart;
	}*/
}
