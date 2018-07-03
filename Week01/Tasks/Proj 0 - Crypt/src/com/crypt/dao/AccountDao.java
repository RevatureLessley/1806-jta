package com.crypt.dao;

import java.sql.SQLException;
import java.util.List;

import com.crypt.beans.Account;

public interface AccountDao{
	List<Account> selectAll() throws SQLException;
	List<Account> selectConditional(String sql) throws SQLException;
	Boolean insertNewAccount(Account a);
	Integer updateAccount(Account a);
	Boolean approveAccount(Integer index);
}
