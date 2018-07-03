package Project0.service;

import java.util.List;

import Project0.bean.UnAppUser;
import Project0.dao.UnAppUserDao;
import Project0.dao.UnAppUserDaoImpl;

/**
 * The UnAppUserService interacts with the unapproved user data access object.
 * @author Vladimir Bukhalo
 *
 */
public class UnAppUserService {
	public boolean insertUnAppUser(UnAppUser user) {
		UnAppUserDao ud = new UnAppUserDaoImpl();
		return ud.insertUser(user);
	}
	
	/**
	 * The getNumberOfUsers() returns the number of unapproved users.
	 * @return Number of unapproved users.
	 */
	public int getNumberOfUsers() {
		UnAppUserDao ud = new UnAppUserDaoImpl();
		return ud.tableSize();
	}
	
	/**
	 * The getAllUnAppUsers() return a list of all unapproved users.
	 * @return List of all unapproved users.
	 */
	public List<UnAppUser> getAllUnAppUsers(){
		UnAppUserDao ud = new UnAppUserDaoImpl();
		
		List<UnAppUser> unUsers = ud.selectAllUsers();
		return unUsers;
	}
	
	/**
	 * The deleteUserByUsername() deletes a unapproved user based on a specified username.
	 * @param username The username of the unapproved user to be deleted. 
	 */
	public void deleteUserByUsername(String username) {
		UnAppUserDao ud = new UnAppUserDaoImpl();
		ud.deleteUserByUsername(username);
	}

}
