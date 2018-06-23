package com.crypt.Accounts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6332014426602382753L;
	
	//Class variables
	//private List<CryptData> depositedData;
	private List<String> transactionHistory = new ArrayList<String>();
	private ArrayList<String> items = new ArrayList<>();
	private Object uploadedData;
	private String filepath;
	private String fileName;
	private String username;
	private String password;
	private boolean approved = false;
	private byte role = 0;
	private int defaultSeed;
	Random generator = new Random();
	
	//Constructors
	public Account() {
		super();
	}
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Account(String usern, String pass, byte rol) {
		super();
		this.username = usern;
		this.password = pass;
		this.role = rol;
		
		if(role == 1) approved = true; else approved = false;
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
	
	//Methods
	public void deposit(/*byte a, byte b, byte c*/) {
		//TODO: Create a new folder for specific user repo
		//TODO: Encrypt based on encryption specified by user
		
		items.add(fileName);
		transactionHistory.add("Deposited " + fileName);
	}

	public void withdraw(byte index) {
		//TODO: Decrypt item
		if(items.isEmpty()) { System.out.println("No items currently stored"); return; }
		transactionHistory.add("Withdrew " + items.get(index));
		items.remove(index);
		reportBalance();
	}
	
	public void reportBalance() { System.out.println("You currently have " + items.size() + "items stored."); }
	//Getters / Setters
	public ArrayList<String> getRepoContents(){ return items; }
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
		setFileName(filepath);
	}
	private void setFileName(String filepath) {
		String s[] = filepath.split("/");
		this.fileName = s[s.length - 1];
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
