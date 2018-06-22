package p0;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class GamblingMatch implements Serializable{

	private ArrayList<User> Users = new ArrayList<User>(0);
	private String path;
	static Scanner userInput;
	
	public GamblingMatch readGame() {
			GamblingMatch game = null;
		try {
			FileInputStream fIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fIn);
			game = (GamblingMatch) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Unexpected behavior");
			e.printStackTrace();
			return null;
		}
		
		return game;
	}
	
	public void writeGame(GamblingMatch game) {
		try {
			FileOutputStream fOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(game);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public GamblingMatch(String path) {
		super();
		Users.add(new User("admin","securepassword",0));
		userInput = new Scanner(System.in);
		this.path = path;
	}
	
	public int userExists(String username) {
			int i=0;
			for (User user : Users) {
				if (user.accountName.equals(username)) return i;
				i++;
			}
			return -1;
		}
	
	public void start() {
		while(true) {
			System.out.print("Please select an option:\n	1: login\n	2: create account\n	3: quit\n");
			String input = userInput.nextLine();
			if (input.equals("1")) {
				login();
			}
			else if (input.equals("2")) {
				create();
			}
			else if (input.equals("3")) break;
			else System.out.println("Enter only 1,2 or 3");
		}
	}
	
	public void loggedIn(int userNumber) {
		User curUser = Users.get(userNumber);
		System.out.println(curUser);
		while(true) {
			
			if (curUser.getRole()==0) {
			// admin menu
				
			} else {
			// normie menu	
				
			}
		}
	}
	
	public void login() {
		 while(true) {
			 System.out.println("Please enter your username: ");
			 String input = userInput.nextLine();
			 int userNumber = userExists(input);
			 if (userNumber!=-1) {
				 if (Users.get(userNumber).getRole()==-1) {
					 System.out.println("Sorry, you are not yet approved to login. Please wait for admin approval."); 
				 } else {
					 System.out.println("Please enter your password: "); 
					 if (userInput.nextLine().equals(Users.get(userNumber).getPassword())) {
						 loggedIn(userNumber);
						 break;
					 } else System.out.println("Incorrect password.");
				 }
			 } else {
				 System.out.println("That user does not exist");
			 }
			 System.out.print("Please choose an option\n	1: try to login again\n	anything else: go back to start\n");
			 input = userInput.nextLine();
			 if (input.equals("1")) continue;
			 else break;
		 }
	}	
	
	/*public void menu() {
		
	}*/
	
	public void create() {
		 while(true) {
			 System.out.println("Please enter your desired username: ");
			 String input = userInput.nextLine();
			 if (userExists(input)==-1) {
				 System.out.println("Please enter your desired password: ");
				 Users.add(new User(input,userInput.nextLine(),-1));
				 System.out.print("Your account has been created, but still needs to be approved by an admin before you login.\n	1: create another account\n	anything else: go back to start\n");
			 } else {
				 System.out.print("That username is taken, please choose an option\n	1: try a new username\n	anything else: go back to start\n");
			 }
			 input = userInput.nextLine();
			 if (input.equals("1")) continue;
			 else break;
		 }
	}
	
	@Override
	public String toString() {
		return "GamblingMatch [Users=" + Users + "]";
	}

	public static void main(String[] args) {
		GamblingMatch g = new GamblingMatch("game.ser");
		/*g.Users.add(new User("aa","asdf",1));
		g.Users.add(new User("ac","qwer",2));
		g.Users.add(new User("ba","ily",1));
		g.Users.add(new User("za","btbttbdf",60));
		g.writeGame(g);
		GamblingMatch a = g.readGame();
		System.out.println(a);*/
		
		g.start();
		
	}
	

}
