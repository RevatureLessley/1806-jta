package com.crypt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.util.Connections;

public class AccountDaoImpl{
	public static List<Account> selectAll() throws SQLException{
		List<Account> accounts = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try(Connection conn = Connections.getConnection()){

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM acct");

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
}