package com.revature.project0;


import java.util.Scanner;
import org.apache.log4j.Logger;

public class EntryClass {
	public static Logger logger = Logger.getLogger(EntryClass.class);
	static Scanner in = null;

	public static void main(String[] args) {
		mainFlow();
	}

	private static void mainFlow() {
		breakhere: while (true) {
			int choice = mainMenu();
			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				loginFlow();
				break;
			case 3:
				logger.info("Perform quit case.");
				break breakhere;
			default:
				// The user input an unexpected choice.
			}
		}
	}
	private static void loginFlow() {
		in = new Scanner(System.in);
		System.out.println("Enter Username:");
		String username = in.nextLine();
		System.out.println("Enter Password:");
		String password = in.nextLine();
		logger.info(username + " " + password);
		User u1 = Utility.retrieveUser(username);

		if (u1.getPassword().equals(password)) {

			logger.info("Login Successful for user" + username);
			if (u1.isAdmin()) {
				breakAdmin: while (true) {
					int adminChoice = menuAdmin();
					switch (adminChoice) {
					case 1:
						adminApproveAcc();
						break;
					case 2:
						adminBanAcc();
						break;
					case 3:
						logger.info("Perform quit case.");
						break breakAdmin;
					default: // The user input an unexpected choice.
					}
				}
			} else {
				breakUser: while (true) {
					int userChoice = menuUser();
					switch (userChoice) {
					case 1:
						userDeposit(u1);
						break;
					case 2:
						userWithdraw(u1);
						break;
					case 3: 
						userBalanceCheck(u1);
						break;	
					case 4:
						logger.info("Perform quit case.");
						break breakUser;
					default: // The user inputs an unexpected choice
					}
				}

			}
		} else {
			System.out.println("Incorrect username or password");
		}
	}
	private static void userWithdraw(User u1) {
		System.out.println("Enter Withdrawel Amount:");
		double withAmnt = in.nextDouble();
		if (u1.getAccount().isActive()) {
			u1.getAccount().withdraw(withAmnt);
			Utility.persistObj(u1);
			logger.info(u1.getAccount().getAccNum() + " | " + u1.getAccount().getBal());
		} else  {
			System.out.println("You account is not activated, please contact admin.");
		}
	}
	private static void userDeposit(User u1) {
		System.out.println("Enter Deposit Amount:");
		double depAmnt = in.nextDouble();
		if (u1.getAccount().isActive()) {
			u1.getAccount().deposit(depAmnt);
			Utility.persistObj(u1);
			logger.info(u1.getAccount().getAccNum() + " | " + u1.getAccount().getBal());
		} else {
			System.out.println("You account is not activated, please contact admin.");
		}
	}
	private static void userBalanceCheck(User u1) {
		if (u1.getAccount().isActive()) {
			System.out.println("Your balance is: $ " + u1.getAccount().getBal());
			Utility.persistObj(u1);
			logger.info(u1.getAccount().getAccNum() + " | " + u1.getAccount().getBal());
		} else  {
			System.out.println("You account is not activated, please contact admin.");
		}
	}
	private static void adminApproveAcc() {
		in = new Scanner(System.in);
		System.out.println("Enter Account to Approve:");
		String userAccountName = in.nextLine();
		User u2 = Utility.retrieveUser(userAccountName);
		u2.getAccount().setActive(Boolean.TRUE);
		Utility.persistObj(u2);
		System.out.println("Account is approved:" + userAccountName);
	}
	private static void adminBanAcc() {
		in = new Scanner(System.in);
		System.out.println("Enter Account to Ban:");
		String userAccountName = in.nextLine();
		User u2 = Utility.retrieveUser(userAccountName);
		u2.getAccount().setActive(Boolean.FALSE);
		Utility.persistObj(u2);
		System.out.println("Account is Inactivated:" + userAccountName);
	}
	private static void createAccount() {
		in = new Scanner(System.in);
		System.out.println("Enter new Username:");
		String username = in.nextLine();
		System.out.println("Enter new Password:");
		String password = in.nextLine();
		System.out.println("Admin?");
		boolean isAdmin = in.nextBoolean();
		logger.info(username + " " + password);
		User u = new User(username, password, isAdmin);
		logger.info(u.getAccount().getAccNum());
		Utility.persistObj(u);
	}
	public static int mainMenu() {

		int selection;
		in = new Scanner(System.in);

		/***************************************************/

		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - Create an account");
		System.out.println("2 - Login");
		System.out.println("3 - Quit");

		selection = in.nextInt();
		return selection;
	}
	public static int menuUser() {

		int selection;
		in = new Scanner(System.in);

		/***************************************************/

		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - Deposit to account");
		System.out.println("2 - Withdraw from account");
		System.out.println("3 - Check account balance");
		System.out.println("4 - Quit");

		selection = in.nextInt();
		return selection;
	}
	public static int menuAdmin() {
		int selection;
		in = new Scanner(System.in);

		/***************************************************/

		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - Approve account");
		System.out.println("2 - Ban account");
		System.out.println("3 - Quit");

		selection = in.nextInt();
		return selection;
	}

	

}
