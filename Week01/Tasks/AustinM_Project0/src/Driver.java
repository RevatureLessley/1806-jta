import java.io.IOException;
import java.util.Scanner;

public class Driver {

	private UserController userController;
	private Phase phase = Phase.Initialize;
	private User activeUser;

	public static void main(String[] args) {

		Driver d = new Driver();

		Scanner scanner = new Scanner(System.in);
		Phase newPhase = null;

		while (d.phase != Phase.Terminate) {

			//d.clearConsole();

			if (d.phase == Phase.Initialize) {
				newPhase = d.executeInitPhase();

			} else if (d.phase == Phase.Login) {
				newPhase = d.executeLoginPhase(scanner);

			} else if (d.phase == Phase.UserControl) {
				if (d.activeUser.isAdmin())
					newPhase = d.executeAdminPhase(scanner);
				else
					newPhase = d.executeUserPhase(scanner);

			} else if (d.phase == Phase.AccountControl) {

			}

			if (newPhase != null)
				d.phase = newPhase;
			else {
				System.out.println("Invalid Input");
				d.wait(scanner);
			}
			
		}
		
		scanner.close();
	}

	private Phase executeInitPhase() {
		userController = new UserController();
		return Phase.Login;
	}

	private Phase executeLoginPhase(Scanner scanner) {

		printOptions("Login", "Register", "Quit");
		int input = getOption(scanner);

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
			wait(scanner);
			return phase;

		} else if (input == 2) {
			return Phase.Terminate;
		}

		return null;
	}

	private Phase executeUserPhase(Scanner scanner) {

		printOptions("Select Account", "Open New Account", "Logout");
		int input = getOption(scanner);

		if (input == 0) {
			return executeUserSelectAccount(scanner);
		} else if (input == 1) {
			return executeUserOpenAccount(scanner);
		} else if (input == 2) {
			return Phase.Login;
		}

		return null;
	}

	private Phase executeUserOpenAccount(Scanner scanner) {
		System.out.print("Enter an account name: ");
		String name = scanner.nextLine();

		System.out.println("Select an account type ");
		printOptions("Checking", "Savings", "Cancel");
		int input = getOption(scanner);

		if (input == 0 || input == 1) {
			activeUser.addAccount(name, input);
			System.out.println("Account '" + name + "' successfully created.");
		}

		return Phase.UserControl;
	}

	private Phase executeUserSelectAccount(Scanner scanner) {
		System.out.println("Select an account");
		String[] acctNames = activeUser.getAccountNames();
		printOptions(acctNames);
		int input = getOption(scanner);

		return null;
	}

	private Phase executeAdminPhase(Scanner scanner) {

		int numWaiting = userController.getWaitUserCount();

		printOptions("Validate Users: " + numWaiting + " waiting", "ban user", "Grant Admin Priveleges", "User Menu",
				"Logout");
		int input = getOption(scanner);

		if (input == 0) {
			// validate
			return executeValidate(scanner);
		} else if (input == 1) {
			// ban
			//TODO
		} else if (input == 2) {
			// grant priveleges
			//TODO
		} else if (input == 3) {
			// user menu
			return executeUserPhase(scanner);
		} else {
			return Phase.Login;
		}

		return null;
	}

	private Phase executeValidate(Scanner scanner) {
		
		String[] names = userController.getWaitUserNames();
		System.out.println("Select a waiting user");
		printOptions(names);
		int waitIndex = getOption(scanner);
		
		if(waitIndex < 0 || waitIndex >= userController.getWaitUserCount()) {
			return null;
		}else {
			
			System.out.println("You have selected: " + names[waitIndex]);
			printOptions("Approved", "Deny");
			int input = getOption(scanner);
			
			if(input == 0) {
				//approve
				User user = userController.getWaitUser(waitIndex);
				userController.validateNewUser(user);
				System.out.println(names[waitIndex]+"'s user profile has been approved.");
				return Phase.UserControl;
			}else if(input == 1){
				//deny
				System.out.println(names[waitIndex]+"'s was denied.");
				return Phase.UserControl;
			}
				
		}
		
		
		return null;
	}

	private void printOptions(String... options) {

		for (int i = 0; i < options.length; i++) {
			System.out.println(i + ")\t" + options[i]);
		}

	}

	private int getOption(Scanner scanner) {
		int input;

		try {
			input = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			input = -1;
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

	private void wait(Scanner scanner) {
		System.out.println("(press 'Enter' to continue)");
		scanner.nextLine();
	}

	private enum Phase {
		Initialize, Login, UserControl, AccountControl, Terminate
	}
}
