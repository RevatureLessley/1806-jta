package bank;

import java.io.Serializable;

public  class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6620672985645695111L;
	static int accNumCnt = 1000;
	int accNum;
	String accType;
	String fName;
	String lName;
	String userName;
	String password;
	
	public Account(String accType, String fName, String lName, String userName, String password) {
		super();
		this.accType = accType;
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.password = password;
		accNum = accNumCnt;
		accNumCnt++;
	}
	
	public Account(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public Account() {
		super();
	}
	
	public void DisplayAccDetails() {
		System.out.format("|%-15d|%-12s|%-20s|%-20s|", accNum, accType, fName, lName);
	}
	
	public void UpdateAccountDetails() {
		
	}
	
	public boolean isEquivalent(Account obj) {
		if (this.userName.equals(obj.userName))
			return true;
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Account)) {
			return false;
		}
		
		Account acc = (Account)obj;
		
		return acc.userName.equals(this.userName) && acc.password.equals(this.password);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
        result = 31 * result + fName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + lName.hashCode();
        return result;
	}
}
