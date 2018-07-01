package p0.dao;

import java.util.ArrayList;

import p0.AdministratorAccount;
import p0.BankerAccount;
import p0.LoanerAccount;
import p0.PlayerAccount;

public interface AccountListDao {
	public Integer getAccSize();
	public ArrayList<PlayerAccount> getPlayerArray();
	public AdministratorAccount getAdmin();
	public BankerAccount getBanker();
	public LoanerAccount getLoaner();
}
