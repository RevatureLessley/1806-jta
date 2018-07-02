package com.crypt;

import static com.crypt.Crypt.ROLES;
import static com.crypt.Crypt.logger;

import java.util.Set;

import com.crypt.beans.Account;

public class Menus {
	public static byte showMainMenu() { //Shows main menu
		logger.trace("showed main method");
		return Input.showMenu("Welcome to Crypt.", 
				Input.giveStringArray("Log in", "Create an account", "Forgotten password", "Exit")); }
	public static byte showLoginFailMenu() {
		logger.trace("login fail menu shown" );
		return Input.showMenu("Username and password do not match.", //calls showMenu and gives prescript
				Input.giveStringArray("Try again", "Exit to main menu")); //gets string array, prints options
	}
	public static byte showLoginPassMenu(Account a) {
		logger.trace("login pass menu shown");
		String[] s;
		if(a.getRole() == ROLES.get("admin")) { 
			s = new String[] { "Account Options", "Deposit Data", "Withdraw Data", "Admin Options", "Log Out"};  //Displays admin options if admin
		} else { 
			s = new String[] { "Account Options", "Deposit Data", "Withdraw Data", "Log Out" };//Not so much
		}
		
		return Input.showMenu("Access Granted", s);
	}
	public static byte showAdminOptionsMenu() {
		logger.trace("admin options menu shown");
		return Input.showMenu("Admin Options.", 
				Input.giveStringArray("Approve an applying user", "Search accounts", "Change user accounts", "Return to previous menu"));
	}
	public static byte showApproveAccountsMenu(Set<String> keySet) {
		String[] s = new String[keySet.size()]; 
		s = keySet.toArray(new String[keySet.size() + 1]);
		s[s.length - 1] = "All of the above.";
		return Input.showMenu("These accounts are waiting for approval.", s);
	}
	public static byte showCurrentItemsMenu() {
		return Input.showMenu("Would you like to see a list of your current items?",
				Input.giveStringArray("Yes", "No"));
	}
	public static byte showEncryptionAOptionsMenu() {
		return Input.showMenu("Please select an encryption algorithm.", 
				Input.giveStringArray("AES", "DES", "DESede", "RSA"));
	}	
	public static byte showEncryptionBOptionsMenu() {
		return Input.showMenu("Please select an encryption mode.", 
				Input.giveStringArray("CBC", "ECB"));
	}	
	public static byte showEncryptionCOptionsMenu() {
		return Input.showMenu("Please select an encryption padding mode.", 
				Input.giveStringArray("NoPadding", "PKCS5Padding"));
	}
	public static byte showDefaultSeedMethodMenu() {
		return Input.showMenu("Choose a default seed method:", 
				Input.giveStringArray("Manual", "Randomly generate"));
	}
}
