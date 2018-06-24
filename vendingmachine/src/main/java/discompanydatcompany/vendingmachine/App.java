package discompanydatcompany.vendingmachine;

import java.util.HashMap;

import discompanydatcompany.vendingmachine.entities.Admin;
import discompanydatcompany.vendingmachine.entities.User;
import discompanydatcompany.vendingmachine.entities.UserList;
import discompanydatcompany.vendingmachine.entities.VendingMachine;
import discompanydatcompany.vendingmachine.entities.VendingMachineList;
import discompanydatcompany.vendingmachine.utilities.Container;
import discompanydatcompany.vendingmachine.utilities.Input;
import discompanydatcompany.vendingmachine.utilities.SaveFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App {
	public static final String SAVE_FILE_NAME = "DATSAV";
    private boolean isSaveFilePresent = false;
    
    public String getGreeting() {
        return "\"Hello world.\" -- Feral Vending Machine (No Owner)";
    }

    public static void main(String[] args) {
    	App app = new App();
    	Input input = new Input();
    	SaveFile save = new SaveFile();
    	VendingMachine vend;
    	VendingMachineList vendingMachineList;
    	UserList userList;
    	User activeUser;
    	
    	
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
    		String name;
    		String password;
    		
    		vend = new VendingMachine("Feral Vending Machine", "No Owner", "An arbitrary forest in Northern New England. This vending machine is comfortably tucked under a rocky alcove. 1368 A.D.");
    		System.out.println(vend.toString());
    		System.out.println(app.getGreeting());
    		System.out.println("You are Admin. You have no choice. -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		System.out.println("What's your name -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		
    		do {
    			name = input.passNext();
    		} while(name == null || name == "");
    		
    		System.out.println("Tell me something about yourself. -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		String aboutMe = input.passNext();
    		
    		System.out.println("Enter a password and tell me no lies. -- " + vend.getVendingMachineName() + "(" + vend.getAdminName() + ")");
    		
    		do {
    			password = input.passNext();
    		} while (password == null || password == "");
    		
    		Admin admin = new Admin(name, password, aboutMe);
    		vend.setAdminName(admin.getName());
    		System.out.println("Admin name:" + admin.getName());
    		System.out.println(vend.getVendingMachineName() + " " + vend.getVendingMachineId());
    		
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
    		System.out.println("User: " + activeUser.getName());
    		System.out.println("User location: " + activeUser.getLocation() );
    		System.out.println("Vending Machine" + vendingMachineList.getVendingMachine(activeUser.getLocation()).getVendingMachineName());
    	
    	} else {
    		// Save is present
    		save.loadFromFile(SAVE_FILE_NAME);
    		String password;
    		String username;
    		HashMap<String, String> keyRing = new HashMap<String, String>();
    		
    		// retrieve save data 
    		userList = save.getUserList();
    		vendingMachineList = save.getVendingMachineList();
    		
    		// login
    		User loginSuccess = null;
    		
    		while(loginSuccess == null) {
    			System.out.println("Enter username");
    			username = input.passNext();
    			password = input.passNext();
    			loginSuccess = userList.getUserCredentials(username, password);
    		}
    		
    		activeUser = loginSuccess;
    		System.out.println("User: " + activeUser.getName());
    		System.out.println("User location: " + activeUser.getLocation() );
    		System.out.println("Vending Machine" + vendingMachineList.getVendingMachine(activeUser.getLocation()).getVendingMachineName());
    		
    	}
    
	    // vend = new VendingMachine("Feral Vending Machine", "No Owner", "An arbitrary forest in Northern New England. This vending machine is comfortably tucked under a rocky alcove. 1368 A.D.");
		vendingMachineList = save.getVendingMachineList();
		userList = save.getUserList();
    	vend = vendingMachineList.getVendingMachine(activeUser.getLocation());
		System.out.println("vend login" + activeUser.getLocation() + "list size "+ vendingMachineList.size() );
		Container textInput = new Container(new ArrayList<String>());
		Container staticContent = new Container(new ArrayList<String>());
		//Container textOutput = new Container(new ArrayList<String>());
		String userInput = "";
		//System.out.println(consoleContainer.toString());
		shell:
		while ((userInput = input.next()) != "close") {
		    switch(userInput) {
				default:
					// textInput.add("=>" + userInput);
					// User Info
					// Inventory
					System.out.println(vend.toString());
					System.out.println(vend.getVendingMachineName() + ": Do Something (enter help for options)!");
				case "login":
					break;
				case "help":
					System.out.println(vend.toString());
					System.out.println(vend.getVendingMachineName() + " says ...");
					System.out.println("Available commands are ..");
					System.out.println();
					System.out.println("login -- ");
					System.out.println("history -- ");
					System.out.println("help -- ");
					System.out.println("exit -- ");
					System.out.println("status -- ");
					break;
		        case "history":
		        	input.printHistory();
		        	break;
		        case "exit":
		        	break shell;
		        case "save":
		        	try {
		        		save.setUserList(userList);
		        		save.setVendingMachineList(vendingMachineList);
						save.writeToFile(SAVE_FILE_NAME);
					} catch (IOException e) {
						System.out.println("Failed to save!");
						e.printStackTrace();
					}
		        	break;
		        case "status":
		        	break;
		    }
		}
    }
}
