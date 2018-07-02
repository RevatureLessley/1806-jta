package com.crypt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crypt.beans.Account;

public class AccountDaoImpl extends DAO{
	public static List<Account> selectAll() throws SQLException{
		List<Account> accounts = new ArrayList<>();
		
		ResultSet rs = getData("SELECT * FROM acct");
		while(rs.next()) {
			Account a = new Account(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getInt(3), 
					rs.getInt(4), 
					rs.getInt(5)
					);
			accounts.add(a);
		}
		
		return accounts;
	}
}
