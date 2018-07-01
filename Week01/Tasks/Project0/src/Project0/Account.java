package Project0;
import java.io.Serializable;

/**
 * The Account class tracks user balance and allows for deposits and withdrawals.
 * @author Vladimir Bukhalo
 *
 */
public class Account implements Serializable {
	private int balance;
	
	/**
	 * The Account() constructor creates an Account object and set the initial balance to 0.
	 */
	public Account() {
		this.balance = 0;
	}
	
	/**
	 * The deposit() method allows a user to deposit into their account.
	 * @param deposit The amount the user deposits.
	 * @return Returns the balance after the deposit.
	 */
	public int deposit(int deposit){
		balance += deposit;
		return balance;
	}
	
	/**
	 * The withdrawal() method allows a user to withdraw from their account.
	 * @param withdraw The amount the user withdraws.
	 * @return Returns the balance after withdrawal.
	 */
	public int withdrawal(int withdraw) {
		balance -= withdraw;
		return balance;
	}

	/**
	 * The getBalance() method returns the current balance of the account.
	 * @return Returns the account balance. 
	 */
	public int getBalance() {
		return balance;
	}
	
	
	

}
