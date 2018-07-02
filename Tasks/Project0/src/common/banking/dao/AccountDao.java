package common.banking.dao;

import common.banking.application.customer;

public interface AccountDao {
	public void depositAccount(customer account, float amount);
	public void withdrawAccount(customer account, float amount);
	public float getBalance(customer account);
	public void createAccount(customer account);
	public String getPassword(customer account);
}
