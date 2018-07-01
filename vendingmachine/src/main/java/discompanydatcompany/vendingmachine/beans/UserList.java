package discompanydatcompany.vendingmachine.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	public List<User> getUserByName(String username) {
		ArrayList<User> users = new ArrayList<User>();
		
		for (User user : userList.values()) {
			if (user.getName().equals(username)) {
				users.add(user);
			}
		}
		
		if (users.size() == 0) {
			return null;
		}
		
		return users;
	}
	
	public List<User> userIdStartsWith(String key) {
		ArrayList<User> result = new ArrayList<User>();
				
		if (key.length() <  32 && key.length() > 1) {
			String[] uuids = (String[]) userList.keySet().toArray();
			for (String uuid : uuids) {
				if (uuid.startsWith(key)) {
					result.add(this.getUser(uuid));
				}
			}
			return result;
		}
		
		return null;
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
	
	public List<User> getUsersAtVendingMachine(String vendingMachineUUID) {
		ArrayList<User> result = new ArrayList<User>();
		
		for (User user : userList.values()) {
			if (user.getLocation().equals(vendingMachineUUID)) {
				result.add(user);
			}
		}
		
		if (result.size() == 0) {
			return null;
		}
		
		return result;
	}
	
	public List<User> getInactiveUsers() {
		List<User> users = new ArrayList<User>();
		
		for (User user : userList.values()) {
			if(!user.getEnabled()) {
				users.add(user);
			}
		}
		
		if (users.size() == 0) {
			return null;
		} else {
			return users;
		}
	}
	
	public int size() {
		return this.userList.size();
	}
}
