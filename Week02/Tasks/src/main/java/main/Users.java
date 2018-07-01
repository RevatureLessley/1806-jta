//This class was used when storing with serializable

package main;

import java.io.Serializable;
import java.util.HashMap;


public class Users implements Serializable{
	
	//SerialUID for Users
	private static final long serialVersionUID = -178098286672692870L;
	
	private HashMap<String, User> users;

	/**
	 * Setup the Hashmap which contains the users
	 */
	public Users() {
		this.users = new HashMap();
		
		//Use commented out lines on the first run to make the admin account
		/*Admin adm = new Admin("account","password","Andrew","Hwang");
		adm.setAuth(true);
		this.users.put("account",adm);*/
	}
	
	/**
	 * Add a user to the hash map
	 * @param user
	 * User to put into the hash map
	 */
	public void addUser(User user) {
		this.users.put(user.getUserid(), user);
	}
	
	/**
	 * Remove a user from the hash map
	 * @param user
	 * User to remove from the hash map
	 */
	public void removeUser(User user) {
		users.remove(user.getUserid());
	}
	
	//Getters and Setters
	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}
}
