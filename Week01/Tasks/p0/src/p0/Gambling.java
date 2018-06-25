package p0;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;

/** Class for a simple gambling system
 * @author Nate
 *
 */

public class Gambling implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<User> Users = new ArrayList<User>(0);
	private String path;
	static Scanner userInput;
	final static Logger logger = Logger.getLogger(Gambling.class);
	
	/** Method to read game from path defined in referenced class
	 * @return game: Game object read from file
	 */
	public Gambling readGame() {
		logger.info("reading in a game file");
			Gambling game = null;
		try {
			FileInputStream fIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fIn);
			game = (Gambling) in.readObject();
			in.close();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Unexpected behavior");
			e.printStackTrace();
			return null;
		}
		logger.info("read finished");
		return game;
	}
	
	/** Method to write game to path defined in reference object
	 * 
	 */
	public void writeGame() {
		logger.info("writing a game file");
		try {
			FileOutputStream fOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		logger.info("write finished");
	}
	
	/** Constructor for Gambling object, initializes scanner and path
	 *  as well as adding an admin account
	 * @param path: file path to use for this object
	 */
	public Gambling(String path) {
		super();
		logger.info("constructing game object");
		Users.add(new User("admin","securepassword",0));
		userInput = new Scanner(System.in);
		this.path = path;
		
		/*this.path = path;
		Gambling g = readGame();
		if (g.userExists("admin")==-1) Users.add(new User("admin","securepassword",0));
		this.Users = g.Users;
		final Gambling passThis = this;
		userInput = new Scanner(System.in);
		System.out.println("aaa");
		
		Runtime.getRuntime().addShutdownHook(
				  new Thread() {
				    @Override 
				    public void run() { 
				      passThis.writeGame(); 
				    }
				});*/
	}
	
	/** Method to check if user exists in current object
	 * @param username: username to check for
	 * @return -1 if user does not exist, index of user in Users if exists
	 */
	public int userExists(String username) {
			logger.info("checking users for " + username);
			int i=0;
			for (User user : Users) {
				if (user.accountName.equals(username)) return i;
				i++;
			}
			logger.info(username + " not found");
			return -1;
		}
	
	/** Menu method to choose create account, login, or exit program
	 * 
	 */
	public void start() {
		logger.info("entered start menu");
		while(true) {
			System.out.print("Please select an option:\n	1: login\n	2: create account\n	anything else: quit\n");
			String input = userInput.nextLine();
			if (input.equals("1")) {
				login();
			}
			else if (input.equals("2")) {
				create();
			}
			else break;
		}
	}
	
	
	/** Menu method giving options for a logged in user
	 * @param userNumber: user that has successfully logged in
	 */
	public void loggedIn(int userNumber) {
		logger.info(Users.get(userNumber).accountName + "logged in succesfully, choosing next action");
		User curUser = Users.get(userNumber);
	outer:
		while(true) {
			System.out.println("Please choose an option:");
			
			if (curUser.getRole()==0) {
				 // admin options
				 System.out.print("	1: gamble\n	2: print balance\n	3: deposit\n	4: withdraw\n	5: approve an account\n	6: ban an account\n	anything else: go back to start\n");
			} else {
				 // normie options
				 System.out.print("	1: gamble\n	2: print balance\n	3: deposit\n	4: withdraw\n	anything else: go back to start\n");
			}
			String input = userInput.nextLine();
			switch(input) {
				case "1":
					gamble(curUser);
					break;
					
				case "2":
					System.out.println("Your balance is: $" + curUser.getBalance());
					break;
					
				case "3":
					while(true) {
						System.out.println("Your balance is: $" + curUser.getBalance());	
						System.out.println("Enter an amount to deposit, it will be rounded to two decimal points:");
						input = userInput.nextLine();	
					double deposit = 0;
						try {
						// rounds to 2 decimal places
						deposit = (double)Math.round(Double.parseDouble((input)) * 100d) / 100d;
						if (deposit <= 0) {
							System.out.println("Please enter a positive value.");
							continue;
						}
						} catch (Exception e){
							System.out.println("Please enter a double. ");
							continue;
						}
						curUser.setBalance(curUser.getBalance()+deposit);
						System.out.println("You just deposited $" + deposit + ", your balance is now $" + curUser.getBalance());
					break;
					}
					break;
					
				case "4":
					while(true) {
						System.out.println("Your balance is: $" + curUser.getBalance());
						System.out.println("Enter an amount to withdraw, it will be rounded to two decimal points:");
						input = userInput.nextLine();	
						double withdrawal = verifyWithdrawal(input, curUser);
						if (withdrawal==-1) continue;
							curUser.setBalance(curUser.getBalance()-withdrawal);
							System.out.println("You just withdrew $" + withdrawal + ", your balance is now $" + curUser.getBalance());
						break;
						}
					break;
					
				case "5":
					// admin ONLY
					if (curUser.getRole()==0) {
						while(true) {
							System.out.println("Enter the username of the account to approve: ");
							input = userInput.nextLine();
							int approvingUserNum = userExists(input);
							if (approvingUserNum==-1) {
								System.out.println("That user does not exist. Try again?\n	1: yes\n	anything else: no");
							} else {
								// role 1 = approved normal user
								Users.get(approvingUserNum).setRole(1);
								System.out.println("User approved. Approve another?\n	1: yes\n	anything else: no");
							}
							input = userInput.nextLine();
							if (input=="1") continue;
							else break;
						}
						break;
					}
					else break outer;
					
				case "6":
					// admin ONLY
					if (curUser.getRole()==0) {
						while(true) {
							System.out.println("Enter the username of the account to ban: ");
							input = userInput.nextLine();
							int approvingUserNum = userExists(input);
							if (approvingUserNum==-1) {
								System.out.println("That user does not exist. Try again?\n	1: yes\n	anything else: no");
							} else {
								// role -2 = banned user
								Users.get(approvingUserNum).setRole(-2);
								System.out.println("User banned >=). Ban another?\n	1: yes\n	anything else: no");
							}
							input = userInput.nextLine();
							if (input=="1") continue;
							else break;
						}
						break;
					}
					else break outer;
					
				default: break outer;
			
			}
		}
	}
	
	public double verifyWithdrawal(String input, User curUser) {
			double withdrawal = 0;
		try {
			withdrawal = (double)Math.round(Double.parseDouble((input)) * 100d) / 100d;
			
			if (withdrawal <= 0) {
				System.out.println("Please enter a positive value.");
				return -1;
			} else if (withdrawal > curUser.getBalance()) {
				System.out.println("You don't have that much.");
				return -1;
			}
		} catch (Exception e){
			System.out.println("Please enter a double. ");
			return -1;
		}
		return withdrawal;
	}
	
	/** Method implementing a simple gambling game
	 * @param curUser: User who is gambling
	 */
	public void gamble(User curUser) {
		logger.info("user has started to gamble");
		while(true) {
			System.out.println("This is a basic gambling game. There will be a number from 1-100 generated, and you win if it rolls over 50!");
			System.out.println("Your balance is: $" + curUser.getBalance());
			System.out.println("Please enter an amount to bet:");
			String input = userInput.nextLine();	
			double bet = verifyWithdrawal(input, curUser);
			if (bet==-1) continue;

				// random integer from 1 to 100
				int rand = new Random().nextInt(100)+1;
				if (rand > 50) {
					System.out.println("You rolled a " + rand + ". You win!");
					curUser.setBalance(curUser.getBalance()+bet);
					System.out.println("You just won $" + bet + ", your balance is now $" + curUser.getBalance());
				}
				else {
					System.out.println("You rolled a " + rand + ". You lose :c");
					curUser.setBalance(curUser.getBalance()-bet);
					System.out.println("You just lost $" + bet + ", your balance is now $" + curUser.getBalance());
				}

				System.out.print("Please choose an option\n	1: gamble again\n	anything else: go back to start\n");
				input = userInput.nextLine();
				if (input.equals("1")) continue;
				else break;
			}
	}
	
	/** Method allowing user to login
	 * 
	 */
	public void login() {
		logger.info("attempting to login");
		 while(true) {
			 System.out.println("Please enter your username: ");
			 String input = userInput.nextLine();
			 int userNumber = userExists(input);
			 if (userNumber!=-1) {
				 // all new users start with -1, need to be approved to be a normal user (role 1)
				 if (Users.get(userNumber).getRole()==-1) {
					 System.out.println("Sorry, you are not yet approved to login. Please wait for admin approval."); 
				 } else if (Users.get(userNumber).getRole()==-2) {
					 System.out.println("You're banned! Get outta here."); 
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
	
	/** Method to add a new user to Users
	 * 
	 */
	public void create() {
		logger.info("attempting to create a new user");
		 while(true) {
			 System.out.println("Please enter your desired username: ");
			 String input = userInput.nextLine();
			 // can only create if user doesn't already exist
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
		System.out.println("Warning: terminating this program unnaturally will not save any updates.");
		Gambling g = new Gambling("game.ser");
		try {
			g.Users = g.readGame().Users;
		} catch (Exception e){
			logger.info("File not found, a new file will be created");
		}
		
		System.out.println(g);
		g.start();
		g.writeGame();
		
	}
	

}
