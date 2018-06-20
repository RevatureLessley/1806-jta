import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 * 
 *        Maintains collection of users.
 */
public class UserController {

	private HashMap<String, User> users;
	private ArrayList<User> unvalidatedUsers;

	public UserController() {
		users = new HashMap<String, User>();
		unvalidatedUsers = new ArrayList<User>();
		User admin = addUser("Admin","pass123");
		admin.setAdmin(true);
		validateNewUser(admin);
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
		unvalidatedUsers.add(user);
		
		return user;
	}
	
	public void validateNewUser(User user) {
		user.validateNewUser();
		unvalidatedUsers.remove(user);	
	}
	
	public int getWaitUserCount() {
		return unvalidatedUsers.size();
	}
	
	public User getWaitUser(int i) {
		return unvalidatedUsers.get(i);
	}
	
	public String[] getWaitUserNames() {
		String[] names = new String[unvalidatedUsers.size()];
		int i = 0;
		
		for(User u : unvalidatedUsers) {
			names[i++] = u.getName();
		}
		
		return names;
	}
}
