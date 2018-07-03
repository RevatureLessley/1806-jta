package dao;

import java.util.List;

import beans.CustomerAccount;

public interface CustomerDao {
	public void insertCusomter(CustomerAccount cust);
	public CustomerAccount selectCustomerById(Integer id);
	public List<CustomerAccount> selectAllCustomers();
	public Integer deleteCustomerById(Integer id);
	public Integer updateCustomer(CustomerAccount cust);
	public Boolean insertCustomerViaSp(CustomerAccount cust);
}
