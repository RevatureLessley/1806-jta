import java.util.HashMap;
import java.util.Map;

public class UserCollection {
	private Map<String, User> users;
	
	public UserCollection() {
		users = new HashMap<>();
	}
	
	public boolean containsKey(String userName) {
		return users.containsKey(userName);
	}
	
	public void addUser(User newUser) {
		users.put(newUser.getUserName(), newUser);
	}
	
	public void getUser(User user) {
		users.get(user.getUserName());
	}
	

}
