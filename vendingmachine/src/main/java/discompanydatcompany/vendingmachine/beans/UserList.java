package discompanydatcompany.vendingmachine.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class UserList implements Serializable {
	
	private HashMap<String, User> userList;
	
	public UserList() {
		this.userList = new HashMap<String, User>();
	}
	
	public void addUser(User user) {
		if (user == null) {
			return;
		} else {
			this.userList.put(user.getLoginUUID(), user);
		}
	}
	
	public User getUser(String key) {
		if (this.userList.containsKey(key)){
			return this.userList.get(key);
		} else {
			return null;
		}
	}
	
	public User getUserCredentials(String user, String password) {
		HashMap<String, User> keyRing = new HashMap<String, User>();
		
		if (user == null || password == null) {
			return null;
		}
		
		for (User u : this.userList.values()) {
			keyRing.put(u.getName(), u);
		}
		
		if (keyRing.containsKey(user) && keyRing.get(user).getPassword().equals(password)) {
			return keyRing.get(user);
		}
		
		return null;
	}
	
	public int size() {
		return this.userList.size();
	}
}
