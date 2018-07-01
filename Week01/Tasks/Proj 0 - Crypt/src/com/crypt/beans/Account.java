package com.crypt.beans;

public class Account {
	private int id;
	private String username;
	private int approved;
	private int role;
	private int defaultSeed;
	
	public Account() {
		super();
	}

	public Account(int id, String username, int approved, int role, int defaultSeed) {
		super();
		this.id = id;
		this.username = username;
		this.approved = approved;
		this.role = role;
		this.defaultSeed = defaultSeed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getDefaultSeed() {
		return defaultSeed;
	}

	public void setDefaultSeed(int defaultSeed) {
		this.defaultSeed = defaultSeed;
	}
}
