package com.revature.bean;

public class GradeScale {

	private Integer id;
	private String name;

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

	@Override
	public String toString() {
		return "GradeScale [id=" + id + ", name=" + name + "]";
	}

	public GradeScale(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
