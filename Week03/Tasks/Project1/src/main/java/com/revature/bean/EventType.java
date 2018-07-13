package com.revature.bean;

public class EventType {
	private Integer id;
	private String name;
	private Integer percent;

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

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public EventType(Integer id, String name, Integer percent) {
		super();
		this.id = id;
		this.name = name;
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "EventType [id=" + id + ", name=" + name + ", percent=" + percent + "]";
	}

}
