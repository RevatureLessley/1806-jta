package service;

import java.util.List;

import beans.Account;
import beans.AdminAccount;
import beans.BankerAccount;
import beans.CustomerAccount;
import beans.Loan;
import dao.AdminDaoImpl;
import dao.BankerDaoImpl;
import dao.CustomerDaoImpl;
import dao.LoanDaoImpl;
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
	
	public boolean insertLoan(Loan loan, Integer id) {
		LoanDaoImpl loanD = new LoanDaoImpl();
		return loanD.insertLoanViaSp(loan, id);
	}
	
	public CustomerAccount selectCustomer(String un, String pw) {
		CustomerDaoImpl custD = new CustomerDaoImpl();
		return custD.selectCustomerByUNandPw(un, pw);
	}
	
	public CustomerAccount selectCustomerById(Integer accId) {
		CustomerDaoImpl custD = new CustomerDaoImpl();
		return custD.selectCustomerById(accId);
	}
	
	public List<CustomerAccount> selectAllCustomers(){
		CustomerDaoImpl custD = new CustomerDaoImpl();
		return custD.selectAllCustomers();
	}
	
	public List<Loan> selectAllLoansByAccId(Integer accId){
		LoanDaoImpl loanD = new LoanDaoImpl();
		return loanD.selectAllLoansByAccId(accId);
	}
	
	public List<Loan> selectAllLoans(){
		LoanDaoImpl loanD = new LoanDaoImpl();
		return loanD.selectAllLoans();
	}
	
	public AdminAccount selectAdmin(String un, String pw) {
		AdminDaoImpl adminD = new AdminDaoImpl();
		return adminD.selectAdminByUNandPw(un, pw);
	}
	
	public BankerAccount selectBanker(String un, String pw) {
		BankerDaoImpl bankerD = new BankerDaoImpl();
		return bankerD.selectBankerByUNandPw(un, pw);
	}
	
	public Boolean updateCustomerAccount(CustomerAccount acc) {
		CustomerDaoImpl custD = new CustomerDaoImpl();
		return custD.updateCustomer((CustomerAccount)acc);
	}
	
	public Boolean updateLoan(Loan loan, Integer accId) {
		LoanDaoImpl loanD = new LoanDaoImpl();
		return loanD.updateLoan(loan, accId);
	}
	
	public Account findUser(String un) {
		return DatabaseUtil.findUserOnDB(un);
	}
}
