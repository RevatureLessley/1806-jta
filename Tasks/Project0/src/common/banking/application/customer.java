package common.banking.application;

import java.io.*;

public class customer implements java.io.Serializable {
    /*Variable and Data for each customer*/
    private float balance = 0;
    private boolean activated = false; 
    private String first_name = "First Name";
    private String last_name = "Last Name";
    private String ss_number = "numbers";
    private StringBuffer password = new StringBuffer();
    private boolean admin = false;

    
    /*Constructor takes in one double variable to set the balance*/
    public customer(String f_name, String l_name, String ss_num, StringBuffer newpass){
      this.first_name = f_name;
      this.last_name = l_name; 
      this.ss_number = ss_num;
      this.password = newpass;
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
    public double getbalance ()
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

	public StringBuffer getPassword() 
	{return password;}

	public void setPassword(StringBuffer password) 
	{this.password = password;}


//===============Activation editors accessible only by ADMIN==========
	//Checks to see if the account is activated
	public boolean isActivated() 
	{return activated;}

	//Activates the account to either true or false
	/**Activates or deactivates the account*/
	public void setActivated(boolean activated) 
	{this.activated = activated;}
	
//==============Check to see if you are an admin=======================

	public boolean isAdmin() 
	{return admin;}

	/**If the parameter given is a string
	 * it checks to see if the password is correct
	 * and sets the account as an admin and activates it
	 * this is done at the login menu*/
	public void setAdmin(String admin_password) 
	{
		if(admin_password.equals("password1")) 
		{
		System.out.println("Correct you are now registered as an admin");
		this.admin = true;
		}
		else {System.out.println("Wrong password you are not set as an admin");}
	}
	
	/**If the parameter given is a boolean
	 * Sets the admin as either true or false */
	public void setAdmin(boolean setter) 
	{this.admin = setter;}




}
