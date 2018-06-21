package com.crypt.filemanager;

import com.crypt.Accounts.Account;

import java.util.List;

import javax.crypto.*;

public class UserPassFileManager extends FileManager<List<Account>> {

	public UserPassFileManager(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeObject(List<Account> t) {
		//TODO: Make an encryptor class to handle password encryption
		//Cipher c = Cipher.getInstance("AES");
		super.writeObject(t);
	}

	@Override
	public List<Account> readObject() {
		// TODO Make a Decryptor class to decrypt password.
		return super.readObject();
	}
	
	

}
