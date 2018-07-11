package com.revature.project0;


import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project0.dao.AccountDao;
import com.revature.project0.dao.AccountDaoImpl;
import com.revature.project0.dao.UserDao;
import com.revature.project0.dao.UserDaoImpl;

public class EntryClass {
	public static Logger logger = Logger.getLogger(EntryClass.class);
	static Scanner in = null;

	public static void main(String[] args) throws Exception{
		/*Connection conn = Connections.getConnection();
		if(conn!=null){
			conn.close();
		}*/
		mainFlow();
	}
	/**
	 *Main flow allows login and account creation.
	 */
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
	/**
	 * Method handles login and subsequent features that differs between Admin and
	 * regular User. Allows Admin to activate and inactivate users. Allows User to 
	 * deposit, withdrawal, and check balance.
	 */
	private static void loginFlow() {
		in = new Scanner(System.in);
		System.out.println("Enter Username:");
		String username = in.nextLine();
		System.out.println("Enter Password:");
		String password = in.nextLine();
		logger.info(username + " " + password);
		//User u1 = Utility.retrieveUser(username);
		UserDao userDao = new UserDaoImpl();
		User u1 = userDao.selectUserByName(username);
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
	/**
	 * Called when the withdrawal option is selected on the User menu. Withdraws
	 * the amount entered from the user's account given sufficent funds.
	 * @param u1 the User Object that is persisted from .ser file.
	 */
	private static void userWithdraw(User u1) {
		System.out.println("Enter Withdrawel Amount:");
		double withAmnt = in.nextDouble();
		AccountDao accDao = new AccountDaoImpl();
		Account acc = accDao.getAccountByUser(u1.getUserid()).get(0);
		if (acc.isActive()) {
			acc.withdraw(withAmnt);
			accDao.updateAccount(acc);
			logger.info(acc.getAccountId() + " | " + acc.getBal());
		} else  {
			System.out.println("You account is not activated, please contact admin.");
		}
	}
	/**
	 * Called when the deposit option is selected on the User menu. Deposits
	 * the amount entered into the user's account.
	 * @param u1 the User Object that is persisted from .ser file.
	 */
	private static void userDeposit(User u1) {
		System.out.println("Enter Deposit Amount:");
		AccountDao accDao = new AccountDaoImpl();
		Account acc = accDao.getAccountByUser(u1.getUserid()).get(0);	
		double depAmnt = in.nextDouble();
		if (acc.isActive()) {
			acc.deposit(depAmnt);
			accDao.updateAccount(acc);
			//Utility.persistObj(u1);		
			logger.info(acc.getAccountId() + " | " + acc.getBal());
		} else {
			System.out.println("You account is not activated, please contact admin.");
		}
	}
	
	/**
	 * Called when the check balance option is selected on the User menu. Checks
	 * the amount available for withdrawal.
	 * @param u1 the User Object that is persisted from .ser file.
	 */
	private static void userBalanceCheck(User u1) {
		AccountDao accDao = new AccountDaoImpl();
		Account acc = accDao.getAccountByUser(u1.getUserid()).get(0);
		if (acc.isActive()) {
			System.out.println("Your balance is: $ " + acc.getBal());
			logger.info(acc.getAccountId() + " | " + acc.getBal());
		} else  {
			System.out.println("You account is not activated, please contact admin.");
		}
	}
	/**
	 * Called when the approve account option is selected on the Admin menu. Activates
	 * and account for a non-Admin User. 
	 */
	private static void adminApproveAcc() {
		in = new Scanner(System.in);
		System.out.println("Enter Account to Approve:");
		String userAccountName = in.nextLine();
		AccountDao accDao = new AccountDaoImpl();
		Account acc = accDao.getAccountByUserName(userAccountName).get(0);
		acc.setActive(Boolean.TRUE);
		accDao.updateAccount(acc);
		//Utility.persistObj(u2);
		System.out.println("Account is approved:" + userAccountName);
	}
	/**
	 * Called when the ban account option is selected on the Admin menu. Inactivates
	 * and account for a non-Admin User. 
	 */
	private static void adminBanAcc() {
		in = new Scanner(System.in);
		System.out.println("Enter Account to Ban:");
		String userAccountName = in.nextLine();
		AccountDao accDao = new AccountDaoImpl();
		Account acc = accDao.getAccountByUserName(userAccountName).get(0);
		acc.setActive(Boolean.FALSE);
		//Utility.persistObj(u2);
		accDao.updateAccount(acc);
		System.out.println("Account is Inactivated:" + userAccountName);
	}
	/**
	 * Account creation method.
	 */
	private static void createAccount() {
		in = new Scanner(System.in);
		System.out.println("Enter new Username:");
		String username = in.nextLine();
		System.out.println("Enter new Password:");
		String password = in.nextLine();
		System.out.println("Admin? (True/False):");
		boolean isAdmin = in.nextBoolean();
		logger.info(username + " " + password);
		User u = new User(username, password, isAdmin);
		UserDao userDao = new UserDaoImpl();
		Integer userId = userDao.insertUser(u);
		Account acc = new Account();
		AccountDao accDao = new AccountDaoImpl();
		Integer accId = accDao.insertAccount(acc);
		userDao.saveUserAccount(userId, accId);
		logger.info(u.getUsername());
		//Utility.persistObj(u);
	}
	/**
	 * The entry menu that allows for account creation and logging in.
	 */
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
