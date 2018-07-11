package proj0;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * This is the serialUID for User
	 */
	private static final long serialVersionUID = 7481145733673760141L;
	
	//stores the user info (userid,pass,fname,lastname,balance,authentication)
	private String userid;
	private String pass;
	private String fname;
	private String lname;
	private double balance;
	private boolean auth;
	

	/**
	 * Empty user
	 */
	public User(){
		super();
	}
	
	/**
	* Constructor which takes in userinfo and sets the balance
	* and authentication to false by default
	*/
	public User(String userid, String pass, String fname, String lname){
		this.userid = userid;
		this.pass = pass;
		this.fname = fname;
		this.lname = lname;
		this.balance = 0;
		this.auth = false;
	}
	
	/**
	 * This method takes in a string inputs and checks if it matches with the user's password
	 * @param pass
	 * password to be checked
	 * @return boolean
	 * returns true if passwords match and false otherwise
	 */
	public boolean confirmPassword(String pass) {
		if(pass.equals(this.pass)) return true;
		return false;
	}
	
	/**
	 * Overridden toString method which returns user info
	 * @return String
	 * prints the user info excluding their password
	 */
	@Override
	public String toString() {
		return "User [userid=" + userid + ", fname=" + fname + ", lname=" + lname +
				", balance=" + balance + "]";
	}

	//Getters and Setters

	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}


}