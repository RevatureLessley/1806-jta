package p0;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Gambling implements Serializable{

	private ArrayList<User> Users = new ArrayList<User>(0);
	private String path;
	static Scanner userInput;
	
	public Gambling readGame() {
			Gambling game = null;
		try {
			FileInputStream fIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fIn);
			game = (Gambling) in.readObject();
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
	
	public void writeGame(Gambling game) {
		try {
			FileOutputStream fOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(game);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public Gambling(String path) {
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
	outer:
		while(true) {
			System.out.println("Please choose an option:");
			
			if (curUser.getRole()==0) {
				 // admin
				 System.out.print("	1: gamble\n	2: print balance\n	3: deposit\n	4: withdraw\n	5: approve an account\n	anything else: go back to start\n");
			} else {
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
						System.out.println("Enter an amount to deposit, it will be ronded to two decimal points:");
						input = userInput.nextLine();	
					double deposit = 0;
						try {
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
					
				case "4":
					while(true) {
						System.out.println("Your balance is: $" + curUser.getBalance());
						System.out.println("Enter an amount to withdraw, it will be rounded to two decimal points:");
						input = userInput.nextLine();	
						double withdrawal = 0;
							try {
								withdrawal = (double)Math.round(Double.parseDouble((input)) * 100d) / 100d;
								
								if (withdrawal <= 0) {
									System.out.println("Please enter a positive value.");
									continue;
								} else if (withdrawal > curUser.getBalance()) {
									System.out.println("You don't have that much.");
									continue;
								}
							} catch (Exception e){
								System.out.println("Please enter a double. ");
								continue;
							}
							curUser.setBalance(curUser.getBalance()-withdrawal);
							System.out.println("You just withdrew $" + withdrawal + ", your balance is now $" + curUser.getBalance());
						break;
						}
					
				case "5":
					if (curUser.getRole()==0) {
						while(true) {
							System.out.println("Enter the username of the account to approve: ");
							input = userInput.nextLine();
							int approvingUserNum = userExists(input);
							if (approvingUserNum==-1) {
								System.out.println("That user does not exist. Try again?\n	1: yes\n	anything else: no");
							} else {
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
					
				default: break outer;
			
			}
		}
		start();
	}
	
	public void gamble(User curUser) {
		System.out.println("gambling");
		curUser.setBalance(curUser.getBalance()+1);
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
		Gambling g = new Gambling("game.ser");
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
