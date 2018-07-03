package Project0.bean;

/**
 * The Account class is a blueprint for an account object following the 'bean' design pattern.
 * @author Vladimir Bukhalo
 *
 */
public class Account {
	private Integer accountId;
	private Integer balance;
	
	public Account(Integer accoundId, Integer balance) {
		super();
		this.accountId = accoundId;
		this.balance = balance;
	}
	
	public Account(Integer balance) {
		this.balance = balance;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccoundId(Integer accoundId) {
		this.accountId = accoundId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
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
	

}
