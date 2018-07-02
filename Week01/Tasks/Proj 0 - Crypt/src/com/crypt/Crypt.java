package com.crypt;

import static com.crypt.Menus.showAdminOptionsMenu;
import static com.crypt.Menus.showApproveAccountsMenu;
import static com.crypt.Menus.showCurrentItemsMenu;
import static com.crypt.Menus.showDefaultSeedMethodMenu;
import static com.crypt.Menus.showLoginFailMenu;
import static com.crypt.Menus.showLoginPassMenu;
import static com.crypt.Menus.showMainMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//import org.apache.log4j.Logger;

import com.crypt.Services.AccountService;
import com.crypt.Services.UserPassService;
import com.crypt.beans.Account;
import com.crypt.beans.UserPass;

public class Crypt {
	//Making a proper enumerator IE: Making a Hash map that I can use to get roles as numbers
	static final HashMap<String, Integer> ROLES = new HashMap<String, Integer>()
	{private static final long serialVersionUID = 1L;
	{put("user",  0); put("admin",  1);}};
	//Keeps a running list of user names and passwords
	static HashMap<String, String> userPassInfo = new HashMap<String, String>();
	//Stores account data
	static Map<String, Account> accounts = new HashMap<>();
	//Saves and loads user pass info

	//final static Logger logger = Logger.getRootLogger();	

	public static void main(String[] args) {
		//logger.trace("Main method started");
		Crypt c = new Crypt();
		//filling variables from files for persistence
		userPassInfo = UserPassService.selectAllUserPass();
		accounts = AccountService.selectAllAccounts();

		//showing the main menu
		c.mainMenu(showMainMenu());
		
	}


	private void mainMenu(byte input) {
		switch(input) {
		case 1:
			login();
			break;
		case 2:
			Account a = createAccount();
			accounts.put(a.getUsername(), a);
			mainMenu(showMainMenu());
			break;
		case 3:
			ForgotPassword();
			break;
		case 4:
			break;
		default:
			noMatch();
			mainMenu(showMainMenu());
		}
	}

	private void login() {
		//logger.info("login attempted");
		//testAdminExist(); //Tests existence of at least one user
		UserPass up = userPass(); //Gets user name and password
		if(testUserPass(up)) //tests user name and password
		{
			Account a = accounts.get(up.getUsername());
			if(a.isApproved() == 1) { loginPass(a); } 
			else {
				System.out.println("This account has yet to be approved.");
				mainMenu(showMainMenu());
			}
		} //if successful log in and the account has been authorized
		else { loginFail(showLoginFailMenu()); } //if login failed
	}

	private void loginFail(byte input) {
		//logger.trace("login failed");
		switch(input) {
		case 1:
			login();
			break;
		case 2:
			mainMenu(showMainMenu());
			break;
		default:
			noMatch();
			break;
		}
	}

	private void loginPass(Account a) {
		//logger.info("login passed");
		switch(showLoginPassMenu(a)) {
		case (byte)1://Account Options 
			//accountOptions();
			System.out.println("Not currently implemented. Please come back later.");
			loginPass(a);
			break;
		case (byte)2://Deposit Data
			deposit(a);
			showCurrentItems(showCurrentItemsMenu(), a);
			loginPass(a);
			break;
		case (byte)3://Withdraw Data
			withdraw(a);
			showCurrentItems(showCurrentItemsMenu(), a);
			loginPass(a);
			break;
		case 5://Log out
			mainMenu(showMainMenu());
			break;
		case (byte)4://Administrator Options
			if(a.getRole() == ROLES.get("admin")) {
				adminOptions(showAdminOptionsMenu(), a);
				break;
			}
		default://incorrect input
			noMatch();
			loginPass(a);
			break;
		}
	}

	private void adminOptions(byte input, Account a) {
		//logger.info("made it to admin options");
		switch(input) {
		case 1: //Approve user
			//logger.info("attempting user approval");
			List<String> unapprovedAccounts = new ArrayList<>();
			
			for( Account a2 : accounts.values()) 
			{ 
				if(a2.isApproved() == 0) 
				{ 
					unapprovedAccounts.add(a2.getUsername()); 
				} 
			}
			if(!unapprovedAccounts.isEmpty())
				approveAccounts(showApproveAccountsMenu(unapprovedAccounts), unapprovedAccounts);
			else {
				System.out.println("There are currently no accounts awaiting approval.");
			}
			adminOptions(showAdminOptionsMenu(), a);
			break;
		case 2://Search accounts
			System.out.println("Not implemented in this version");
			//break;
		case 3://Change user account info
			System.out.println("Not implemented in this version");
			//break;
		case 4://Return to previous menu
			loginPass(a);
			break;
		default://no matching input
			noMatch();
			adminOptions(showAdminOptionsMenu(), a);
		}
	}

	private void approveAccounts(byte input, List<String> unapprovedAccounts) {
		if(input > 0 && input <= unapprovedAccounts.size()) { 
			//Choosing any one particular account to approve
			String enteredUsername = unapprovedAccounts.get(input - 1);
			accounts.get(enteredUsername).setApproved(1);
			unapprovedAccounts.remove(enteredUsername);
		} else if(input == unapprovedAccounts.size() + 1) { 
			//Choosing all of the above
			accounts.values().forEach( a -> a.setApproved(1));
			unapprovedAccounts.clear();
		}else { 
			//No matching input. Reshowing unapproved accounts menu
			noMatch();
			approveAccounts(showApproveAccountsMenu(unapprovedAccounts), unapprovedAccounts);
		}
	}

	private void withdraw(Account a) {
		showCurrentItems((byte) 1, a);
		System.out.println("Which item would you like to withdraw?");
		a.getItems().toString();
		String[] s = new String[a.getItems().size()];
		s = a.getItems().toArray(s);
		a.withdraw(Input.showMenu("", s));
	}

	private void showCurrentItems(byte input, Account a) {
		switch(input) {
		case 1:
			System.out.println("Your currently stored items are:\n" + a.getItems());
			break;
		case 2:
			break;
		default:
			noMatch();
			showCurrentItems(showCurrentItemsMenu(), a);
			break;
		}
	}
	
	/**
	 * WIP: allows for edit of account options
	 * IE: user name, password, etc.
	 */
	private void accountOptions() {
		System.out.println("The following options are available:\n(1) Edit your username\n(2) Edit your password\n"
				+ "(3) Change your default seed\n(4) Go back to previous menu");
		Scanner scan = new Scanner(System.in);
		byte input;
		try{ input = scan.nextByte(); }
		catch(InputMismatchException e) {input = (byte)6;}

		switch(input) {
		case 1://Edit username
			//AccountOptions.editUsernameMenu(a);
			break;
		case 2://Edit Password
			//AccountOptions.editPasswordMenu(a);
			break;
		case 3://Change your default seed
			//AccountOptions.editDefaultSeedMenu(a);
			break;
		case 4://Go back
			break;
		default:
			break;
		}
	}

	/**
	 * calls account deposit and shows deposit menu
	 * @param a
	 */
	private void deposit(Account a) {
		System.out.println("Enter a filepath to deposit:");
		String s = Input.getInputString();
		if(s.contains("/") || s.contains("\\")) { a.deposit(s); }
		else { System.out.println("That is not a valid filepath."); deposit(a); }
	}

	/**
	 * calls for the specification from the user on how they would prefer their data to
	 * be encrypted.
	 */
	private void encryptionOptions() {

	}

	/**
	 * tests to see if user name and password are both approved
	 * and match database entry
	 * @param up
	 * @return
	 */
	private boolean testUserPass(UserPass up) {
		if(
				!userPassInfo.containsKey(up.getUsername())
				&& 
				!userPassInfo.get(up.getUsername()).equals(up.getPassword())) 
		{ return false; } else { return true; }
	}

	/**
	 * allows for creation of new users and administrators
	 * @return
	 */
	private Account createAccount() {
		UserPass up = userPass();
		for(String username : userPassInfo.keySet()) {
			if(up.getUsername().equals(username)) {
				System.out.println("That usename is not available. Please try again.");
				createAccount();
			}
		}
		
		userPassInfo.put(up.getUsername(), up.getPassword());

		Account a = new Account(up.getUsername(), ROLES.get("user"));
		a = defaultSeedMethod(a);
		
		return a;
	}

	private Account defaultSeedMethod(Account a) {
		switch(showDefaultSeedMethodMenu()) 
		{
		case 1:
			a.setDefaultSeed(Integer.parseInt(Input.getInputString()));
			break;
		case 2:
			a.generateDefaultSeed();
			break;
		default:
			noMatch();
			createAccount();
			break;
		}
		return a;
	}
	/**
	 * allows user to reset password in case of forgetting
	 * @return
	 */
	private String ForgotPassword() {
		String input = "";
		Scanner scan = new Scanner(System.in);

		scan.close();
		return input;
	}

	/**
	 * retrieves user name and password
	 * @return
	 */
	private UserPass userPass() {
		UserPass up = new UserPass();

		System.out.print("Username: ");
		up.setUsername(Input.getInputString().toLowerCase());
		System.out.print("Password: ");
		up.setPassword(Input.getInputString());
		return up;
	}

	/**
	 * if input does not match allowed options
	 */
	private void noMatch() { System.out.println("Input did not correspond to "
			+ "any valid options. Please try again."); }
}
