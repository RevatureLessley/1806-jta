package com.revature.project0.dao;

import java.util.List;

import com.revature.project0.Account;

public interface AccountDao {
	public Integer insertAccount(Account acc);
	public Account selectAccountById(Integer id);
	public Integer deleteAccountById(Integer id);
	public Integer updateAccount(Account acc);
	public List<Account> getAccountByUser(Integer userId);
	public List<Account> getAccountByUserName(String userName);
}
