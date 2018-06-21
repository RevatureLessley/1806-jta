import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver {
	
	private static Map<String, User> users;
	private static Map<String, User> unAppUsers;

	public static void main(String[] args) {	
		Account adminAccount = new Account();
		users = new HashMap<>();
		User admin = new User("admin", "password", adminAccount,true,true);
		unAppUsers = new HashMap<>();
		users.put(admin.getUserName(), admin);
		run();
	}
	
	public static void run() {	
		welcome();
		
		boolean finished = false;
		while(!finished) {
			Scanner in = new Scanner(System.in);
			
			String input = in.nextLine().toLowerCase();
			while(!(input.equals("register") || input.equals("login"))) {
				System.out.println("Invalid command. To login type 'login'. To register type 'register' ");
				input = in.nextLine().toLowerCase();
			}
			
			if(input.toLowerCase().equals("register")){
				Account acc = new Account();
				
				System.out.print("Please enter username: ");
				String uName = in.nextLine();
				System.out.print("Please enter password: ");
				String pass = in.nextLine();
				
				User u = new User(uName,pass,acc,false,false);
				users.put(u.getUserName(),u);
				unAppUsers.put(uName, u);
				
				if(!u.isApproved()) {
					System.out.println("An administrator will approve your account");
					break;
				}
					
				
				System.out.println("Congratulations you have created a new account");
				System.out.println("Your current balance is $" + u.getUserBalance());
				System.out.println();
				
				
				while(true) {
					System.out.println("Please select from the following commands:");
					System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
					
					String command = in.nextLine().toLowerCase();
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
						finished = true;
						break;
					}
					else {
						System.out.println("Not a valid command");
					}
					System.out.println();
				}						
				
			}
			else if(input.toLowerCase().equals("login")) {
				System.out.print("Please enter username: ");
				String uName = in.nextLine();
				
				while(!users.containsKey(uName)) {
					System.out.println("Username not found. Please try again");
					System.out.print("Please enter username: ");
					 uName = in.nextLine();
				}
				if(users.containsKey(uName) && users.get(uName).isApproved() && !users.get(uName).isAdmin()){
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(users.get(uName).getPassword())) {
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}
				}
				else if(users.containsKey(uName) && users.get(uName).isAdmin()) {
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(users.get(uName).getPassword())) {
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}
					while(true) {
						System.out.println("Accounts awating approval " + unAppUsers.size());
						System.out.println("To approve users, type 'Approve'");
						
						String command = in.nextLine().toLowerCase();
						if(command.equals("approve") && !unAppUsers.isEmpty()) {
							for(String userName: unAppUsers.keySet()) {
								System.out.println(userName);
							}
						}
					}
				}
				else {
					System.out.println("Sorry your account has not been approved");
					break;
					}
				User u = users.get(uName);				
				System.out.println("Welcome back");
				System.out.println("Your balance is $" + u.getUserBalance());
				
				System.out.println();
				
			
				while(true) {
					System.out.println("Please select from the following commands:");
					System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
					
					String command = in.nextLine().toLowerCase();
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
						finished = true;
						break;
					}
					else {
						System.out.println("Not a valid command");
					}
					System.out.println();
					}	
					
				}
			}
		run();	
		}
	
		

	
	public static void welcome() {
		System.out.println("Welcome");
		System.out.println("To login type 'login'. To register type 'register'" );
	}

}
