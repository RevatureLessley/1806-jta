package Project0.dao;

import java.util.List;

import Project0.bean.UnAppUser;

public interface UnAppUserDao {
	public Boolean insertUser(UnAppUser user);
	public UnAppUser getUserById(Integer id);
	public List<UnAppUser> selectAllUsers();
	public Boolean deleteUserByUsername(String username);
	public Integer tableSize();

}
