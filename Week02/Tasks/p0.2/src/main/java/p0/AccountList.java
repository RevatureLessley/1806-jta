package p0;

import java.util.ArrayList;

public class AccountList
{
	/**
	 * A serializable class to store the active Admin, Banker, Loaner 
	 * and all currently acitve player characters as well as manage deletion 
	 * of accounts that are flagged for removal.
	 */

	private AdministratorAccount admin;
	private BankerAccount banker;
	private LoanerAccount loaner;
	private ArrayList<PlayerAccount> accounts = new ArrayList<PlayerAccount>();	
	private ArrayList<PlayerAccount> waiting = new ArrayList<PlayerAccount>();
	
	public AccountList() {
		admin = null;
		banker = null;
		loaner = null;
	}
	
	public AccountList(ArrayList<PlayerAccount> list, AdministratorAccount a, BankerAccount b, LoanerAccount l){
		accounts = list;
		admin = a;
		banker = b;
		loaner = l;
	}
	
	public ArrayList<PlayerAccount> getList(){
		return accounts;
	}
	
	public void add(PlayerAccount a){
		accounts.add(a);
	}
	
	public ArrayList<PlayerAccount> getWaitingList(){
		return waiting;
	}

	public void addWaiting(PlayerAccount a) {
		waiting.add(a);
	}
	public BankerAccount getBanker() {
		return banker;
	}

	public void setBanker(BankerAccount banker) {
		this.banker = banker;
	}

	public AdministratorAccount getAdmin() {
		return admin;
	}

	public void setAdmin(AdministratorAccount admin) {
		this.admin = admin;
	}

	public LoanerAccount getLoaner() {
		return loaner;
	}

	public void setLoaner(LoanerAccount loaner) {
		this.loaner = loaner;
	}

	
}