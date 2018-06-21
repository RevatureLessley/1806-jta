package com.crypt.Accounts;

import java.util.List;
import java.util.Random;

public class Account {
	//private List<CryptData> depositedData;
	private List<String> transactionHistory;
	private Object uploadedData;
	private String filepath;
	private String fileName;
	private String username;
	private String password;
	
	
	public Account() {
		super();
	}
	
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Account(List<String> transactionHistory, Object uploadedData, String filepath,
			String fileName, String username, String password, byte role, int defaultSeed, Random generator) {
		super();
		//this.depositedData = depositedData;
		this.transactionHistory = transactionHistory;
		this.uploadedData = uploadedData;
		this.filepath = filepath;
		this.fileName = fileName;
		this.username = username;
		this.password = password;
		this.role = role;
		this.defaultSeed = defaultSeed;
		this.generator = generator;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private byte role = 0;
	private int defaultSeed;
	Random generator = new Random();
	
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public byte getRole() {
		return role;
	}
	public void setRole(byte role) {
		this.role = role;
	}
	public int getDefaultSeed() {
		return defaultSeed;
	}
	public void setDefaultSeed(int defaultSeed) {
		this.defaultSeed = defaultSeed;
	}
	public void generateDefaultSeed() { defaultSeed = (generator.nextInt(10000000) + 10000000 * 2)/4; }
	
	
	
}
