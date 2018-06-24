package com.crypt.Accounts;

import java.util.List;
import java.util.Random;

public class Account {
	private List<CryptData> depositedData;
	private List<String> transactionHistory;
	private Object uploadedData;
	private String filepath;
	private String fileName;
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
