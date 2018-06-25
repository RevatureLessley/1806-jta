package view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.AccountController;
import controller.MasterController;
import controller.UserController;
import model.Account;
import model.User;

public class Driver {

	private UserController userController;
	private AccountController accountController;
	private Phase phase = Phase.Initialize;
	private User activeUser;
	private Account activeAccount;
	private static boolean doClear = true;
	private static Scanner scanner;

	private static final int WIN_W = 100, WIN_H = 26;

	/**
	 * Main method of execution; calls other methods depending on the execution
	 * phase of the program. During the initialization phase, any existing data is
	 * read. The main loop is broken when the phase has been set to Terminate.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> al = Arrays.asList(args);

		if (al.contains("noclear"))
			doClear = false;

		resizeConsole();
		clearConsole();

		Driver d = new Driver();

		scanner = new Scanner(System.in);
		Phase newPhase = null;

		while (d.phase != Phase.Terminate) {

			// Execute main phase
			if (d.phase == Phase.Initialize) {
				newPhase = d.executeInitPhase();

			} else if (d.phase == Phase.Login) {
				newPhase = d.executeMainPhase();

			} else if (d.phase == Phase.UserControl) {
				if (d.activeUser.isAdmin())
					newPhase = d.executeAdminPhase();
				else
					newPhase = d.executeUserPhase();

			} else if (d.phase == Phase.AccountControl) {
				newPhase = d.executeAccountPhase();
			}

			// set phase for next iteration
			if (newPhase != null)
				d.phase = newPhase;
			else {
				// default message for invalid input
				System.out.println("Invalid Input");
				d.enterWait();
			}

		}

		clearConsole();
		scanner.close();
		MasterController.shutdown();
	}

	/**
	 * Initialization phase which obtains the userController and accountController
	 * from the MasterController
	 * 
	 * @return Login phase
	 */
	private Phase executeInitPhase() {

		userController = MasterController.getUserController();
		accountController = MasterController.getAccountController();

		return Phase.Login;
	}

	/**
	 * Execution phase which prompts and processes input for the Login phase. Users
	 * may also choose to register new user profiles at this phase
	 * <p>
	 * Options: Login, Register, Exit
	 * 
	 * @return next major program phase to execute in the main loop
	 */
	private Phase executeMainPhase() {
		printHeader("Welcome to the Westward Banking Service", "Now is the time to consume");
		printOptions(true, "Login", "Register", "Exit");
		activeUser = null;

		int input = getOption();

		if (input == 1) {
			return executeLoginPhase();

		} else if (input == 2) {
			return executeRegisterPhase();

		} else if (input == 0) {
			// Exit
			return Phase.Terminate;
		}

		// invalid input
		return null;
	}

	private Phase executeRegisterPhase() {

		System.out
				.println("Usernames may not include any spaces and must be at least 4 characters. Type '\\c' to cancel");
	
		String name = null;
		boolean valid = false;

		while (!valid) {
			System.out.print("Enter username: ");
			name = scanner.nextLine();

			if (name.equals("\\x") || name.equals("\\c)")) {
				return phase;
			} else if (!User.checkUsernameValid(name)) {
				System.out.println("username '" + name + "' is not valid");
			} else if (!userController.checkUsernameAvailable(name)) {
				System.out.println("username '" + name + "' is unavailable");
			} else {
				valid = true;
			}
		}

		valid = false;
		String password = null;
		System.out.println("Passwords must be at least 6 characters long");

		while (!valid) {
			System.out.print("Enter password: ");
			password = scanner.nextLine();

			if (User.checkPasswordValid(password)) {
				valid = true;
			} else {
				System.out.println("username '" + name + "' is not valid");
			}

		}

		userController.addUser(name, password);
		System.out.println("Hello, " + name + ". Welcome to The Westward Banking Service");
		enterWait();

		return phase;
	}

	private Phase executeLoginPhase() {
		System.out.print("Enter username: ");
		String name = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		User user = userController.getUser(name);
		if (user == null) {
			System.out.println("Invalid username");
			enterWait();
			return phase;
		} else {
			int logVal = user.validateLogin(password);
			if (logVal == 1) {
				activeUser = user;
				return Phase.UserControl;
			} else {
				System.out.println("Invalid password");
				enterWait();
				return phase;
			}

		}
	}

	/**
	 * Execution phase which prompts and processes input for standard (non-admin)
	 * users
	 * <p>
	 * Options: Account Summary, Select Account, Open New Account, Logout
	 * 
	 * @return next major program phase to execute in the main loop, returns null in
	 *         the event of invalid input
	 */
	private Phase executeUserPhase() {

		printHeader("Welcome, " + activeUser.getName() + ".",
				"Your total balance: " + Account.formatCurrency(activeUser.totalBalance()));

		printOptions(true, "Accounts Summary", "Select Account", "Open New Account", "Logout");
		int input = getOption();

		if (input == 1) {
			// Accounts Summary
			System.out.println(activeUser.accountSummary());
			enterWait();
			return phase;
		} else if (input == 2) {
			// Select an Account
			return executeUserSelectAccount();
		} else if (input == 3) {
			// Open new Account
			return executeUserOpenAccount();
		} else if (input == 0) {
			// Logout (return to Login phase)
			return Phase.Login;
		}

		// invalid input
		return null;
	}

	/**
	 * Execution phase which prompts and processes input for opening a user account.
	 * The user selects the type of account to create. A name is generated for the
	 * account, the account is created, then added to the accountController object.
	 * <p>
	 * invalid input is ignored; process continues to the User menu
	 * 
	 * @return next major program phase to execute in the main loop
	 */
	private Phase executeUserOpenAccount() {

		System.out.println("Select an account type ");
		printOptions(true, "Checking", "Savings", "Cancel");
		int input = getOption();

		if (input == 1 || input == 2) {

			Account a = accountController.addNewAccount(activeUser, input);
			String name = a.getName();

			if (activeUser.isAdmin()) {
				System.out.println("Account '" + name + "' was created and is now ready for use.");
			} else {
				System.out.println(
						"Account '" + name + "' was created.\nPlease wait for an Admin to validate the new account.");
			}

			enterWait();
		}

		return Phase.UserControl;
	}

	/**
	 * Execution phase which prompts and processes input for selecting a user
	 * Account. Lists all accounts created by the users, including the status of the
	 * accounts. Accounts that have not been validated are shown, but the user
	 * cannot enter the account menu for not-validated accounts
	 * 
	 * @return next major program phase to execute in the main loop, returns null in
	 *         the event of invalid input
	 */
	private Phase executeUserSelectAccount() {
		System.out.println("Select an account");
		String[] acctNames = activeUser.getAccountNames();

		if (acctNames.length == 0) {
			System.out.println("No accounts available");
			enterWait();
			return phase;
		}

		printOptions(false, acctNames);
		int input = getOption();

		Account a = activeUser.getAccount(input - 1);

		if (a != null) {
			// selected an existing account
			activeAccount = a;
			if (a.isValidated())
				return Phase.AccountControl;
			else {
				System.out.println("Apologies, this account has not yet been validated.");
				enterWait();
				return phase;
			}
		}

		return null;
	}

	/**
	 * Execution phase which prompts and processes input for Account actions
	 * <p>
	 * Options: Withdraw, Deposit, Back
	 * 
	 * @return next major program phase to execute in the main loop
	 */
	private Phase executeAccountPhase() {
		printHeader(activeAccount.toString(), "");

		printOptions(true, "Withdraw", "Deposit", "Back");
		int input = getOption();

		if (input == 1) {
			// withdraw
			System.out.print("withdraw amount: ");
			double amt = getDouble();

			activeAccount.withdraw(amt);

		} else if (input == 2) {
			// deposit
			System.out.print("deposit amount: ");
			double amt = getDouble();

			activeAccount.deposit(amt);
		} else if (input == 0) {
			// back
			activeAccount = null;
			return Phase.UserControl;
		}

		return phase;

	}

	/**
	 * Execution phase which prompts and processes input for Admin main menu
	 * <p>
	 * Admin Main Options: Validate, Show all Users, Grant Privileges, go to User
	 * main menu, Free system, Logout
	 * 
	 * @return next major program phase to execute in the main loop, returns null in
	 *         the event of invalid input
	 */
	private Phase executeAdminPhase() {

		int numWaiting = accountController.getWaitAccountCount();

		printHeader("Good evening, Sir " + activeUser.getName(), "How may I serve you today?");
		printOptions(true, "Validate Accounts: " + numWaiting + " waiting", "Show All Users", "Grant Admin Priveleges",
				"User Menu", "Free System Data", "Logout");
		int input = getOption();

		if (input == 1) {
			// validate
			if (numWaiting == 0) {
				System.out.println("No users awaiting validation");
				enterWait();
				return phase;
			} else
				return executeValidate();

		} else if (input == 2) {
			clearConsole();
			printHeader("Here is the list of all users. I hope it is to your liking.");
			System.out.println(userController.summarizeAllUsers());
			enterWait();
			return phase;
		} else if (input == 3) {
			// grant priveleges
			// TODO

		} else if (input == 4) {
			// user menu
			return executeUserPhase();
		} else if (input == 5) {
			System.out.println(
					"Are you sure you would like to free data?\nThis will delete all users and user accounts.");
			printOptions(true, "Yes", "No");
			int input2 = getOption();

			if (input2 == 1) {
				MasterController.setFreeData();
				return Phase.Terminate;
			} else
				return phase;

		} else if (input == 0) {
			return Phase.Login;
		}

		return null;
	}

	/**
	 * Execution phase which prompts and processes input for Account Validation
	 * <p>
	 * Select User Account <br>
	 * Approve or Deny Account
	 * 
	 * @return next major program phase to execute in the main loop, will return the
	 *         current phase in the event of invalid input
	 */
	private Phase executeValidate() {

		String[] names = accountController.getWaitAccountNames();
		System.out.println("Please select and account to modify");
		printOptions(false, names);
		int waitIndex = getOption() - 1;

		if (waitIndex < 0 || waitIndex >= accountController.getWaitAccountCount()) {
			return null;
		} else {

			System.out.println("You have selected: " + names[waitIndex]);
			printOptions(true, "Approved", "Deny");
			int input = getOption();

			if (input == 1) {
				// approve
				Account acct = accountController.getWaitAccount(waitIndex);
				accountController.validateAccount(acct);
				System.out.println(names[waitIndex] + "'s user account has been approved.");
				enterWait();
				return Phase.UserControl;
			} else if (input == 0) {
				// deny
				System.out.println(names[waitIndex] + "'s was denied.");
				enterWait();
				return Phase.UserControl;
			}

		}

		System.out.println("no user selected");
		enterWait();

		return phase;
	}

	/**
	 * Prints strings that appear within a nicely formatted ascii window. Also makes
	 * a call {@link #clearConsole()} so that the header appears at the top of the
	 * console window.
	 * 
	 * @param strings
	 */
	private void printHeader(String... strings) {
		clearConsole();
		int w = WIN_W - 2;
		String border;
		StringBuilder sb = new StringBuilder("+");

		for (int i = 0; i < w; i++)
			sb.append('-');
		sb.append('+');

		border = sb.toString();
		System.out.println(border);

		for (String s : strings) {
			sb = new StringBuilder("| ");
			sb.append(s);
			for (int i = 0; i < w - s.length() - 1; i++)
				sb.append(' ');
			sb.append('|');

			System.out.println(sb.toString());
		}

		System.out.println(border);
		System.out.println();

	}

	/**
	 * Prints an array of strings each proceeded by an integer. The Strings a
	 * formatted for readability and numbered for user input. This command typically
	 * proceeds the {@link #getOption()} method
	 * 
	 * @param hasCancel
	 *            when true, the last string is assigned the option value of 0
	 * @param options
	 *            an array of strings to print as options
	 */
	private void printOptions(boolean hasCancel, String... options) {

		for (int i = 0; i < options.length; i++) {
			if ((i == options.length - 1) && hasCancel)
				System.out.println("-\n" + 0 + ")\t" + options[i]);
			else
				System.out.println((i + 1) + ")\t" + options[i]);
		}

	}

	/**
	 * Gets the next line of user input from System Input Stream and parses as an
	 * Integer.
	 * <p>
	 * Invalid input is converted to -1
	 * 
	 * @return integer from the command line
	 */
	private int getOption() {
		int input;

		try {
			input = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			input = -1;
		}

		return input;
	}

	/**
	 * Gets the next line of user input from System Input Stream and parses as a
	 * Double.
	 * <p>
	 * Invalid input is converted to 0.0
	 * 
	 * @return double from the command line
	 */
	private double getDouble() {
		double input;

		try {
			input = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			input = 0.0;
		}

		return input;
	}

	/**
	 * Runs a system process to clear the console. The command is specific to
	 * Windows machines
	 */
	private static void clearConsole() {
		if (!doClear)
			return;

		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs a system process to resize the console. The command is specific to
	 * Windows machines
	 */
	private static void resizeConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "mode con cols=" + WIN_W + " lines=" + WIN_H).inheritIO().start().waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a prompt for the user to press the 'Enter' key. The process is
	 * temporarily paused until a line of input can be read. Any additional line
	 * input is ignored.
	 */
	private void enterWait() {
		System.out.println("(press 'Enter' to continue)");
		scanner.nextLine();
	}

	/**
	 * 
	 * @author Austin
	 *
	 *         Enumeration used to represent the major phases of the program
	 */
	private enum Phase {
		Initialize, Login, UserControl, AccountControl, Terminate
	}
}
