package com.crypt.Services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crypt.beans.Account;
import com.crypt.dao.AccountDaoImpl;
import com.crypt.dao.DataFileDaoImpl;
import com.crypt.dao.NoteDaoImpl;

public class AccountService {
	public static Map<String, Account> selectAllAccounts() {
		Map<String,Account> accounts = new HashMap<>();
		List<Account> accountList = new ArrayList<>();
		try {
			accountList = AccountDaoImpl.selectAll();
			for(Account a : accountList) {
				a.setTransactionHistory(NoteDaoImpl.selectAll(a));
				a.setItems(DataFileDaoImpl.selectAll(a));
				accounts.put(a.getUsername(), a);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}
}
