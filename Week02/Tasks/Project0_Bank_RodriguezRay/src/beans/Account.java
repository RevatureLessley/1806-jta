package beans;

import java.io.Serializable;

public  class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6620672985645695111L;
	int accNum;
	String accType;
	String fName;
	String lName;
	String userName;
	String password;
	
	public Account(int accNum, String accType, String fName, String lName, String userName, String password) {
		super();
		this.accType = accType;
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.password = password;
		this.accNum = accNum;
	}
	
	public Account(String accType, String fName, String lName, String userName, String password) {
		super();
		this.accType = accType;
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.password = password;
	}
	
	public Account(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public Account() {
		super();
	}
	
	
	
	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
