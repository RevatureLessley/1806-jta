package Project0.dao;

import Project0.bean.*;;

public interface AccountDao {
	public Boolean insertAccount(Account account);
	public Integer updateAccount(Account account);
	public Account selectAccountById(Integer id);
	

}
