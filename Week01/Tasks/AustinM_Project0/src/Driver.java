import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Driver {

	private static final String USER_DATA_FILE = "userdata.dat";

	private UserController userController;
	private Phase phase = Phase.Initialize;
	private User activeUser;
	private Account activeAccount;
	private static boolean doClear = true;
	private static Scanner scanner;

	private static final int WIN_W = 100, WIN_H = 26;

	public static void main(String[] args) {

		resizeConsole();

		if (args.length > 0) {
			if (args[0].equals("noclear"))
				doClear = false;
		}

		Driver d = new Driver();

		scanner = new Scanner(System.in);
		Phase newPhase = null;

		while (d.phase != Phase.Terminate) {

			// d.clearConsole();

			if (d.phase == Phase.Initialize) {
				newPhase = d.executeInitPhase();

			} else if (d.phase == Phase.Login) {
				newPhase = d.executeLoginPhase();

			} else if (d.phase == Phase.UserControl) {
				if (d.activeUser.isAdmin())
					newPhase = d.executeAdminPhase();
				else
					newPhase = d.executeUserPhase();

			} else if (d.phase == Phase.AccountControl) {
				newPhase = d.executeAccountPhase();
			}

			if (newPhase != null)
				d.phase = newPhase;
			else {
				System.out.println("Invalid Input");
				d.enterWait();
			}

		}

		scanner.close();
		d.writeData();
	}

	private Phase executeInitPhase() {

		if (!readData())
			userController = new UserController();

		return Phase.Login;
	}

	private Phase executeLoginPhase() {
		printHeader("Welcome to the Westward Banking Service", "Now is the time to consume");
		printOptions(true, "Login", "Register", "Quit");
		int input = getOption();

		if (input == 1) {
			// Login
			System.out.print("enter username: ");
			String name = scanner.nextLine();
			System.out.print("enter password: ");
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
				}else if (logVal == -1) {
					System.out.println("Please wait for an Administrator to validate your account.");
					enterWait();
					return phase;
				}else {
					System.out.println("Invalid password");
					enterWait();
					return phase;
				}

			}

		} else if (input == 2) {
			// Register
			System.out.print("enter username: ");
			String name = scanner.nextLine();

			if (!userController.checkUsernameAvailable(name)) {
				System.out.println("username '" + name + "' is unavailable");
				return phase;
			}

			System.out.print("enter password: ");
			String password = scanner.nextLine();

			userController.addUser(name, password);
			System.out.println(
					"User profile was created for '" + name + "' - an admin will validate your account shortly");
			enterWait();
			return phase;

		} else if (input == 0) {
			return Phase.Terminate;
		}

		return null;
	}

	private Phase executeUserPhase() {

		printHeader("Welcome, " + activeUser.getName() + ".",
				"Your total balance: " + Account.formatCurrency(activeUser.totalBalance()));

		printOptions(true, "Account Summary", "Select Account", "Open New Account", "Logout");
		int input = getOption();

		if (input == 1) {
			System.out.println(activeUser.accountSummary());
			enterWait();
			return phase;
		} else if (input == 2) {
			return executeUserSelectAccount();
		} else if (input == 3) {
			return executeUserOpenAccount();
		} else if (input == 0) {
			return Phase.Login;
		}

		return null;
	}

	private Phase executeUserOpenAccount() {

		System.out.println("Select an account type ");
		printOptions(true, "Checking", "Savings", "Cancel");
		int input = getOption();

		if (input == 1 || input == 2) {
			String name = Account.getTypeName(input);
			Account a = activeUser.addAccount(name, input);
			
			System.out.println("Account '" + name + "' successfully created.\nWould you like to access this account now?");
			printOptions(true, "Yes", "No");
			input = getOption();
			
			if(input == 0) {
				activeAccount = a;
				phase = Phase.AccountControl;
			}
			
		}

		return Phase.UserControl;
	}

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
			activeAccount = a;
			return Phase.AccountControl;
		}

		return null;
	}

	private Phase executeAccountPhase() {
		printHeader(activeAccount.toString(), "");

		printOptions(true, "Withdraw", "Deposit", "Exit");
		int input = getOption();

		if (input == 1) {
			System.out.print("withdraw amount: ");
			double amt = getDouble();

			activeAccount.withdraw(amt);

		} else if (input == 2) {
			System.out.print("deposit amount: ");
			double amt = getDouble();

			activeAccount.deposit(amt);
		} else if (input == 0) {
			return Phase.UserControl;
		}

		return phase;

	}

	private Phase executeAdminPhase() {

		int numWaiting = userController.getWaitUserCount();

		printHeader("Good evening, Sir " + activeUser.getName(), "How may I serve you today?");
		printOptions(true, "Validate Users: " + numWaiting + " waiting", "ban user", "Grant Admin Priveleges",
				"User Menu", "Show All Users", "Logout");
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
			// ban
			// TODO
		} else if (input == 3) {
			// grant priveleges
			// TODO

		} else if (input == 4) {
			// user menu
			return executeUserPhase();
		} else if (input == 5) {
			clearConsole();
			printHeader("Here is the list of all users. I hope it is to your liking.");
			System.out.println(userController.summarizeAllUsers());
			enterWait();
			return phase;

		} else {
			return Phase.Login;
		}

		return null;
	}

	private Phase executeValidate() {

		String[] names = userController.getWaitUserNames();
		System.out.println("Select a waiting user");
		printOptions(false, names);
		int waitIndex = getOption() - 1;

		if (waitIndex < 0 || waitIndex >= userController.getWaitUserCount()) {
			return null;
		} else {

			System.out.println("You have selected: " + names[waitIndex]);
			printOptions(true, "Approved", "Deny");
			int input = getOption();

			if (input == 1) {
				// approve
				User user = userController.getWaitUser(waitIndex);
				userController.validateNewUser(user);
				System.out.println(names[waitIndex] + "'s user profile has been approved.");
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

	private void printHeader(String... strings) {
		clearConsole();
		int w = WIN_W - 2;
		String header, border;
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

	private static void resizeConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "mode con cols=" + WIN_W + " lines=" + WIN_H).inheritIO().start().waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printOptions(boolean hasCancel, String... options) {

		for (int i = 0; i < options.length; i++) {
			if ((i == options.length - 1) && hasCancel)
				System.out.println("-\n" + 0 + ")\t" + options[i]);
			else
				System.out.println((i + 1) + ")\t" + options[i]);
		}

	}

	private int getOption() {
		int input;

		try {
			input = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			input = -1;
		}

		return input;
	}

	private double getDouble() {
		double input;

		try {
			input = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			input = 0.0;
		}

		return input;
	}

	private void clearConsole() {
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

	private void enterWait() {
		System.out.println("(press 'Enter' to continue)");
		scanner.nextLine();
	}

	private void writeData() {

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE));
			oos.writeObject(userController);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (oos != null)
				oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean readData() {

		ObjectInputStream ois = null;
		boolean result = false;

		try {
			ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE));
			userController = (UserController) ois.readObject();
			result = true;
		} catch (FileNotFoundException e) {
			System.out.println("no previous user data");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			if (ois != null)
				ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	private enum Phase {
		Initialize, Login, UserControl, AccountControl, Terminate
	}
}
