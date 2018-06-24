import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {
	
	private static Scanner in;
	private static Map<String, User> users;
	private static Map<String, User> unAppUsers;
	private static List<Map> list; 

	public static void main(String[] args) {
		list = new ArrayList<>();
		
		File f = new File("users.ser");
		
		if(f.exists()) {
			users = deserializeUsers().get(0);
			unAppUsers = deserializeUsers().get(1);
		}
		else {
			Account adminAccount = new Account();
			users = new HashMap<>();
			User admin = new User("admin", "password", adminAccount,true,true);
			unAppUsers = new HashMap<>();
			users.put(admin.getUserName(), admin);
		}			
		
		in = new Scanner(System.in);		

		run();
		list.add(users);
		list.add(unAppUsers);
		serialize();
		in.close();
	}
	
	public static void run() {	
		welcome();
		
		while(true) {					
			String input = in.nextLine().toLowerCase();
			while(!(input.equals("register") || input.equals("login"))) {
				System.out.println("Invalid command. To login type 'login'. To register type 'register' ");
				input = in.nextLine().toLowerCase();
			}
			
			if(input.toLowerCase().equals("register")){
				register();
				break;
					
			}
			else if(input.toLowerCase().equals("login")) {
				System.out.print("Please enter username: ");
				String uName = in.nextLine();
				
				while(!users.containsKey(uName)) {
					System.out.println("Username not found. Please try again");
					System.out.print("Please enter username: ");
					 uName = in.nextLine();
				}
				if(users.get(uName).isApproved() && !users.get(uName).isAdmin()){
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(users.get(uName).getPassword())) {
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}
				}
				else if(!users.get(uName).isApproved()) {
					System.out.println("Sorry your account has not been approved");
					System.out.println();
					break;
					}
				
				else if(users.get(uName).isAdmin()) {
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(users.get(uName).getPassword())) {
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}
					
					User admin = users.get(uName);
					System.out.println("Welcome");
					System.out.println("Your balance is $" + admin.getUserBalance());						
					System.out.println();
					
					boolean activeAdmin = true;
					while(activeAdmin) {
						int unAppUserSize = unAppUsers.size();
						System.out.println("Accounts awating approval " + unAppUserSize);
						System.out.println("To approve users, type 'Approve', OR " 
								+  "Select from the following commands");		
						System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
						System.out.println();
						
						String command = in.nextLine().toLowerCase();
						activeAdmin = adminMenu(command, admin);
					}
					break;
				}
				
				User u = users.get(uName);				
				System.out.println("Welcome");
				System.out.println("Your balance is $" + u.getUserBalance());
				
				System.out.println();
				
				
				boolean activeUser = true;
				while(activeUser) {
					System.out.println("Please select from the following commands:");
					System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
					
					String command = in.nextLine().toLowerCase();
					activeUser = userMenu(command, u);
					
					}	
				break;
				}
			}
		}
	
		

	
	public static void welcome() {
		System.out.println("Welcome");
		System.out.println("To login type 'login'. To register type 'register'" );
	}
	
	public static void register() {
		Account acc = new Account();
		
		System.out.print("Please enter username: ");
		String uName = in.nextLine();
		System.out.print("Please enter password: ");
		String pass = in.nextLine();
		
		User u = new User(uName,pass,acc,false,false);
		users.put(u.getUserName(),u);
		unAppUsers.put(uName, u);
		
		System.out.println("Thank you for registering.");
		System.out.println("An administrator will approve your account");
		System.out.println();		
	}

	
	public static boolean userMenu(String command, User u) {
		boolean active = true;
		
		
		if(command.equals("deposit")) {
			System.out.print("How much would you like to deposit? $");
			int amount = Integer.parseInt(in.nextLine());
			System.out.println("Balance after deposit $" + u.getAccount().deposit(amount));
		}
		else if(command.equals("withdraw")) {
			System.out.print("How much would you like to withdraw? $");
			int amount = Integer.parseInt(in.nextLine());
			System.out.println("Balance after withdrawal $" + u.getAccount().withdrawal(amount));
		}
		else if(command.equals("balance")) {
			System.out.println("Current balance $" + u.getUserBalance());
		}
		else if(command.equals("logout")) {
			active = false;
		}
		else {
			System.out.println("Not a valid command");
		}
		System.out.println();
		return active;
	}
	
	public static boolean adminMenu(String command, User u) {
		boolean active = true;
		
		
		if(command.equals("approve") && !unAppUsers.isEmpty()) {
			System.out.println("Accounts awaiting approval:");
			for(String userName: unAppUsers.keySet()) {
				System.out.println(userName);								
			}
			System.out.println();
			System.out.println("Select User to Approve");
			String user = in.nextLine();
			if(users.containsKey(user)){
				users.get(user).setApproved(true);
				unAppUsers.remove(user);
				System.out.println(user + " Approved");
				System.out.println();
			}								
		}
		else if(command.equals("approve") && unAppUsers.isEmpty()) {
			System.out.println("No users to approve");
			System.out.println();
		}
		else {
			active = userMenu(command, u);
		}
		return active;
		
	}
	
	
	
	public static void serialize() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"));
			oos.writeObject(list);			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
//	@SuppressWarnings("unchecked")
	public static List<Map> deserializeUsers() {
		List<Map> users = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"));
			users = (List<Map>)ois.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
		
	}

		
	

}
