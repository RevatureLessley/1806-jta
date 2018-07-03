package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.BUser;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.UserDaoImpl;

public class UserService{

	UserDaoImpl userDao = new UserDaoImpl();
	private static Logger logger = Logger.getLogger(UserService.class);

	/**
	 * Gets a userId by name from the DB
	 * 
	 * @param name
	 * @return User associated with the given name
	 */
	public Integer getUser(String name) {
		BUser bUser = userDao.selectByName(name);
		if (bUser != null)
			return bUser.getId();
		else
			return null;
	}

	/**
	 * Accesses the DB to determine whether a user name is available
	 * @param name
	 * @return
	 */
	public boolean checkUsernameAvailable(String name) {
		return userDao.selectByName(name) == null;
	}

	/**
	 * Creates a new User and adds it to the database.
	 * TODO password encryption
	 * 
	 * @param name
	 * @param password
	 * @return new User instance
	 */
	public Integer addUser(String name, String password) {
		Integer id = userDao.selectAll().size() + 1;

		BUser u = new BUser(id, 2, name, password);
		userDao.insert(u);
		
		logger.info("added user " + name + " with id " + id);

		return id;
	}

	/**
	 * Creates a string which contains all user names. Each user name is followed by
	 * the sum of balances in all the user's accounts.
	 * 
	 * @return
	 */
	public String summarizeAllUsers() {
		List<BUser> l = userDao.selectAll();

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2;

		for (BUser u : l) {
			sb2 = new StringBuilder();
			sb2.append(u.getName());

			if (u.isAdmin())
				sb2.append("(A)");

			while (sb2.length() < 15)
				sb2.append(" ");

			sb2.append(AccountService.formatCurrency(new AccountDaoImpl().getUserTotalBalance(u.getId())));
			sb2.append('\n');

			sb1.append(sb2.toString());
		}

		return sb1.toString();
	}

	/**
	 * Checks if a given String matches the User's password
	 * 
	 * @param password
	 * @return true if valid, else false
	 */
	public int validateLogin(String username, String password) {

		BUser u = userDao.selectByName(username);

		if (password.equals(u.getPassword()))
			return 1;

		return 0;
	}

	/**
	 * 
	 * @return true if the user is an admin, else false
	 */
	public boolean isAdmin(Integer userId) {
		BUser u = userDao.selectById(userId);

		return u.isAdmin();
	}

	/**
	 * Sets the user's admin permissions
	 * 
	 * @param admin
	 */
	public void setAdmin(Integer userId, boolean admin) {
		BUser u = userDao.selectById(userId);

		u.setType(admin ? 1 : 2);
		
		logger.info("user " + userId + " is now an admin ");

		userDao.update(u);
	}

	/**
	 * Checks whether the password is long enough.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkPasswordValid(String password) {
		if (password.length() < 6)
			return false;

		return true;
	}

	/**
	 * Checks whether the username is long enough. Usernames also cannot contain any
	 * spaces.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkUsernameValid(String name) {

		if (name.length() < 4)
			return false;
		if (name.indexOf(' ') != -1)
			return false;

		return true;
	}

	public String getUserName(Integer userId) {
		BUser u = userDao.selectById(userId);
		return u.getName();
	}

}
