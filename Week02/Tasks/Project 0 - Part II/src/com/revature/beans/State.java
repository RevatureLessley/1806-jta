package com.revature.beans;

public class State {
	private int id;
	private String state;
	
	public State(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}

	public State() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
