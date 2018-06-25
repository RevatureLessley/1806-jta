package controller;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.log4j.Logger;

import model.Account;
import model.User;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public final class UserController implements Serializable {

	private static final long serialVersionUID = -195953297393512306L;
	private HashMap<String, User> users;
	
	private static Logger logger = Logger.getLogger(UserController.class);

	UserController() {
		users = new HashMap<String, User>();

		User admin = addUser("Admin", "pass123");
		admin.setAdmin(true);

	}

	/**
	 * Gets a user by name
	 * 
	 * @param name
	 * @return User associated with the given name
	 */
	public User getUser(String name) {
		return users.get(name);
	}

	/**
	 * Checks whether any users have already taken the given name.
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkUsernameAvailable(String name) {
		return !users.containsKey(name);
	}

	/**
	 * Creates a new User and adds it to the controller.
	 * 
	 * @param name
	 * @param password
	 * @return new User instance
	 */
	public User addUser(String name, String password) {
		
		logger.info("New user " + name + " created.");
	
		User user = new User(name, password);
		users.put(name, user);

		return user;
	}

	/**
	 * Creates a string which contains all user names. Each user name is followed by
	 * the sum of balances in all the user's accounts.
	 * 
	 * @return
	 */
	public String summarizeAllUsers() {

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2;

		for (User u : users.values()) {
			sb2 = new StringBuilder();
			sb2.append(u.getName());

			if (u.isAdmin())
				sb2.append("(A)");

			while (sb2.length() < 15)
				sb2.append(" ");

			sb2.append(Account.formatCurrency(u.totalBalance()));
			sb2.append('\n');

			sb1.append(sb2.toString());
		}

		return sb1.toString();
	}
}
