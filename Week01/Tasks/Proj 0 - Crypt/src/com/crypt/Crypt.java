package com.crypt;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.crypt.Accounts.Account;
import com.crypt.Accounts.AccountOptions;
import com.crypt.filemanager.UserPassFileManager;

public class Crypt {
	static HashMap<String, Byte> roles = new HashMap<String, Byte>();
	static HashMap<String, String> userPassInfo = new HashMap<String, String>();
	static List<Account> accounts;
	
	public static void main(String[] args) {
		UserPassFileManager upfm = new UserPassFileManager("accounts.ser");	
		Crypt c = new Crypt();
		if(upfm.fileExists())accounts = upfm.readObject();
		
		roles.put("user",  (byte) 0);
		roles.put("admin", (byte) 1);
		
		c.mainMenu(c.showMainMenu());
		upfm.writeObject(accounts);
	}
	
	private byte showMainMenu() { return Input.showMenu("Welcome to Crypt.", Input.giveStringArray("Log in", "Create an account", "Forgotten password", "Exit")); }
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
			mainMenu(showMainMenu());
		}
	}
	
	private void login() {
		Account a = userPass();
		if(testUserPass(a)) { loginPass(a);	} 
		else {
			doloop:
			do {
				Input.showMenu("Username and password do not match.", Input.giveStringArray("Try again", "Exit to main menu"));			
				sw:
				switch(Input.getInputByte()) {
				case 1:
					login();
					break sw;
				case 2:
					mainMenu(showMainMenu());
					break sw;
				default:
					System.out.println("Input did not correspond to any valid options. Please try again.");
					break sw;
				}
			}while(true);
		}
	}
	private byte showLoginPassMenu(Account a) {
		byte b = Input.showMenu("Access Granted", 
				 Input.giveStringArray("Account Options", "Deposit Data", "Withdraw Data"));
		System.out.println("(4) Admin Options");
		System.out.println("(5) Log Out");
		return b;
	}
	
	private void loginPass(Account a) {
		switch(showLoginPassMenu(a)) {
		case (byte)1://Account Options 
			accountOptions();
			break;
		case (byte)2://Deposit Data
			//DepositMenu();
			break;
		case (byte)3://Withdraw Data
			//WithdrawMenu();
			break;
		case (byte)4://Admin Options
			//AdminOptions();
			break;
		case 5:
			mainMenu(showMainMenu());
			break;
		default://incorrect input
			System.out.println("Your input matched no expected options. Please try again.");
			loginPass(a);
			break;
		}
	}

	
	private static void accountOptions() {
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
	private boolean testUserPass(Account a) {
		if(!userPassInfo.get(a.getUsername()).equals(a.getPassword())) { return false; }
		else { return false; }
	}
	
	private Account createAccount() {
		Account a = userPass();
		
		Input.showMenu("Choose a default seed method:", Input.giveStringArray("Manual", "Randomly generate"));
		switch(Input.getInputByte()) {
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
		
		a.setRole(roles.get("user"));
		return a;
	}

	private static String ForgotPassword() {
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
		a.setUsername(Input.getInputString());
		return a;
	}
}
