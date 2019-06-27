package com.getitnow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Customers")
@GenericGenerator(name="pkgen",strategy="increment")
public class CustomerEntity {
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer customerId;
	
	private String customerName;
	private String username;
	private String password;
	private String address;
	private String city;
	private Integer pincode;
	private Long mobileNo;
	private String emailId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Customer_Card_Table",
		joinColumns = @JoinColumn(name = "customerId", referencedColumnName = "customerId"),
		inverseJoinColumns = @JoinColumn(name = "cardNumber", referencedColumnName = "cardNumber", unique = true))
	private List<CardEntity> card;
	
	
	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartId",unique = true)
	private Cart cart;*/

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public List<CardEntity> getCard() {
		return card;
	}

	public void setCard(List<CardEntity> card) {
		this.card = card;
	}
	
	
/*	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}*/
	
	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
