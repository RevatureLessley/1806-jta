package dao;

import java.util.List;

import beans.AdminAccount;
import beans.BankerAccount;

public interface BankerDao {
	public void insertBanker(BankerAccount banker);
	public BankerAccount selectBankerById(Integer id);
	public BankerAccount selectBankerByUNandPw(String un, String pw);
	public List<BankerAccount> selectAllBankers();
	public Integer deleteBankerById(Integer id);
	public Integer updateBanker(BankerAccount banker);
	public Boolean insertBankerViaSp(BankerAccount banker);
}
