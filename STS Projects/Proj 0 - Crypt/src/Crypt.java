import java.util.Scanner;

public class Crypt {

	public static void main(String[] args) {
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
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to Crypt.");		
		System.out.println("This is the main menu.");
		System.out.println("Would you like to (1) Log in or (2) Create an account? "
						 + "Or have you (3) forgotten your password?");
		input = scan.nextLine();
		
		if(input != "1" || input != "2")
		{ System.out.println("The input was incorrect. Please try again."); MainMenu();}
		scan.close();
		return input;
	}

	private static void LogIn() {
		String input = "";
		String username = "";
		String password = "";
		Scanner scan = new Scanner(System.in);
		
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
			System.out.println("Username and password do not match. Please (1) try again or (2) exit to main menu");
			input = scan.nextLine();
			
			switch(input) {
			case "1":
				done = true;
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
	
	private static void LoginPass() {
		
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
