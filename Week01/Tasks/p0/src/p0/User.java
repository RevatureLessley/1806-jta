package p0;

public class User implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final String accountName;
	private String password;
	private int role;
	private double balance;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		// round to two decimal points
		this.balance = (double)Math.round(balance * 100d) / 100d;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public User(String accountName,  String password, int role) {
		this.accountName = accountName;
		this.password = password;
		this.role = role;
		this.balance = 100.0;
	}
	
	@Override
	public String toString() {
		return "User [accountName=" + accountName + ", password=" + password + ", role=" + role + ", balance=$" + balance
				+ "]";
	}

}
