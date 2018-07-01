package com.revature.beans;

public class BAccount {
	private Integer id;
	private Integer type;
	private Double balance;
	private Integer validated;
	private Integer user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getValidated() {
		return validated;
	}

	public void setValidated(Integer validated) {
		this.validated = validated;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public BAccount(Integer id, Integer type, Double balance, Integer validated, Integer user) {
		super();
		this.id = id;
		this.type = type;
		this.balance = balance;
		this.validated = validated;
		this.user = user;
	}

	public BAccount(int type, double balance, Integer validated, Integer user) {
		this.type = type;
		this.balance = balance;
		this.validated = validated ;
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserType [id=" + id + ", type=" + type + ", balance=" + balance + ", validated=" + validated + ", user="
				+ user + "]";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "Account " + id;
	}

}
