import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 * 
 *        Maintains collection of users.
 */
public class UserController {

	private HashMap<String, User> users;
	private LinkedList<User> unvalidatedUsers;

	public UserController() {
		users = new HashMap<String, User>();
		unvalidatedUsers = new LinkedList<User>();
		User admin = addUser("Admin","pass123");
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

	public void banUser() {
		
		
	}
}
