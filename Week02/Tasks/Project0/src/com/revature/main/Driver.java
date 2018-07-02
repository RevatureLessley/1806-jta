package com.revature.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.Bank;
import com.revature.service.AccountService;
import com.revature.util.Connections;


public class Driver 
{
	final static Logger logger = Logger.getLogger(Bank.class);
	
	public static void main(String[] args) 
	{
		Connection conn = Connections.getConnection();
		logger.info("connection established");
		
		if(conn!=null)
		{
			try 
			{
				conn.close();
				logger.info("connection closed");
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		AccountService as = new AccountService();
		
		ArrayList<Account> accs = as.getAllAccounts();
		
		logger.info("Bank object initialized with Arraylist<Account> accs");
		Bank testBank = new Bank(accs);
		testBank.login();	
	}

}
