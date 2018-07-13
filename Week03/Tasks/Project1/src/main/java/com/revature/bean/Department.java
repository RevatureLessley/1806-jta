package com.revature.bean;

public class Department {
	private Integer Id;
	private String name;

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

	public Department(Integer id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [Id=" + Id + ", name=" + name + "]";
	}

}
