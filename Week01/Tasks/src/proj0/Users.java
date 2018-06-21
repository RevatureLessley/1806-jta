package proj0;

import java.io.Serializable;
import java.util.HashMap;

public class Users {
	private HashMap<String, User> users;
	
	
	public void addUser(User user) {
		users.put(user.getUserid(), user);
	}
	

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
