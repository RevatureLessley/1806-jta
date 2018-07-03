package service;

import beans.Account;
import beans.AdminAccount;
import beans.BankerAccount;
import beans.CustomerAccount;
import dao.AdminDaoImpl;
import dao.BankerDaoImpl;
import dao.CustomerDaoImpl;
import util.DatabaseUtil;

public class AccountService {
	public boolean insertUser(CustomerAccount cust) {
		CustomerDaoImpl custD = new CustomerDaoImpl();
		return custD.insertCustomerViaSp(cust);
	}
	public boolean insertAdmin(AdminAccount admin) {
		AdminDaoImpl adminD = new AdminDaoImpl();
		return adminD.insertAdminViaSp(admin);
	}
	public boolean insertBanker(BankerAccount banker) {
		BankerDaoImpl bankerD = new BankerDaoImpl();
		return bankerD.insertBankerViaSp(banker);
	}
	
	public CustomerAccount selectCustomer(String un, String pw) {
		CustomerDaoImpl custD = new CustomerDaoImpl();
		return custD.selectCustomerByUNandPw(un, pw);
	}
	
	public AdminAccount selectAdmin(String un, String pw) {
		AdminDaoImpl adminD = new AdminDaoImpl();
		return adminD.selectAdminByUNandPw(un, pw);
	}
	
	public BankerAccount selectBanker(String un, String pw) {
		BankerDaoImpl bankerD = new BankerDaoImpl();
		return bankerD.selectBankerByUNandPw(un, pw);
	}
	
	public Account findUser(String un) {
		return DatabaseUtil.findUserOnDB(un);
	}
}
