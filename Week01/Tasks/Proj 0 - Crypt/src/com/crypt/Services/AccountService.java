package com.crypt.Services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crypt.beans.Account;
import com.crypt.beans.UserPass;
import com.crypt.dao.AccountDao;
import com.crypt.dao.AccountDaoImpl;
import com.crypt.dao.DataFileDao;
import com.crypt.dao.DataFileDaoImpl;
import com.crypt.dao.NoteDao;
import com.crypt.dao.NoteDaoImpl;

public class AccountService {
	public static Map<String, Account> selectAllAccounts() {
		Map<String,Account> accounts = new HashMap<>();
		List<Account> accountList = new ArrayList<>();
		try {
			AccountDao adi = new AccountDaoImpl();
			accountList = adi.selectAll();
			
			for(Account a : accountList) {
				DataFileDao dfd = new DataFileDaoImpl();
				NoteDao nd = new NoteDaoImpl();
				a.setTransactionHistory(nd.selectAll(a));
				a.setItems(dfd.selectAll(a));
				accounts.put(a.getUsername(), a);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println("ACCOUNT SQL RETRIEVAL ERROR");
			
		}
		
		return accounts;
	}
	
	public static List<Account> selectAccount(String sql){
		AccountDao ad = new AccountDaoImpl();
		try {
			return ad.selectConditional(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("ACCOUNT SQL RETRIEVAL FAILED");
		}
		return null;
	}
	public static Boolean newAccount(Account a, UserPass up){
		UserPassService.insertUserPass(up);
		AccountDao ad = new AccountDaoImpl();
		return ad.insertNewAccount(a);
	}
	
	public static Boolean approveAccount(Integer index) {
		AccountDao ad = new AccountDaoImpl();
		return ad.approveAccount(index);
	}
	
	public static void updateAccounts(List<Account> accounts) {
		AccountDao ad = new AccountDaoImpl();
		accounts.forEach(a -> ad.updateAccount(a));
	}
}
