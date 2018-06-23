package bank;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;

import static bank.BankDeserializer.getBank;

public class Driver {
	final static Logger logger = Logger.getRootLogger();
	Bank bank = getBank();
	
	public static Scanner reader = new Scanner(System.in);
	String nextLine;
	
	AdminAccount adminLoggedIn;
	UserAccount loggedIn;
	BankerAccount bankerLoggedIn;
	
	int mOpt = 7;
	int aOpt = 7;
	int adOpt = 7;
	
	public static void main(String[] args) {
//		UserAccount acc = new UserAccount("user", "Ray", "Rodriguez", "rrod", "cards", 2000);
//		AdminAccount adAcc = new AdminAccount("admin", "Jay", "Garcia", "jgar", "cards");
//		acc.DisplayAccDetails();
		Driver d = new Driver();
//		d.bank.getUserAcc().add(acc);
//		d.bank.getAdminAcc().add(adAcc);
		
		d.mainMenu();
		
		d.serializeBankInfo();
		
		reader.close();
	}
	/**
	 * This methods serializes the bank object which contains all the accounts
	 */
	public void serializeBankInfo() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bank.ser"));
			oos.writeObject(bank);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.fatal(e.getMessage());
		} finally {
			
		}
		
	}
	
	/**
	 * This methods hold the account menu prompts to display to the user
	 */
	public void accountMenu() {
		do {
			System.out.println("Please Choose an option 1-6: \n");
			System.out.println("1: Check Balance\n"
							 + "2: Withdraw\n"
							 + "3: Deposit\n"
							 + "4: Display Account Details\n"
							 + "5: Apply for loan\n"
							 + "6: Display Loan(s) Details\n"
							 + "0: Logout\n");
			
			nextLine = reader.nextLine();
			if (isNumeric(nextLine)) {
				aOpt = Integer.parseInt(nextLine);
				System.out.println();
			} else {
				System.out.println("Option not recognized.\n");
				logger.error("Wrong input");
			}
			
			switch(aOpt) {
			case 1:
				loggedIn.DisplayBalance();
				break;
			case 2:
				withdraw();
				break;
			case 3:
				deposit();
				break;
			case 4:
				loggedIn.DisplayAccDetails();
				break;
			case 5:
				applyForLoan();
				break;
			case 6:
				loggedIn.DisplayLoansDetails();
				break;
			}
		} while (aOpt != 0);
	}
	
	/**
	 * This method prompts the user for all the information needed for the user to apply for a loan,
	 * once the user has entered all the required information. The logged in user account instance's ApplyForLoan
	 * method will be called with all the prompted info as arguments
	 */
	public void applyForLoan() {
		double loanAmount = 0;
		int loanTerm = 0;
		System.out.println("Please enter loan amount: ");
		loanAmount = Double.parseDouble(reader.nextLine());
		System.out.println();
		System.out.println("Please enter loan term: ");
		loanTerm = Integer.parseInt(reader.nextLine());
		System.out.println();
		loggedIn.ApplyForLoan(loanAmount, loanTerm);
	}
	
	/**
	 * The deposit method will be called on the logged in userAccount instance
	 */
	public void deposit() {
		double amount = 0;
		System.out.println("Please enter amount to deposit: \n");
		amount = Double.parseDouble(reader.nextLine());
		loggedIn.Deposit(amount);
	}
	
	/**
	 * The withdraw method will be called on the logged in userAccount instance
	 */
	public void withdraw() {
		double amount = 0;
		System.out.println("Please enter amount to withdraw: \n");
		amount = Double.parseDouble(reader.nextLine());
		System.out.println();
		loggedIn.Withdrawal(amount);
	}
	
	/**
	 * This methods hold the main menu prompts to display to the user
	 */
	public void mainMenu() {
		do {
			System.out.println("Please Choose an option 1-2: \n");
			System.out.println("1: Login\n"
							 + "2: Sign up\n"
							 + "0: Exit App\n");

			nextLine = reader.nextLine();
			if (isNumeric(nextLine)) {
				mOpt = Integer.parseInt(nextLine);
				System.out.println();
			} else {
				System.out.println("Option not recognized.\n");
				logger.error("Wrong input");
			}
			
			switch(mOpt) {
			case 1:
				Login();
				break;
			case 2:
				Signup();
				break;
			}
		} while (mOpt != 0);
	}
	
	/**
	 * This methods holds the admin account menu prompts to display to the user
	 */
	public void adminAccMenu() {
		do {
			System.out.println("Please Choose an option 1-3: \n");
			System.out.println("1: Approve accounts\n"
							 + "2: Ban users\n"
							 + "3: Display users\n"
							 + "0: Logout\n");

			nextLine = reader.nextLine();
			if (isNumeric(nextLine)) {
				adOpt = Integer.parseInt(nextLine);
				System.out.println();
			} else {
				System.out.println("Option not recognized.\n");
				logger.error("Wrong input");
			}
			
			switch(adOpt) {
			case 1:
				adminLoggedIn.ApproveUsers(bank.userAccounts, reader);
				break;
			case 2:
				adminLoggedIn.BanUsers(bank.userAccounts, reader);
				break;
			case 3:
				adminLoggedIn.DisplayUsers(bank.userAccounts);
				break;
			}
		} while (adOpt != 0);
	}
	
	/**
	 * This methods holds the banker account menu prompts to display to the user
	 */
	public void bankerAccMenu() {
		do {
			System.out.println("Please Choose an option 1-3: \n");
			System.out.println("1: Approve user loans\n"
							 + "3: Display users\n"
							 + "0: Logout\n");

			nextLine = reader.nextLine();
			if (isNumeric(nextLine)) {
				adOpt = Integer.parseInt(nextLine);
				System.out.println();
			} else {
				System.out.println("Option not recognized.\n");
				logger.error("Wrong input");
			}
			
			switch(adOpt) {
			case 1:
				bankerLoggedIn.ApproveUserLoans(bank.userAccounts, reader);
				break;
			case 3:
				bankerLoggedIn.DisplayUsers(bank.userAccounts);
				break;
			}
		} while (adOpt != 0);
	}
	
	/**
	 * This method will prompt the user for their login info, the login info will be used to determine if
	 * the user is admin or user, and log them in accordingly. Also checks if the user exists or not, or if the user
	 * has been banned. If the user does not exist or is banned, he will not be allowed to enter the according menu
	 */
	public void Login() {
		String un;
		String pw;
		
		System.out.println("Please enter your Username: ");
		un = reader.nextLine();
		System.out.println();
		
		System.out.println("Please enter you Password: ");
		pw = reader.nextLine();
		System.out.println();
		
		Account key = new Account(un, pw);
		
		int index = -1;
		
		if (bank.userAccounts.indexOf(key) > -1) {
			index = bank.userAccounts.indexOf(key);
			loggedIn = bank.userAccounts.get(index);
			System.out.println(un + " logged in\n");
			
			if (!loggedIn.banned) {
				if (loggedIn.approved)
					accountMenu();
				else {
					System.out.println("This account has no been approved yet. Please contact an admin.\n");
					logger.info("unapproved account login attempt");
				}
			} else {
				System.out.println("This account has been banned. Please contact an admin.\n");
				logger.info("banned account login attempt");
				loggedIn = null;
			}
		} else if (bank.adminAccounts.indexOf(key) > -1) {
			index = bank.adminAccounts.indexOf(key);
			adminLoggedIn = bank.adminAccounts.get(index);
			System.out.println(un + " logged in\n");
			
			adminAccMenu();
		} else if (bank.bankerAccounts.indexOf(key) > -1) {
			index = bank.bankerAccounts.indexOf(key);
			bankerLoggedIn = bank.bankerAccounts.get(index);
			System.out.println(un + " logged in\n");
			
			bankerAccMenu();
		} else {
			System.out.println("User not found!\n");
			logger.warn("admin not found");
			loggedIn = null;
			adminLoggedIn = null;
		}
	}
	
	/**
	 * This method will prompt the user with the information needed to sign up, once the user signs up
	 * the account will be labeled unapproved for later approval of an admin
	 */
	public void Signup() {
		String accType;
		String fName;
		String lName;
		String un;
		String pw;
		
		System.out.println("Please enter your first name: ");
		fName = reader.nextLine();
		System.out.println();
		
		System.out.println("Please enter your last name: ");
		lName = reader.nextLine();
		System.out.println();
		
		System.out.println("Please enter an account type: ");
		accType = reader.nextLine();
		accType = accType.toLowerCase();
		System.out.println();
		
		System.out.println("Please enter a username: ");
		un = reader.nextLine();
		System.out.println();
		
		System.out.println("Please enter a password: ");
		pw = reader.nextLine();
		System.out.println();
		
		UserAccount user;
		AdminAccount admin;
		BankerAccount banker;
		
		switch(accType) {
		case "user":
			user = new UserAccount(accType, fName, lName, un, pw);
			bank.userAccounts.add(user);
			break;
		case "admin":
			admin = new AdminAccount(accType, fName, lName, un, pw);
			bank.adminAccounts.add(admin);
			break;
		case "banker":
			banker = new BankerAccount(accType, fName, lName, un, pw);
			bank.bankerAccounts.add(banker);
			break;
		}
		
		System.out.println(un + " signed up\n");
	}

	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}
