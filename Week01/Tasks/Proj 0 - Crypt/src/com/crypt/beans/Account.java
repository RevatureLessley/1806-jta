package com.crypt.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.crypt.Services.DataFileService;
import com.crypt.filemanager.FileManager;
import com.crypt.util.CloseStreams;

public class Account implements Serializable {
	private static final long serialVersionUID = 6332014426602382753L;

	//Class variables
	private Integer id;
	private String username;
	private Integer approved = 0;
	private Integer role = 0;
	private Integer defaultSeed;
	private Random generator = new Random();

	private List<Note> transactionHistory = new ArrayList<>();
	private List<DataFile> items = new ArrayList<>();

	//Constructors
	public Account() {
		super();
	}
	public Account(String username) {
		super();
		this.username = username;
	}

	/**
	 * sets user name and role. If role is administrator then it auto-approves the
	 * account
	 * @param usern
	 * @param rol
	 */
	public Account(String usern, Integer rol) {
		super();
		this.username = usern;
		this.role = rol;

		if(role == 1) approved = 1;
	}

	public Account(int id, String username, int approved, int role, int defaultSeed) {
		super();
		this.id = id;
		this.username = username;
		this.approved = approved;
		this.role = role;
		this.defaultSeed = defaultSeed;
	}

	/**
	 * allows for complete instantiation of an account
	 * @param transactionHistory
	 * @param uploadedData
	 * @param filepath
	 * @param fileName
	 * @param username
	 * @param password
	 * @param role
	 * @param defaultSeed
	 * @param generator
	 */
	public Account(int id, String username, int approved, int role, int defaultSeed, List<Note> transactionHistory, List<DataFile> files) {
		super();
		this.id = id;
		this.username = username;
		this.approved = approved;
		this.role = role;
		this.defaultSeed = defaultSeed;
		this.transactionHistory = transactionHistory;
		this.items = files;
	}

	//Methods

	/**
	 * Deposits and *eventually* encrypts source data
	 */
	public void deposit(String pathname/*byte a, byte b, byte c*/) {
		//TODO: Create a new folder for specific user repo
		//TODO: Encrypt based on encryption specified by user
		byte[] fileData = null;
		File filePath = new File(pathname);
		InputStream is = null;

		try {
			is = new FileInputStream(filePath);
			fileData = new byte[is.available()];
			for(int i = 0; i < is.available(); i++) 
			{ fileData[i] = (byte)is.read(); }

			items.add(
					new DataFile(
							this.id,
							filePath,
							filePath.getName(),
							fileData)
					);
			System.out.println(items.toString());
			DataFileService.insertNewFile(items.get(items.size() - 1));
			
		}catch(IOException e) { e.printStackTrace(); 
		}finally{ CloseStreams.close(is); }

		System.out.println("Deposited " + filePath.getName());
		transactionHistory.add(new Note(transactionHistory.size(), this.id, "Deposited " + filePath.getName() ));
		refreshFiles();
		reportBalance();
	}

	/**
	 * allows withdrawal and *eventual* decryption of data
	 * @param index
	 */
	public void withdraw(byte index) {
		//TODO: Decrypt item
		if(items.isEmpty()) { System.out.println("No items currently stored"); return; }
		
		FileManager fm = new FileManager(items.get(index-1).getFilepath().getAbsolutePath());
		fm.writeObject(items.get(index-1).getUploadedData());
		
		transactionHistory.add(
				new Note(
						transactionHistory.size(),
						this.id,
						"Withdrew " + items.get(index-1).getFileName() 
						)
				);
		items.remove(index-1);

		reportBalance();
	}
	
	private void refreshFiles() {
		Account a = new Account();
		a.setId(this.id);
		items = DataFileService.selectAllFiles(a);
	}
	/**
	 * reports current balance.
	 */
	public void reportBalance() { System.out.println("You currently have " + items.size() + "items stored."); }

	//Getters / Setters

	public Integer getId() {
		return id;
	}
	public List<Note> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<Note> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	public List<DataFile> getItems() {
		return items;
	}
	public void setItems(List<DataFile> items) {
		this.items = items;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer isApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public int getDefaultSeed() {
		return defaultSeed;
	}
	public void setDefaultSeed(int defaultSeed) {
		this.defaultSeed = defaultSeed;
	}
	public void generateDefaultSeed() { defaultSeed = (generator.nextInt((10000000) + 10000000) * 2)/4; }



}
