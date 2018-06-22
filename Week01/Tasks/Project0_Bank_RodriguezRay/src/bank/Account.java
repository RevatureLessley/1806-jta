package bank;

public  class Account {
	static int accNum = 10000;
	String accType;
	String fName;
	String lName;
	String userName;
	String password;
	boolean banned;
	boolean approved;
	
	public Account(String accType, String fName, String lName, String userName, String password) {
		super();
		this.accType = accType;
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.password = password;
		approved = false;
		accNum++;
	}
	
	public Account(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public void DisplayAccDetails() {
		System.out.format("|%-15d|%-10s|%-20s|%-20s|", accNum, accType, fName, lName);
	}
	
	public void UpdateAccountDetails() {
		
	}
	
	public boolean isEquivalent(Account obj) {
		if (this.userName.equals(obj))
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
