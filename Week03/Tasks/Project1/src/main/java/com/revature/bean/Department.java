package com.revature.bean;

public class Department {
	private Integer Id;
	private String name;
	private Integer headId;

	public Integer getHeadId() {
		return headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}

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

	public Department(Integer id, String name, Integer headId) {
		super();
		Id = id;
		this.name = name;
		this.headId = headId;
	}

	@Override
	public String toString() {
		return "Department [Id=" + Id + ", name=" + name + ", headId=" + headId + "]";
	}

}
