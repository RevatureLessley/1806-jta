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
		return ald.getPlayerArray();
	}
	
	public ArrayList<PlayerAccount> getWaitingPlayerArray(){
		return ald.getWaitingPlayerArray();
	}
	
	public AdministratorAccount getAdmin() {
		return ald.getAdmin();
	}
	
	public BankerAccount getBanker() {
		return ald.getBanker();
	}
	
	public LoanerAccount getLoaner() {
		return ald.getLoaner();
	}
}
