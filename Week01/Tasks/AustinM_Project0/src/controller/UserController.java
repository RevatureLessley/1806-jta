package controller;
import java.io.Serializable;
import java.util.HashMap;

import model.Account;
import model.User;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class UserController implements Serializable{

	private static final long serialVersionUID = -195953297393512306L;
	private HashMap<String, User> users;
	

	public UserController() {
		users = new HashMap<String, User>();
		
		User admin = addUser("Admin","pass123");
		admin.setAdmin(true);

	}
	
	public User getUser(String name) {
		return users.get(name);
	}

	public boolean checkUsernameAvailable(String name) {
		return !users.containsKey(name);
	}

	public User addUser(String name, String password) {
		User user = new User(name, password);
		users.put(name, user);
		
		return user;
	}

	public String summarizeAllUsers() {

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2;
		
		for(User u : users.values()) {
			sb2 =new StringBuilder();
			sb2.append(u.getName());
			
			if(u.isAdmin())
				sb2.append("(A)");
			
			while(sb2.length() < 15)
				sb2.append(" ");
			
			sb2.append(Account.formatCurrency(u.totalBalance()));
			sb2.append('\n');
			
			sb1.append(sb2.toString());
		}
		
		return sb1.toString();
	}
}
