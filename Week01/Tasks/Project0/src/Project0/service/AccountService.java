package Project0.service;

import Project0.bean.Account;
import Project0.dao.AccountDao;
import Project0.dao.AccountDaoImpl;

/**
 * The AccountService class is interacts with the Account data access object. 
 * @author Vladimir Bukhalo
 *
 */
public class AccountService {
	public boolean insertAccount(Account account) {
		AccountDao ad = new AccountDaoImpl();
		return ad.insertAccount(account);
	}
	
	/**
	 * The getAccountById() returns an account using a specified id.
	 * @param id The id of the account.
	 * @return Returns the account with the specified id.
	 */
	public Account getAccountById(Integer id) {
		AccountDao ad = new AccountDaoImpl();
		Account account = ad.selectAccountById(id);
		
		return account;
	}
		
	/**
	 * The deposit() deposits a specified amount into an account and updates the account in the database.
	 * @param id The id of the account to be updated.
	 * @param amount The amount to be deposited.
	 * @return Returns the account balance after the deposit.
	 */
	public int deposit(Integer id, Integer amount) {
		AccountDao ad = new AccountDaoImpl();
		Account account = ad.selectAccountById(id);
		
		account.setBalance(account.deposit(amount));
		ad.updateAccount(account);
		
		return account.getBalance();
	}
	
	/**
	 * The withdraw() withdraws a specified amount from an account and updates the account in the database. 
	 * @param id The id of the account to be updated.
	 * @param amount The amount to be withdrawn.
	 * @return Returns the account balance after withdrawal.
	 */
	public int withdraw(Integer id, Integer amount) {
		AccountDao ad = new AccountDaoImpl();
		Account account = ad.selectAccountById(id);
		
		account.setBalance(account.withdrawal(amount));
		ad.updateAccount(account);
		
		return account.getBalance();
	}
	
	/**
	 * The balance() returns the account balance of a specified account.
	 * @param id The id of the account/
	 * @return Returns the account balance of the specified account.
	 */
	public int balance(Integer id) {
		AccountDao ad = new AccountDaoImpl();
		Account account = ad.selectAccountById(id);
		
		return account.getBalance();
	}
	
}
