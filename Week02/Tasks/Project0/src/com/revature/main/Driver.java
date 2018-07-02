package com.revature.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.Bank;
import com.revature.service.AccountService;
import com.revature.util.Connections;

public class Driver 
{
	final static Logger logger = Logger.getLogger(Bank.class);
	private static final int USER = 0;
	private static final int ADMIN = 1;
	private static ArrayList<Account> accs = null;
	
	
	public static void main(String[] args) 
	{
		Connection conn = Connections.getConnection();
		
		if(conn!=null)
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		AccountService as = new AccountService();
		
		ArrayList<Account> accs = as.getAllAccounts();
		
		//displayAccountGroup(accs);
		
		Bank testBank = new Bank(accs);
		testBank.login();
				
	}

}
