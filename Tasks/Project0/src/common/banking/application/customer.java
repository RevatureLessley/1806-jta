package common.banking.application;

import java.io.*;

public class customer implements java.io.Serializable {
    /*Variable and Data for each customer*/
    private float balance = 0;
    private int activated = 0; 
    private int admin = 0;
    private String first_name = "First Name";
    private String last_name = "Last Name";
    private String ss_number = "numbers";
    private String password = null;
    private String address = null;
    private String phonenum = null;
    
    
    //Resets the default client
    public void reset() {
        this.balance = 0;
        this.activated = 0; 
        this.admin = 0;
        this.first_name = "First Name";
        this.last_name = "Last Name";
        this.ss_number = "numbers";
        this.password = null;
        this.address = null;
        this.phonenum = null;    
        }

    
    /*Constructor takes in one double variable to set the balance*/
    public customer(String first_name, String last_name, String ss_number,
			String password, String address, String phonenum) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.ss_number = ss_number;
		this.password = password;
		this.address = address;
		this.phonenum = phonenum;
	}
    
    
    
//=================SOCIAL SECURITY EDITORS===============
    //returns the social security number
	public String getSs_number() {
		return ss_number;
	}



	//sets the social security number
	public void setSs_number(String ss_number) {
		this.ss_number = ss_number;
	}
//=================NAME EDITORS==========================
	//returns first name
    public String getFirst_name() {
		return first_name;
	}

    //sets first name
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	//returns last name
	public String getLast_name() {
		return last_name;
	}

	//sets last name
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
//=================BALANCE EDITORS=======================
    /*Get the balance*/
    public float getbalance ()
    {return balance;}

	/*Print out the balance*/
    /**Prints out the account information NAME and LAST NAME and BALANCE*/
    public void statement ()
    {
      System.out.println("Hello "+ first_name + " " + last_name);
      System.out.println("Balance is: ");
      System.out.printf("%.2f",balance);
      System.out.println(""); 
    }

    /*Deposit some munnies*/
    /**Adds the amount given to the balance*/
    public void deposit (float depositamount)
    {this.balance = balance + depositamount;}

    /*Take sum munnies*/
    /**Substracts the amount given to the balance*/
    public void withdraw (float withdrawamount)
    {this.balance = balance - withdrawamount;}

    /*Set the balance*/
    /**Sets the balance to a set amount*/
    public void setBalance(float amount) 
    {this.balance = amount;}
    
    /*Returns the balance*/
    /**Returns the amount given*/
    public float getBalance() 
    {return balance;}


//================PASSWORD EDITORS=====================

	public String getPassword() 
	{return password;}

	public void setPassword(String password) 
	{this.password = password;}


//===============Activation editors accessible only by ADMIN==========
	//Checks to see if the account is activated
	public int isActivated() 
	{return activated;}

	//Activates the account to either true or false
	/**Activates or deactivates the account*/
	public void setActivated(int activated) 
	{this.activated = activated;}
	
//==============Check to see if you are an admin=======================

	public int isAdmin() 
	{return admin;}

	/**If the parameter given is a string
	 * it checks to see if the password is correct
	 * and sets the account as an admin and activates it
	 * this is done at the login menu*/
	public void setAdmin(String admin_password) 
	{
		if(admin_password.equals("password1")) //Change 'password1' to change admin authorization code
		{
		System.out.println("Correct you are now registered as an admin");
		this.admin = 1;
		}
		else {System.out.println("Wrong password you are not set as an admin");}
	}
	
	/**If the parameter given is a boolean
	 * Sets the admin as either true or false */
	public void setAdmin(int setter) 
	{this.admin = setter;}



	
	//==================ADDRESS GETTERS AND SETTER===================

	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}


	//==================PHONE NUMBER GETTERS AND SETTER===================

	public String getPhonenum() {
		return phonenum;
	}



	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}




}
