package com.getitnow.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Card")
public class CardEntity {
	@Id
	private Long cardNumber;
	
	private String cardHolderName;
	private Integer cvv;
	private Integer expiryDate_MONTH;
	private Integer expiryDate_YEAR;
	private String transactionPassword;
	
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public Integer getExpiryDate_MONTH() {
		return expiryDate_MONTH;
	}
	public void setExpiryDate_MONTH(Integer expiryDate_MONTH) {
		this.expiryDate_MONTH = expiryDate_MONTH;
	}
	public Integer getExpiryDate_YEAR() {
		return expiryDate_YEAR;
	}
	public void setExpiryDate_YEAR(Integer expiryDate_YEAR) {
		this.expiryDate_YEAR = expiryDate_YEAR;
	}
	public String getTransactionPassword() {
		return transactionPassword;
	}
	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}
}
