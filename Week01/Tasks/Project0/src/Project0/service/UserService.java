package Project0.service;

import Project0.bean.User;
import Project0.dao.UserDao;
import Project0.dao.UserDaoImpl;

/**
 * The UserService class interact with the User data access object. 
 * @author Vladimir Bukhalo
 *
 */
public class UserService {
	public boolean insertUser(User user) {
		UserDao ud = new UserDaoImpl();
		return ud.insertUser(user);		
	}
	
	/**
	 * The getUserByUserName() returns a User based on a specified username.
	 * @param username The username of the User to be retrieved. 
	 * @return Returns user with the specified username.
	 */
	public User getUserByUsername(String username) {
		UserDao ud = new UserDaoImpl();
		
		User user = ud.selectUserByUsername(username);
		if(user != null) {
			return user;
		}
		else {
			return null;
		}
	}
	
	/**
	 * The updateApproval() approves a user with a specified username.
	 * @param username The username of the user to approve.
	 */
	public void updateApproval(String username) {
		UserDao ud = new UserDaoImpl();
		User u = ud.selectUserByUsername(username);
		u.setApproved(true);
				
		ud.updateUser(u);
	}
	
	/**
	 * The getNumberOfUsers() returns the number of users.
	 * @return The number of users.
	 */
	public int getNumberOfUsers() {
		UserDao ud = new UserDaoImpl();
		return ud.tableSize();
	}
	
}
