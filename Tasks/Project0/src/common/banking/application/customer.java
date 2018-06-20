package common.banking.application;

import java.io.*;

public class customer implements java.io.Serializable {
    /*Variable and Data for each customer*/
    private float balance = 0;
    private boolean activated = false; 
    private String first_name = "First Name";
    private String last_name = "Last Name";
    private StringBuffer ss_number = new StringBuffer("numbers");

    
    /*Constructor takes in one double variable to set the balance*/
    public customer(String f_name, String l_name, String ss_num){
      first_name = f_name;
      last_name = l_name; 
      ss_number = new StringBuffer(ss_num);
    }
    
    
    
//=================SOCIAL SECURITY EDITORS===============
    //returns the social security number
	public StringBuffer getSs_number() {
		return ss_number;
	}

    //sets the social security number
	public void setSs_number(StringBuffer ss_number) {
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
    public double getbalance (){
      return balance; 
    }

	/*Print out the balance*/
    public void statement (){
      System.out.println("Hello "+ first_name + " " + last_name);
      System.out.println("Balance is: ");
      System.out.printf("%.2f",balance);
      
    }

    /*Deposit some munnies*/
    public void deposit (float depositamount){
      this.balance = balance + depositamount;
    }

    /*Take sum munnies*/
    public void withdraw (float withdrawamount){
      this.balance = balance - withdrawamount;
    }
}
