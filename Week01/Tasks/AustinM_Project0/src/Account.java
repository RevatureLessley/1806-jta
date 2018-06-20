/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class Account {
	
	private String name;
	private double balance;
	private int accountType = 0;
	
	public Account(String name, int type) {
		this.name = name;
		this.accountType = type;
	}
	
	public double getBalance() {
		return 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void withdraw(double amount) {
		balance -= amount;
	}
	
}
