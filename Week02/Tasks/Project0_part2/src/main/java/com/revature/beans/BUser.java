package com.revature.beans;

public class BUser {
	private Integer id;
	private Integer type;
	private String name;
	private String password;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BUser(Integer id, Integer type, String name, String password) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", type=" + type + ", name=" + name + ", password=" + password + "]";
	}

	public boolean isAdmin() {
		return (type==1);
	}

}
