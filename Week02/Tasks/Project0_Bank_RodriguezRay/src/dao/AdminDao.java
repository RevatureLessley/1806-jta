package dao;

import java.util.List;

import beans.AdminAccount;
import beans.CustomerAccount;

public interface AdminDao {
	public void insertAdmin(AdminAccount admin);
	public AdminAccount selectAdminById(Integer id);
	public AdminAccount selectAdminByUNandPw(String un, String pw);
	public List<AdminAccount> selectAllAdmins();
	public Integer deleteAdminById(Integer id);
	public Integer updateAdmin(AdminAccount admin);
	public Boolean insertAdminViaSp(AdminAccount admin);
}
