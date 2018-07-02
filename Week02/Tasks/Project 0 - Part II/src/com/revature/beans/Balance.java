package com.revature.beans;

import java.math.BigDecimal;

public class Balance {
	private int id;
	private BigDecimal balance;
	private int cardNumber;
	private int userId;
	
	public Balance(int id, BigDecimal balance, int cardNumber, int userId) {
		super();
		this.id = id;
		this.balance = balance;
		this.cardNumber = cardNumber;
		this.userId = userId;
	}
	
	public Balance(BigDecimal balance, int cardNumber, int userId) {
		super();
		this.balance = balance;
		this.cardNumber = cardNumber;
		this.userId = userId;
	}
	
	public Balance(BigDecimal balance) {
		super();
		this.balance = balance;
	}

	public Balance() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
