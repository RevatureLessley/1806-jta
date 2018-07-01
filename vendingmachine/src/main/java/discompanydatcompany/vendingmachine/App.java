package discompanydatcompany.vendingmachine;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import discompanydatcompany.vendingmachine.beans.Admin;
import discompanydatcompany.vendingmachine.beans.BottledWater;
import discompanydatcompany.vendingmachine.beans.Gum;
import discompanydatcompany.vendingmachine.beans.Item;
import discompanydatcompany.vendingmachine.beans.MysteriousPocketPortalMachine;
import discompanydatcompany.vendingmachine.beans.Snack;
import discompanydatcompany.vendingmachine.beans.StockItem;
import discompanydatcompany.vendingmachine.beans.User;
import discompanydatcompany.vendingmachine.beans.UserList;
import discompanydatcompany.vendingmachine.beans.VendingMachine;
import discompanydatcompany.vendingmachine.beans.VendingMachineList;
import discompanydatcompany.vendingmachine.utilities.Container;
import discompanydatcompany.vendingmachine.utilities.Input;
import discompanydatcompany.vendingmachine.utilities.Printing;
import discompanydatcompany.vendingmachine.utilities.SaveFile;

public class App {
	// private static Logger logger = LoggerFactory.getLogger("discompanydatcompany.vendingmachine.App");
	
	public static final String SAVE_FILE_NAME = "DATSAV";
    private boolean isSaveFilePresent = false;
    
    
    public String getGreeting() {
        return "\"Hello world.\" -- Feral Vending Machine (No Owner)";
    }

    public static void main(String[] args) {
    	String scannerInput = "";
    	Scanner scanner = new Scanner(System.in);
    	App app = new App();
    	Input input = new Input();
    	SaveFile save = new SaveFile();
    	VendingMachine vend;
    	VendingMachineList vendingMachineList;
    	UserList userList;
    	User activeUser;
    	
    	// logger.info("LOGGER");
    	
    	if (new File(SAVE_FILE_NAME).isFile()) {
    		save.loadFromFile(SAVE_FILE_NAME);
    		vendingMachineList = save.getVendingMachineList();
    		userList = save.getUserList();
    		
    		if (vendingMachineList != null || userList != null || vendingMachineList.size() != 0 || userList.size() != 0) {
    			app.isSaveFilePresent = true;
    		}
    	}
    	
    	// No save is present
    	if (!app.isSaveFilePresent) {
    		String name ="";
    		String password = "";
    		String aboutMe = "";
    		
    		vend = new VendingMachine("Feral Vending Machine", "No Owner", "" ,"An arbitrary forest in Northern New England. This vending machine is comfortably tucked under a rocky alcove. 1368 A.D.");
    		System.out.println(vend.toString());
    		System.out.println(app.getGreeting());
    		System.out.println("You are Admin. You have no choice. -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		System.out.println("What's your name -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		
    		while(name == null || name == "") {
    			name = scanner.nextLine();
    		}
    		
    		System.out.println("Tell me something about yourself. -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		aboutMe = scanner.nextLine();
    		
    		System.out.println("Enter a password and tell me no lies. -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		
    		while (password == null || password == "") {
    			password = scanner.nextLine();
    		}
    		
    		Admin admin = new Admin(name, password, aboutMe);
    		vend.setAdminName(admin.getName());
    		
    		vendingMachineList = new VendingMachineList();
    		vendingMachineList.addVendingMachine(vend);
    		
    		userList = new UserList();
    		userList.addUser(admin);  
    		
    		admin.setLocation(vend.getVendingMachineId());
    		
    		save.setUserList(userList);
    		save.setVendingMachineList(vendingMachineList);
    		
    		try {
				save.writeToFile(SAVE_FILE_NAME);
			} catch (IOException e) {
				System.out.println("Failed to save!");
				e.printStackTrace();
			}
    		
    		activeUser = admin;
    		// System.out.println("User: " + activeUser.getName());
    		// System.out.println("User location: " + activeUser.getLocation() );
    		
    	
    	} else {
    		// Save is present
    		save.loadFromFile(SAVE_FILE_NAME);
    		String password = "";
    		String username = "";
    		String aboutMe = "";
    		HashMap<String, String> keyRing = new HashMap<String, String>();
    		
    		// retrieve save data 
    		userList = save.getUserList();
    		vendingMachineList = save.getVendingMachineList();
    		
    		// login
    		User loginSuccess = null;
    		System.out.println("New user? Enter \"Y\" if yes.");
    		String newUserResponse = new String(scanner.nextLine()).toUpperCase();
    		
    		if (newUserResponse == "Y" || newUserResponse == "YES") {
    			while (username == "" || username == null) {
    				System.out.println("Please enter a username");
    				username = scanner.nextLine();
    				System.out.println(username);
    			}
    			
    			System.out.println("Tell me something about yourself.");
    			aboutMe = scanner.nextLine();
    			
    			while (password == "" || password == null) {
    				System.out.println("Please enter a password");
    				password = scanner.nextLine();
    			}
    		}
    		
    		while(loginSuccess == null) {
    			System.out.println("Enter your username:");
    			username = scanner.nextLine();
    			System.out.println("Enter your password:");
    			password = scanner.nextLine();
    			loginSuccess = userList.getUserCredentials(username, password);
    		}
    		
    		activeUser = loginSuccess;
    	}
    
	    // vend = new VendingMachine("Feral Vending Machine", "No Owner", "An arbitrary forest in Northern New England. This vending machine is comfortably tucked under a rocky alcove. 1368 A.D.");
		vendingMachineList = save.getVendingMachineList();
		userList = save.getUserList();
    	vend = vendingMachineList.getVendingMachine(activeUser.getLocation());
		// System.out.println("vend login" + activeUser.getLocation() + "list size "+ vendingMachineList.size() );
		Container textInput = new Container(new ArrayList<String>());
		Container staticContent = new Container(new ArrayList<String>());
		//Container textOutput = new Container(new ArrayList<String>());
		String userInput = "";
		//System.out.println(consoleContainer.toString());
		
		shell:
		do {
			String location;
			
			System.out.println(Printing.leftPadString("\n", 19, '\n'));
			System.out.println("Location" + vendingMachineList.getVendingMachine(activeUser.getLocation()).getVendingMachineId());
			System.out.println(vend.toString());
			System.out.println(activeUser.toString());
			
			userInput = scanner.nextLine();
			input.add(userInput);
			
		    switch(userInput) {
				default:
					// textInput.add("=>" + userInput);
					// User Info
					// Inventory
					System.out.println(vend.getVendingMachineName() + ": Do Something (enter help for options)!");
					break;
				case "admin":
					if (!(activeUser instanceof Admin)) {
						System.out.println("This user does not have admin. priveleges.");
					} else {
						System.out.println("Hi admin! Menu options:");
						System.out.println("inactive -- view pending/inactive user list.");
						System.out.println("modify -- modify user. Approve pending.");
						System.out.println("search -- search for user.");
						String option = scanner.nextLine();
						switch(option) {
							case "pending":
								System.out.println("The following users are inactive");
								for (User user : userList.getInactiveUsers()) {
									System.out.println(user.getName() + " " + user.getLoginUUID());
								}
								break;
							case "modify":
								System.out.println("Enter user's UUID");
								String uuid = scanner.nextLine();
								User user = userList.getUser(uuid);
								
								if (user != null) {
									String activity = user.getEnabled() ? "enabled" : "disabled";
									System.out.println(user.getName() + " " + user.getLoginUUID());
									System.out.println("Account active");
									System.out.println(user.getAboutMe());
									System.out.println("Account is " + activity + ".");
								}
								
								System.out.println("enable, disable, rename, password");
								option = scanner.nextLine();
								
								switch(option) {
									case "enable":
										user.enableAccount();
										System.out.println("The account " + user.getName() + " was enabled.");
										break;
									case "disable":
										user.disableAccount();
										System.out.println("The account " + user.getName() + " was disabled");
										break;
									case "password":
										System.out.println("Enter a new password");
										option = scanner.nextLine();
										if (option != null && option != "") {
											user.setPassword(option);
										} else {
											System.out.println("Changing the password failed!");
										}
									case "rename":
										System.out.println("Enter a new username:");
										option = scanner.nextLine();
										if (option != null && option != "") {
											user.setName(option);
										} else {
											System.out.println("Renaming failed");
										}
										break;
								}
								
								modify:
								while (true) {
									if (userList.getUser(uuid) == null) {
										System.out.println("User not found. Try again? (Y/N)");
										scannerInput = scanner.nextLine();
										switch (scannerInput.toUpperCase()) {
											case "Y":
												break;
											case "YES":
												break;
											default:
												break modify;
										}
									}
								}
								break;
							default:
								System.out.println("Search for a user by");
								System.out.println("1 -- UUID");
								System.out.println("2 -- username");
								System.out.println("3 -- Vending Machine UUID");
								break;
						}
					}
					break;
				case "balance":
					System.out.println("You have " + activeUser.getCash() + " units left to spend. Good for you.");
					break;
				case "buy":
		        	System.out.println("Enter a a value A-D, 1-5. Like D3 or C5.");
		        	location = scanner.nextLine();
		        	StockItem stockItem = vend.getInventory().getStockItemAt(location);
		        	if (stockItem != null) {
		        		int cost = stockItem.getQuote(1);
		        		if (activeUser.getCash() > cost) {
		        			activeUser.addItem(stockItem);
		        			activeUser.setCash(-1 * cost);
		        			stockItem.takeFromStock(1);
		        		}
		        	}
					break;
				case "login":
					System.out.println("Command not implemented");
					break;
				case "help":
					System.out.println(vend.getVendingMachineName() + " says ...");
					System.out.println("Available commands are ..");
					System.out.println("admin -- administrator options.");
					System.out.println("balance -- Check your remaining balance.");
					System.out.println("buy -- purchase an item from the vending machine.");
					System.out.println("login -- Login to another account.");
					System.out.println("exit -- Exit the application.");
					System.out.println("history -- Review your 20 most previous queries.");
					System.out.println("help -- See this menu.");
					System.out.println("rename -- rename the vending machine");
					System.out.println("status -- Check your status effects.");
					System.out.println("stock -- add gum, water, and snacks to the vending machine.");
					System.out.println("use -- use an item from your inventory");
					System.out.println(":" + activeUser.getName() + ", do Something (enter help for options)!");
					break;
		        case "history":
		        	input.printHistory();
		        	break;
		        case "exit":
		        	break shell;
		        case "rename":
		        	System.out.println("Enter a new name for the vending machine");
		        	vend.setVendingMachineName(scanner.nextLine());
		        case "save":
		        	try {
		        		save.setUserList(userList);
		        		save.setVendingMachineList(vendingMachineList);
						save.writeToFile(SAVE_FILE_NAME);
						System.out.println("Saved!");
					} catch (IOException e) {
						System.out.println("Failed to save!");
						e.printStackTrace();
					}
		        	break;
		        case "status":
		        	activeUser.getStatus();
		        	break;
		        case "stock":
		        	System.out.println("Enter an item. Like gum, water or a snacks or teleporter.");
		        	String item = scanner.nextLine();
		        	System.out.println("Enter the quantity to add greater than zero please!");
		        	int quantity = Integer.valueOf(scanner.next());
		        	System.out.println("Enter a a value A-D, 1-5. Like D3 or C5.");
		        	location = scanner.nextLine();
		        	switch (item) {
		        		case "gum":
		        			Item gum = new Gum();
		        			StockItem moreGum = new StockItem(gum, quantity);
		        			vend.getInventory().put(location, moreGum);
		        			break;
		        		case "water":
		        			Item water = new BottledWater();
		        			StockItem moreWater = new StockItem(water, quantity);
		        			vend.getInventory().put(location, moreWater);
		        			break;
		        		case "snacks":
		        			Item snacks = new Snack();
		        			StockItem moreSnacks = new StockItem(snacks, quantity);
		        			vend.getInventory().put(location, moreSnacks);
		        			break;
		        		case "teleporter":
		        			Item teleporter = new MysteriousPocketPortalMachine();
		        			StockItem moreTeleporters = new StockItem(teleporter, quantity);
		        			vend.getInventory().put(location, moreTeleporters);
		        			break;
		        		default :
		        			System.out.println("That item does not exist.");
		        			break;
		        	}
		        	break;
		        case "use":
		        	System.out.println("Use an item from your inventory.");
		        	System.out.println("Enter a number 1 through 5.");
		        	int selection = Integer.valueOf(scanner.next());
		        	if (selection  > 0 && selection < 6) {
		        		activeUser.useItem(selection);
		        	}
		        	break;
		    }
		} while (userInput != null);
    }
    
    public static List<User> search(Scanner scanner, UserList userList, VendingMachineList vendingMachineList) {
    	String input = "loop";
		List<User> users;
    	
    	loop:
    	while (input.equals("loop")) {
	    	System.out.println("Search for a user by");
			System.out.println("1 -- UUID");
			System.out.println("2 -- username");
			System.out.println("3 -- Vending Machine UUID");
			System.out.println("Q/q -- quit");
			
			input = scanner.nextLine();
			
			switch (input.toUpperCase()) {
			case "1":
				System.out.println("Enter user's UUID for search:");
				input = scanner.nextLine();
				users = userList.userIdStartsWith(input);
				
				printSearchResults(users);
				
				break;
			case "2":
				System.out.println("Enter user's username for search:");
				input = scanner.nextLine();
				
				users = userList.getUserByName(input);
				
				printSearchResults(users);
				
				break;			
			case "3":
				System.out.println("Enter vending machine's UUID for search:");
				input = scanner.nextLine();
				
				users = userList.getUsersAtVendingMachine(input);
				
				printSearchResults(users);
				
				break;
				
			case "Q":
				return null;
			default:
				continue;
			}
			
			yesno:
			while (true) {
				System.out.println("Quit?(Y/N)");
				
				input = scanner.nextLine();
				
				switch (input.toUpperCase()) {
					case "Y":
						break yesno;
					case "N":
						break loop;
					default:
						break;
				}
			}
    	}
		
    	return null;
    }
    
    public static void printSearchResults(List<User> users) {
    	if (users != null && (users.size() > 0)) {
			System.out.println("Search returned " + users.size() + " user(s).");
			for (int i = 0; i < users.size(); i++) {
				if (i % 5 == 0) {
					System.out.println("  |       name       |    user's login uuid     |    user's location uuid   ");
				}
				System.out.println(i + ": " + users.get(i).getName() + " " + users.get(i).getLoginUUID() + " " + users.get(i).getLocation());
			}
		} else {
			System.out.println("User UUID supplied returned no results.");
		}
    }
}
