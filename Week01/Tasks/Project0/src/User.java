import java.io.Serializable;

public class User implements Serializable {
	
	private String userName;
	private String password;
	private Account account;
	private boolean isApproved;
	private boolean isAdmin;
	
	public User(String userName, String password, Account account, boolean isApproved, boolean isAdmin) {
		this.userName = userName;
		this.password = password;
		this.account = account;
		this.isApproved = isApproved;
		this.isAdmin = isAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
	
	public int getUserBalance() {
		return account.getBalance();
	}

	public Account getAccount() {
		return account;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
	
	
	

	

}
