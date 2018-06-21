package proj0;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersAndAdmins implements Serializable{
	
	/**
	 * This is the serial UID for UsersAndAdmins
	 */
	private static final long serialVersionUID = 2183647921267415711L;
	
	private ArrayList<User> users;
	private ArrayList<Admin> admins;
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void removeUser(User user) {
		this.users.remove(user);
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public ArrayList<Admin> getAdmins() {
		return admins;
	}
	public void setAdmins(ArrayList<Admin> admins) {
		this.admins = admins;
	}
	
	

}
