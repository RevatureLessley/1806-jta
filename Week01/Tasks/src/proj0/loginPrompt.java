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
		this.checkPassword(user);
		
		
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
	
	public void checkPassword(User user) {
		if (user != null){
			boolean passaccepted = false;
			while(!passaccepted) {
				String password = new String(console.readPassword("Password: "));
				passaccepted = user.confirmPassword(password);
				if(passaccepted == false) System.out.println("Invalid password");
			}
			
		}else {
			
		}
	}
	
	public void createUser() {
		
	}
	
}