package com.getitnow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Store")
@GenericGenerator(name = "storeIdGenerator" , strategy = "sequence")
public class StoreEntity {
	@Id
	@GeneratedValue(generator = "storeIdGenerator")
	private Integer storeId;
	private String storeName;
	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	private String street;
	private String city;
	private String state;
	private Integer pincode;
	private Double storeRating;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Store_Product_table",
    	joinColumns = @JoinColumn(name = "storeId", referencedColumnName = "storeId"),      
    	inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"))
	private List<ProductEntity> products;
	
	public Integer getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Integer getPincode() {
		return pincode;
	}
	
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
	public Double getStoreRating() {
		return storeRating;
	}
	
	public void setStoreRating(Double storeRating) {
		this.storeRating = storeRating;
	}

}
