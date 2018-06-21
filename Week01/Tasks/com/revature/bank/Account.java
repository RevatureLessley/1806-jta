package com.revature.bank;

import java.io.Serializable;

public class Account implements Serializable
{
	private static final long serialVersionUID = 2022586512565322588L;
	private String userName;
	private String password;
	private int accountNumber;
	private int accountValue;
	private int accountType;
	
	public Account(String userName, String password, int accountNumber, int accountValue, int accountType) 
	{
		super();
		this.userName = userName;
		this.password = password;
		this.accountNumber = accountNumber;
		this.accountValue = accountValue;
		this.accountType = accountType;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public int getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public int getAccountValue() 
	{
		return accountValue;
	}

	public void setAccountValue(int accountValue) 
	{
		this.accountValue = accountValue;
	}
	
	public int getAccountType()
	{
		return accountType;
	}
	
	public void setAccountType(int accountType)
	{
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + ", accountNumber=" + accountNumber
				+ ", accountValue=" + accountValue + ", accountType=" + accountType + "]";
	}
}
