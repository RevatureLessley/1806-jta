package dao;

import java.util.List;

import beans.CustomerAccount;

public interface CustomerDao {
	public void insertCusomter(CustomerAccount cust);
	public CustomerAccount selectCustomerById(Integer id);
	public CustomerAccount selectCustomerByUNandPw(String un, String pw);
	public boolean withdrawById(Integer accId, Double amount);
	public List<CustomerAccount> selectAllCustomers();
	public Integer deleteCustomerById(Integer id);
	public Boolean updateCustomer(CustomerAccount cust);
	public Boolean insertCustomerViaSp(CustomerAccount cust);
}
