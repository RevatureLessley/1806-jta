/*
 * This class simply serves as a way to store user info
 * and create new users. No real functionality 
 */


package com.revature.week1.tasks;
import java.io.Serializable;
//Use this class to store the person's info

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 88660438728653761L;
	private String name;
	private String password;
	private float balance;
	private boolean isAdmin;
	private boolean isApproved;
	
	
	
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public float getBalance() 
	{
		return balance;
	}
	public void setBalance(float balance) 
	{
		this.balance = balance;
	}
	
	public boolean isAdmin()
	{
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	
	public boolean isApproved()
	{
		return isApproved;
	}
	public void setApproved(boolean isApproved)
	{
		this.isApproved = isApproved;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", balance=" + balance + "]";
	}
	
	public User(String name, String password, float balance, boolean isAdmin, boolean isApproved)
	{
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.isAdmin = isAdmin;
		this.isApproved = isApproved;
	}
	
	
	
	
	
	
	

}
