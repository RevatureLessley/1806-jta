import java.util.ArrayList;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class User {

	private ArrayList<Account> accounts;
	private String name;
	private String password;
	private boolean validated = false;
	private boolean admin = false;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public void addAccount() {
		
	}
	
	public void validateNewUser() {
		System.out.println(name + " validated");
		validated = true;
	}
	
	public boolean validateLogin(String password) {
		if(!validated)
			return false;
		if(password.equals(this.password))
			return true;
		
		return false;
	}
	

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
