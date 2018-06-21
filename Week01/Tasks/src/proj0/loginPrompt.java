package proj0;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Console;

public class loginPrompt{
	
	static Console console = System.console();
	/**
	* Prompts the user for their login info and either asks for their password or
	* creates a new account if the account doesn't exist yet
	* @return None
	*/
	public void inputLogin(){
		Users users = this.retrieveUsers();
		String username = console.readLine("Username: ");
		User user = users.getUsers().get(username);
		this.checkPassword(user,username);
		
		
		//System.out.print(username + " " + password);
	}
	/**
	 * This method is used to retrieve previously stored user data
	 * @return Users
	 */
	public Users retrieveUsers() {
		Users u = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("users.ser"));
			u = (Users)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	/**
	 * This method checks if a valid password is entered for an existing order
	 * If the user does not exist, then a new user is created
	 * @param user
	 * user data containing their info
	 * @param username
	 * username to be cheked or created
	 */
	public void checkPassword(User user,String username) {
		if (user != null){
			boolean passaccepted = false;
			while(!passaccepted) {
				String password = new String(console.readPassword("Password: "));
				passaccepted = user.confirmPassword(password);
				if(passaccepted == false) System.out.println("Invalid password");
			}
			
		}else {
			System.out.println("User not found, enter '0' to create an account or '1' to "
					+ "enter a different username.");
			String input = console.readLine(": ");
			if(input.equals("0")) this.createUser(username);
			else{
				this.inputLogin();
			}
		}
	}
	/**
	 * 
	 * @param username
	 * username to be created
	 * @return User
	 * returns a new User
	 */
	public User createUser(String username) {
		boolean checkpass = false;
		String password = null;
		while(!checkpass) {
			String newpass = new String(console.readPassword("Create Password: "));
			String confirmpass = new String(console.readPassword("Create Password: "));
			checkpass = newpass.equals(confirmpass);
			if(checkpass) {
				password = newpass;
				break;
			}
			System.out.println("Passwords do not match");
		}
		String fname = new String(console.readLine("First Name: "));
		String lname = new String(console.readLine("Last Name: "));
		User user = new User(username,password,fname,lname);
		return user;
	}
	
}