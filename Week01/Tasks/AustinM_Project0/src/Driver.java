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

			// clearConsole();

			if (d.phase == Phase.Initialize) {
				newPhase = d.executeInitPhase();

			} else if (d.phase == Phase.Login) {
				newPhase = d.executeLoginPhase(scanner);

			} else if (d.phase == Phase.UserControl) {

			} else if (d.phase == Phase.AccountControl) {

			}

			if (newPhase != null)
				d.phase = newPhase;
		}
	}

	private Phase executeInitPhase() {
		userController = new UserController();
		return Phase.Login;
	}

	private Phase executeLoginPhase(Scanner scanner) {

		String[] options = { "Login", "Register", "Quit" };

		printOptions(options);
		int input = Integer.parseInt(scanner.nextLine());

		if (input == 0) {
			// Login
			System.out.print("enter username: ");
			String name = scanner.nextLine();
			System.out.print("enter password: ");
			String password = scanner.nextLine();

			User user = userController.getUser(name);
			if (user == null) {
				System.out.println("Invalid username");
				return null;
			} else {
				if (user.validateLogin(password)) {
					activeUser = user;
					return Phase.AccountControl;
				} else {
					System.out.println("Invalid password");
					return null;
				}

			}

		} else if (input == 1) {
			// Register
			System.out.print("enter username: ");
			String name = scanner.nextLine();

			if (!userController.checkUsernameAvailable(name)) {
				System.out.println("username '" + name + "' is unavailable");
				return null;
			}

			System.out.print("enter password: ");
			String password = scanner.nextLine();

			userController.addUser(name, password);
			System.out.println("User profile was created - an admin will validate your account shortly");
			scanner.nextLine();

		} else if (input == 2) {
			return Phase.Terminate;
		}

		return null;
	}

	private Phase executeUserPhase(Scanner scanner) {

		String[] options = {"Select Account", "Logout"};

		printOptions(options);
		int input = Integer.parseInt(scanner.nextLine());

		return null;
	}
	
	private Phase executeAdminPhase(Scanner scanner) {

		String[] options =  {"Validate Accounts", "Logout"};

		printOptions(options);
		int input = Integer.parseInt(scanner.nextLine());

		return null;
	}

	private void printOptions(String[] options) {

		for (int i = 0; i < options.length; i++) {
			System.out.println(i + ")\t" + options[i]);
		}

	}

	private enum Phase {
		Initialize, Login, UserControl, AccountControl, Terminate
	}

	public static void clearConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
