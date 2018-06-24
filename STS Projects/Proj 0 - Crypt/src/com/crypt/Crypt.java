import java.util.Dictionary;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.crypt.Accounts.*;

public class Crypt {
	static Dictionary<String, Byte> roles;
	
	public static void main(String[] args) {
		roles.put("user", (byte)0);
		roles.put("admin", (byte)1);
		
		switch(MainMenu()) {
		case "1":
			LogIn();
			break;
		case "2":
			CreateAccount();
			break;
		case "3":
			ForgotPassword();
		}
	}
	private static String MainMenu() {
		String input = "";
		boolean repeat;
		Scanner scan;
		if(!repeat) scan = new Scanner(System.in);
		
		System.out.println("Welcome to Crypt.");		
		System.out.println("This is the main menu.");
		System.out.println("Would you like to (1) Log in or (2) Create an account? "
						 + "Or have you (3) forgotten your password?");
		input = scan.nextLine();
		
		if(input != "1" || input != "2")
		{ System.out.println("The input was incorrect. Please try again."); MainMenu(); repeat = true;}
		scan.close();
		return input;
	}

	private static void LogIn() {
		String input = "";
		String username = "";
		String password = "";
		boolean repeat;
		Scanner scan;
		if(!repeat) scan = new Scanner(System.in);
		
		System.out.print("Username: ");
		username = scan.nextLine();
		System.out.print("Password: ");
		password = scan.nextLine();
		if(TestUserPass(username, password)) {
			scan.close();
			LoginPass();
		} else {
			Boolean done = false;
			do {
			System.out.println("Username and password do not match. Please (1) try again or (2) exit to main menu.");
			input = scan.nextLine();
			
			switch(input) {
			case "1":
				done = true;
				repeat = true;
				LogIn();
				break;
			case "2":
				done = true;
				MainMenu();
				break;
			default:
				System.out.println("Input did not correspond to any valid options. Please try again.");
				break;
			}
			}while(done == false);
		}
	}
	private static void LoginPass(Account a) {

		
		boolean repeat;
		Scanner scan;
		if(!repeat) scan = new Scanner(System.in);
		byte input;
		
		System.out.println("Access granted.");
		System.out.println("The following options are available:\n(1) Account Options\n(2) Deposit Data\n (3) Withdraw Data");
		if(a.getRole() == roles.get("admin")){ System.out.println("(4) Admin Options"); }
		try { input = scan.nextByte(); } 
		catch(InputMismatchException e) { input = 6; }
		switch(input) {
		
		case (byte)1://Account Options 
			accountOptions();
			break;
		case (byte)2://Deposit Data
			DepositMenu();
			break;
		case (byte)3://Withdraw Data
			WithdrawMenu();
			break;
		case (byte)4://Admin Options
			AdminOptions();
			break;
		default://incorrect input
			System.out.println("Your input matched no expected options. Please try again.");
			LoginPass(a);
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
			AccountOptions.editUsernameMenu(a);
			break;
		case 2://Edit Password
			AccountOptions.editPasswordMenu(a);
			break;
		case 3://Change your default seed
			AccountOptions.editDefaultSeedMenu(a);
			break;
		case 4://Go back
			break;
		default:
			break;
		}
	}
	private boolean TestUserPass(String user, String pass) {
		getUserPassInfo();
		
	}
	
	private static String CreateAccount() {
		String input = "";
		Scanner scan = new Scanner(System.in);
		
		scan.close();
		return input;
	}

	private static String ForgotPassword() {
		String input = "";
		Scanner scan = new Scanner(System.in);
		
		scan.close();
		return input;
	}
}
