import java.io.Serializable;

public class Account implements Serializable {
	private int balance;
	
	public Account() {
		this.balance = 0;
	}
	
	public int deposit(int deposit){
		balance += deposit;
		return balance;
	}
	
	public int withdrawal(int withdraw) {
		balance -= withdraw;
		return balance;
	}

	public int getBalance() {
		return balance;
	}
	
	
	

}
