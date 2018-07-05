package com.revature.beans;

public class Npc {
	private Integer id;
	private String name;
	private Integer lvl;
	private Integer currency;
	private Integer jobClass;
	private String jobClassString;
	
	
	public Npc(String name, Integer lvl, Integer currency, Integer jobClass) {
		super();
		this.name = name;
		this.lvl = lvl;		
		this.currency = currency;
		this.jobClass = jobClass;
	}
	public Npc(Integer id, String name, Integer lvl, Integer currency, Integer jobClass) {
		super();
		this.id = id;
		this.name = name;
		this.lvl = lvl;		this.currency = currency;
		this.jobClass = jobClass;
	}
	
	public Npc(Integer id, String name, Integer lvl, Integer currency, Integer jobClass, String jobClassString) {
		super();
		this.id = id;
		this.name = name;
		this.lvl = lvl;
		this.currency = currency;
		this.jobClass = jobClass;
		this.jobClassString = jobClassString;
	}
	
	public Npc(){
		super();
	}

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

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getJobClass() {
		return jobClass;
	}

	public void setJobClass(Integer jobClass) {
		this.jobClass = jobClass;
	}
	
	

	public String getJobClassString() {
		return jobClassString;
	}

	public void setJobClassString(String jobClassString) {
		this.jobClassString = jobClassString;
	}

	@Override
	public String toString() {
		return "npc [id=" + id + ", name=" + name + ", lvl=" + lvl + ", currency=" + currency + ", jobClass=" + jobClass
				+ "]";
	}
	
	
}
