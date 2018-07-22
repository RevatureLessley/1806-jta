package com.revature.bean;

public class GradeValue {
	private Integer id;
	private Integer scale;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GradeValue [id=" + id + ", scale=" + scale + ", name=" + name + "]";
	}

	public GradeValue(Integer id, Integer scale, String name) {
		super();
		this.id = id;
		this.scale = scale;
		this.name = name;
	}

}
