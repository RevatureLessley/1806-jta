package com.revature.beans;

public class BAccountType {
	private Integer id;
	private String name;
	private Double rate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public BAccountType(Integer id, String name, Double rate) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "AccountType [id=" + id + ", name=" + name + ", rate=" + rate + "]";
	}

}
