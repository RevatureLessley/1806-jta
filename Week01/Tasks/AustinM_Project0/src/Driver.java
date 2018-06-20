import java.io.IOException;
import java.util.Scanner;

public class Driver {

	private UserController userController;
	private Phase phase = Phase.Initialize;
	private User activeUser;
	private Account activeAccount;

	private static Scanner scanner;

	public static void main(String[] args) {

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
	}

	private Phase executeAccountPhase() {
		System.out.println(activeAccount.getBalance());
		printOptions("Withdraw", "Deposit", "Exit");
		int input = getOption();
		
		if(input == 0) {
			System.out.println("withdraw amount: ");
			double amt = getDouble();
			
			activeAccount.withdraw(amt);
			
		}else if(input == 1) {
			System.out.println("deposit amount: ");
			double amt = getDouble();
			
			activeAccount.deposit(amt);
		}else if(input == 2) {
			return Phase.UserControl;
		}
		
		return phase;
	}

	private Phase executeInitPhase() {
		userController = new UserController();
		return Phase.Login;
	}

	private Phase executeLoginPhase() {

		printOptions("Login", "Register", "Quit");
		int input = getOption();

		if (input == 0) {
			// Login
			System.out.print("enter username: ");
			String name = scanner.nextLine();
			System.out.print("enter password: ");
			String password = scanner.nextLine();

			User user = userController.getUser(name);
			if (user == null) {
				System.out.println("Invalid username");
				return phase;
			} else {
				if (user.validateLogin(password)) {
					activeUser = user;
					return Phase.UserControl;
				} else {
					System.out.println("Invalid password");
					return phase;
				}

			}

		} else if (input == 1) {
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

		} else if (input == 2) {
			return Phase.Terminate;
		}

		return null;
	}

	private Phase executeUserPhase() {

		printOptions("Account Summary", "Select Account", "Open New Account", "Logout");
		int input = getOption();

		if (input == 0) {
			System.out.println(activeUser.accountSummary());
			return phase;
		} else if (input == 1) {
			return executeUserSelectAccount();
		} else if (input == 2) {
			return executeUserOpenAccount();
		} else if (input == 3) {
			return Phase.Login;
		}

		return null;
	}

	private Phase executeUserOpenAccount() {
		System.out.print("Enter an account name: ");
		String name = scanner.nextLine();

		System.out.println("Select an account type ");
		printOptions("Checking", "Savings", "Cancel");
		int input = getOption();

		if (input == 0 || input == 1) {
			activeUser.addAccount(name, input);
			System.out.println("Account '" + name + "' successfully created.");
		}

		return Phase.UserControl;
	}

	private Phase executeUserSelectAccount() {
		System.out.println("Select an account");
		String[] acctNames = activeUser.getAccountNames();
		
		if(acctNames.length == 0) {
			System.out.println("No accounts available");
			enterWait();
			return phase;
		}
		
		printOptions(acctNames);
		int input = getOption();
		
		Account a = activeUser.getAccount(input);
		
		if(a != null) {
			activeAccount = a;
			System.out.println(a.getName() + " selected");
			enterWait();
			return Phase.AccountControl;
		}
				
		return null;
	}

	private Phase executeAdminPhase() {

		int numWaiting = userController.getWaitUserCount();

		printOptions("Validate Users: " + numWaiting + " waiting", "ban user", "Grant Admin Priveleges", "User Menu",
				"Logout");
		int input = getOption();

		if (input == 0) {
			// validate
			if (numWaiting == 0) {
				System.out.println("No users awaiting validation");
				enterWait();
				return phase;
			} else
				return executeValidate();

		} else if (input == 1) {
			// ban
			// TODO
		} else if (input == 2) {
			// grant priveleges
			// TODO
		} else if (input == 3) {
			// user menu
			return executeUserPhase();
		} else {
			return Phase.Login;
		}

		return null;
	}

	private Phase executeValidate() {

		String[] names = userController.getWaitUserNames();
		System.out.println("Select a waiting user");
		printOptions(names);
		int waitIndex = getOption();

		if (waitIndex < 0 || waitIndex >= userController.getWaitUserCount()) {
			return null;
		} else {

			System.out.println("You have selected: " + names[waitIndex]);
			printOptions("Approved", "Deny");
			int input = getOption();

			if (input == 0) {
				// approve
				User user = userController.getWaitUser(waitIndex);
				userController.validateNewUser(user);
				System.out.println(names[waitIndex] + "'s user profile has been approved.");
				enterWait();
				return Phase.UserControl;
			} else if (input == 1) {
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

	private void printOptions(String... options) {

		for (int i = 0; i < options.length; i++) {
			System.out.println(i + ")\t" + options[i]);
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

	private enum Phase {
		Initialize, Login, UserControl, AccountControl, Terminate
	}
}
