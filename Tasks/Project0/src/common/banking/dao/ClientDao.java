package common.banking.dao;

import common.banking.application.customer;

public interface ClientDao {
	public void insertClient(customer newCustomer);
	public void activateClient(String ssnum);
	public void adminClient(customer newCustomer);
	public boolean doesExist(customer checkCustomer);
	public int isActivated(customer checkCustomer);
	public int isAdmin(customer checkCustomer);
}
