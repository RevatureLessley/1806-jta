package com.revature.beans;

public class Npc {
	private Integer Id;
	private String name;
	private String job;
	private Integer currency;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Npc(Integer id, String name, String job, Integer currency) {
		super();
		Id = id;
		this.name = name;
		this.job = job;
		this.currency = currency;
	}
	public Npc() {
		super();
		// REQUIRED
	}
	@Override
	public String toString() {
		return "Npc [Id=" + Id + ", name=" + name + ", job=" + job + ", currency=" + currency + "]";
	}
	
	
}
