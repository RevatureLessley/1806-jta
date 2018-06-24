package com.crypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.crypt.Accounts.Account;
import com.crypt.Accounts.AccountOptions;
import com.crypt.filemanager.UserPassFileManager;

public class Crypt {
	//Making a proper enumerator IE: Making a Hash map that I can use to get roles as numbers
	static final HashMap<String, Byte> ROLES = new HashMap<String, Byte>()
	{private static final long serialVersionUID = 1L;
	{put("user", (byte) 0); put("admin", (byte) 1);}};
	static HashMap<String, String> userPassInfo = new HashMap<String, String>();
	static ArrayList<Account> accounts = new ArrayList<Account>();
	static UserPassFileManager upfm = new UserPassFileManager("accounts.ser");	

	public static void main(String[] args) {
		Crypt c = new Crypt();
		//filling variables from files for persistence
		ArrayList<Account> al = new ArrayList<Account>(); 
		if(upfm.fileExists())al = upfm.readObject();
		if(al != null) accounts = al;
		testAdminExist();
		userPassInfo = upfm.fillUserPassInfo(accounts);

		//showing the main menu
		c.mainMenu(c.showMainMenu());

		//writing to files at the end of execution for persistence
		upfm.writeObject(accounts);
	}

	private byte showMainMenu() { //Shows main menu
		return Input.showMenu("Welcome to Crypt.", 
				Input.giveStringArray("Log in", "Create an account", "Forgotten password", "Exit")); }

	private void mainMenu(byte input) {
		switch(input) {
		case 1:
			login();
			break;
		case 2:
			accounts.add(createAccount());
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
		//testAdminExist(); //Tests existence of at least one user
		Account a = userPass(); //Gets user name and password
		if(testUserPass(a)) //tests user name and password
		{ if(a.isApproved())loginPass(a);	} //if successful log in and the account has been authorized
		else { loginFail(showLoginFailMenu()); } //if login failed
	}

	private byte showLoginFailMenu() {
		return Input.showMenu("Username and password do not match.", //calls showMenu and gives prescript
				Input.giveStringArray("Try again", "Exit to main menu")); //gets string array, prints options
	}

	private void loginFail(byte input) {
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

	private byte showLoginPassMenu(Account a) {
		byte b = Input.showMenu("Access Granted", 
				Input.giveStringArray("Account Options", "Deposit Data", "Withdraw Data"));

		if(a.getRole() == ROLES.get("admin")) System.out.println("(4) Admin Options");
		System.out.println("(5) Log Out");

		return b;
	}

	private void loginPass(Account a) {
		switch(showLoginPassMenu(a)) {
		case (byte)1://Account Options 
			//accountOptions();
			System.out.println("Not currently implemented. Please come back later.");
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
				adminOptions(a);
				break;
			}
		default://incorrect input
			noMatch();
			loginPass(a);
			break;
		}
	}

	private byte showAdminOptionsMenu() {
		return Input.showMenu("Admin Options.", 
				Input.giveStringArray("Approve an applying user", "Search accounts", "Change user accounts", "Return to previous menu"));
	}

	private void adminOptions(byte input, Account a) {
		switch(input) {
		case 1: //Approve user		
			HashMap<String, Account> unapprovedAccounts = new HashMap<>();
			
			for( Account a2 : accounts) 
			{ 
				if(!a2.isApproved()) 
				{ 
					unapprovedAccounts.put(a2.getUsername(), a2); 
				} 
				approveAccounts(showApproveAccountsMenu(unapprovedAccounts.keySet()), unapprovedAccounts);
				
			}
			
			break;
		case 2://Search accounts
			break;
		case 3://Change user account info
			break;
		case 4://Return to previous menu
			break;
		default://no matching input
			noMatch();
			adminOptions(showAdminOptionsMenu(), a);
		}
	}

	private void approveAccounts(byte input, HashMap<String, Account> unapprovedAccounts) {
		System.out.println("(" + (unapprovedAccounts.size() + 1) +") All of the above.");
		if(input > 0 && input <= unapprovedAccounts.size()) {
			accounts.get(
					accounts.indexOf(
							unapprovedAccounts.keySet().toArray()[input]))
									.setApproved(true);
		} else if(input == unapprovedAccounts.size() + 1) {
			accounts.forEach( a -> a.setApproved(true));
			for(Account a : accounts) {
				if(!a.isApproved())a.setApproved(true);
			}
		}else {
			
		}
	}

	private byte showApproveAccountsMenu(Set<String> keySet) {
		String[] s = new String[keySet.size()]; 
		s = keySet.toArray(s);
		return Input.showMenu("These accounts are waiting for approval.", s);
	}

	private void withdraw(Account a) {
		showCurrentItems((byte) 1, a);
		System.out.println("Which item would you like to withdraw?");
		a.getRepoContents().trimToSize();
		String[] s = new String[a.getRepoContents().size()];
		s = a.getRepoContents().toArray(s);
		a.withdraw(Input.showMenu("", s));
	}

	private byte showCurrentItemsMenu() {
		return Input.showMenu("Would you like to see a list of your current items?",
				Input.giveStringArray("Yes", "No"));
	}

	private void showCurrentItems(byte input, Account a) {
		switch(input) {
		case 1:
			System.out.println("Your currently stored items are:\n" + a.getRepoContents());
			break;
		case 2:
			break;
		default:
			noMatch();
			showCurrentItems(showCurrentItemsMenu(), a);
			break;

		}
	}

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

	private void deposit(Account a) {
		System.out.println("Enter a filepath to deposit:");
		String s = Input.getInputString();
		if(s.contains("/") || s.contains("\\")) { a.setFilepath(s); }
		else { System.out.println("That is not a valid filepath."); deposit(a); }

		//Sets up encryption based on user input and desire default seed.
		//TODO: Part 2
		//encryptionOptions(showEncryptionAOptionsMenu(), showEncryptionBOptionsMenu(), showEncryptionCOptionsMenu());

		a.deposit(/*a, b, c*/);

	}

	private byte showEncryptionAOptionsMenu() {
		return Input.showMenu("Please select an encryption algorithm.", 
				Input.giveStringArray("AES", "DES", "DESede", "RSA"));
	}	

	private byte showEncryptionBOptionsMenu() {
		return Input.showMenu("Please select an encryption mode.", 
				Input.giveStringArray("CBC", "ECB"));
	}	

	private byte showEncryptionCOptionsMenu() {
		return Input.showMenu("Please select an encryption padding mode.", 
				Input.giveStringArray("NoPadding", "PKCS5Padding"));
	}

	private void encryptionOptions() {

	}


	private boolean testUserPass(Account a) {
		if(!userPassInfo.get(a.getUsername()).equals(a.getPassword())) { return false; }
		else { return true; }
	}

	private Account createAccount() {
		Account a = userPass();
		for(String username : userPassInfo.keySet()) {
			if(a.getUsername().equals(username)) {
				System.out.println("That usename is not available. Please try again.");
				createAccount();
			}
		}
		userPassInfo.put(a.getUsername(), a.getPassword());

		switch(Input.showMenu("Choose a default seed method:", 
				Input.giveStringArray("Manual", "Randomly generate"))) 
		{
		case 1:
			a.setDefaultSeed(Integer.parseInt(Input.getInputString()));
			break;
		case 2:
			a.generateDefaultSeed();
			break;
		default:
			createAccount();
			break;
		}

		a.setRole(ROLES.get("user"));
		return a;
	}

	private String ForgotPassword() {
		String input = "";
		Scanner scan = new Scanner(System.in);

		scan.close();
		return input;
	}

	private Account userPass() {
		Account a = new Account();

		System.out.print("Username: ");
		a.setUsername(Input.getInputString());
		System.out.print("Password: ");
		a.setPassword(Input.getInputString());
		return a;
	}

	private static void testAdminExist() {
		if(accounts.isEmpty() || !accounts.get(0).getUsername().equals("Austin")) 
		{ accounts.add(new Account("Austin", "bobbert", ROLES.get("admin"))); }
	}

	private void noMatch() { System.out.println("Input did not correspond to "
			+ "any valid options. Please try again."); }
}
