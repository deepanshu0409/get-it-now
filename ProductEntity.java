package com.getitnow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//import com.getitnow.model.Store;

@Entity
@Table(name = "Products")
@GenericGenerator(name= "pIdGenerator", strategy = "increment")
public class ProductEntity {
	@Id
	@GeneratedValue(generator = "pIdGenerator")
	private Integer productId;
	private String productName;
	private Double cost;
	private Integer quantity;
	private String productGenderType;
	private String productCategory;
	private String brand;
	private String color;
	private Double discount;
	private String description;
	private String productImageUrl;
	private String productSize;
/*	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customerLoan",
    joinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"),      
    inverseJoinColumns = @JoinColumn(name = "storeId", referencedColumnName = "storeId"))
	private List<Store> store;*/
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Double getCost() {
		return cost;
	}
	
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getProductGenderType() {
		return productGenderType;
	}
	
	public void setProductGenderType(String productGenderType) {
		this.productGenderType = productGenderType;
	}
	
	public String getProductCategory() {
		return productCategory;
	}
	
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getProductImageUrl() {
		return productImageUrl;
	}
	
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String size) {
		this.productSize = size;
	}
	
/*	public List<Store> getStore() {
		return store;
	}

	public void setStore(List<Store> store) {
		this.store = store;
	}*/

}
