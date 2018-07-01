package main;


import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import util.Connections;
import static util.CloseStreams.close;

public class LoginPrompt{
	//logger for loginprompt
	static final Logger logger = Logger.getLogger(LoginPrompt.class);
	
	//console for user input
	static Console console = System.console();
	
	/**
	* Prompts the user for their login info and either asks for their password or
	* creates a new account if the account doesn't exist yet
	* 
	* @return None
	*/
	public User enterLogin(){
		String username = console.readLine("Username: ");
		User user = this.retrieveUserDB(username);
		//User user = users.getUsers().get(username);
		user = this.checkPassword(user,username);
		if (user == null) return null;
		//users.addUser(user);
		//this.storeUsers(users);
		System.out.println(user.toString());
		if (!user.isAuth()) {
			System.out.println("Your account is not approved yet, please wait 0 or more years");
			return null;
		}
		return user;
		
	}

	public User retrieveUserDB(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try (Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM aggregate_data WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) return null;
			rs.beforeFirst();
			
			while(rs.next()) {
				return new User(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDouble(5),
						rs.getInt(6));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps);
			close(rs);
		}
		return null;
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
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	/**
	 * Uses serialization to store user data
	 * @param users
	 * class that contains hash map with all of the users
	 */
	public void storeUsers(Users users) {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("users.ser"));
			oos.writeObject(users); //Serialize
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if a valid password is entered for an existing order
	 * If the user does not exist, then a new user is created
	 * @param user
	 * user data containing their info
	 * @param username
	 * username to be checked or created
	 */
	public User checkPassword(User user,String username) {
		if (user != null){
			boolean passaccepted = false;
			while(!passaccepted) {
				String password = new String(console.readPassword("Password: "));
				passaccepted = user.confirmPassword(password);
				if(passaccepted == false) System.out.println("Invalid password");
			}
			
		}else {
			System.out.println("User not found, enter '0' to create an account or '1' to "
					+ "exit.");
			String input = console.readLine(": ");
			if(input.equals("0")) user = this.createUser(username);
			else{
				return null;
			}
		}
		return user;
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
			String confirmpass = new String(console.readPassword("Confirm Password: "));
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
		logger.info(user.toString() + " Created an account");
		return user;
	}
	
}