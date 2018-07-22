package com.revature.bean;

public class GradeScale {

	private Integer id;
	private String name;
	private Integer presentation;

	public Integer getPresentation() {
		return presentation;
	}

	public void setPresentation(Integer presentation) {
		this.presentation = presentation;
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

	@Override
	public String toString() {
		return "GradeScale [id=" + id + ", name=" + name + ", presentation=" + presentation + "]";
	}

	public GradeScale(Integer id, String name, Integer presentation) {
		super();
		this.id = id;
		this.name = name;
		this.presentation = presentation;
	}

}
