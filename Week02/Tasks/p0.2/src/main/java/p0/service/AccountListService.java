package p0.service;

import java.util.ArrayList;

import p0.AdministratorAccount;
import p0.BankerAccount;
import p0.LoanerAccount;
import p0.PlayerAccount;
import p0.dao.AccountListDaoImpl;

public class AccountListService {
	private AccountListDaoImpl ald = new AccountListDaoImpl();

	public Integer getAccSize() {
		return ald.getAccSize();
	}
	
	public ArrayList<PlayerAccount> getPlayerArray(){
		ald.getPlayerArray();
		return null;
	}
	
	public AdministratorAccount getAdmin() {
		ald.getAdmin();
		return null;
	}
	
	public BankerAccount getBanker() {
		ald.getBanker();
		return null;
	}
	
	public LoanerAccount getLoaner() {
		ald.getLoaner();
		return null;
	}
}
