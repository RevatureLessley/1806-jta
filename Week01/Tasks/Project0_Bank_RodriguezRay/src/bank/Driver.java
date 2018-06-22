package bank;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Driver {
	public static Scanner reader = new Scanner(System.in);
	String nextLine;
	List<UserAccount> userAccounts = new ArrayList<UserAccount>();
	UserAccount loggedIn;
	int mOpt = 7;
	int aOpt = 7;
	
	public static void main(String[] args) {
		UserAccount acc = new UserAccount("user", "Ray", "Rodriguez", "rrod", "cards", 2000);
//		acc.DisplayAccDetails();
		Driver d = new Driver();
		d.userAccounts.add(acc);
		
		d.mainMenu();
		
		reader.close();
	}
	
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
	
	public void deposit() {
		double amount = 0;
		System.out.println("Please enter amount to deposit: \n");
		amount = Double.parseDouble(reader.nextLine());
		loggedIn.Deposit(amount);
	}
	
	public void withdraw() {
		double amount = 0;
		System.out.println("Please enter amount to withdraw: \n");
		amount = Double.parseDouble(reader.nextLine());
		System.out.println();
		loggedIn.Withdrawal(amount);
	}
	
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
			}
			
			switch(mOpt) {
			case 1:
				Login();
				break;
			case 2:
				Signup();
			}
		} while (mOpt != 0);
	}
	
	public void Login() {
		String un;
		String pw;
		
		System.out.println("Please enter your Username: ");
		un = reader.nextLine();
		System.out.println();
		
		System.out.println("Please enter you Password: ");
		pw = reader.nextLine();
		System.out.println();
		
		UserAccount key = new UserAccount(un, pw);
		
		int index = userAccounts.indexOf(key);
		
		if (index > -1) {
			loggedIn = userAccounts.get(index);
			System.out.println(un + " logged in\n");
			accountMenu();
		} else {
			System.out.println("User not found!\n");
			loggedIn = null;
		}
	}
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
		System.out.println();
		
		System.out.println("Please enter a username: ");
		un = reader.nextLine();
		System.out.println();
		
		System.out.println("Please enter a password: ");
		pw = reader.nextLine();
		System.out.println();
		
		UserAccount user = new UserAccount(accType, fName, lName, un, pw);
		userAccounts.add(user);
		System.out.println(un + " signed up\n");
	}

	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}
