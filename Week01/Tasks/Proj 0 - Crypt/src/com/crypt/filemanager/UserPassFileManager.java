package com.crypt.filemanager;

import com.crypt.Accounts.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.*;

public class UserPassFileManager extends FileManager<HashMap<String, Account>> {

	public UserPassFileManager(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeObject(HashMap<String, Account> t) {
		//TODO: Make an encryptor class to handle password encryption
		//Cipher c = Cipher.getInstance("AES");
		super.writeObject(t);
	}

	@Override
	public HashMap<String, Account> readObject() {
		// TODO Make a Decryptor class to decrypt password.
		return super.readObject();
	}

	public HashMap<String, String> fillUserPassInfo(HashMap<String, Account> accounts) {
		HashMap<String,String> userPassInfo = new HashMap<>();
		userPassInfo.put("", "");
		if(accounts.isEmpty())return userPassInfo;
		for(Account a : accounts.values()) { userPassInfo.put(a.getUsername(), a.getPassword()); }
		return userPassInfo;
	}
	
	

}
