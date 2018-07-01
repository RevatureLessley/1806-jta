package Project0_PartII.RevatureAccounts;

import java.io.*;
import java.sql.*;
import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.AccountAttributes.*;

/**
 * UserAccount encapsulates the logic of a user account.
 */
public class UserAccount extends Account 
						 implements LogReference, Serializable {
	private static final long serialVersionUID = -1329930768642177273L;

	/**
	 * This constructor initializes the attributes and actions of a user 
	 * account.
	 */
	public UserAccount() {
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
                	 "Constructing UserAccount().");
		new Username(this);
		new Password(this);
		new FirstName(this);
		new LastName(this); 
		new Balance(this);
		new Status(this);
		actions.add((Runnable & Serializable)() -> makeDeposit());
		actions.add((Runnable & Serializable)() -> makeWithdrawal());
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
           	 		 "Constructed UserAccount().");
	}
	
	public UserAccount(ResultSet rs) throws SQLException {
		new Username(this, rs);
		new Password(this, rs);
		new FirstName(this, rs);
		new LastName(this, rs); 
		new Balance(this, rs);
		new Status(this, rs);
		actions.add((Runnable & Serializable)() -> makeDeposit());
		actions.add((Runnable & Serializable)() -> makeWithdrawal());
	}

	/**
	 * approved() displays the options of an APPROVED account.
	 */
	@Override
	public void approved() {
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
           	 		 "Entered approved().");
		Integer action;

		do {
			String firstname = attributes.get("Firstname").get();
			System.out.println("Hi " + firstname + ", welcome to your account."
					          );
			System.out.println("What would you like to do?");
			System.out.println("[0]:\tSignout");
			System.out.println("[1]:\tMake a Deposit");
			System.out.println("[2]:\tMake a Withdrawal");
			String input = askUser("[0-2]");
			action = Integer.valueOf(input);
			actions.get(action).run();
		} while(action.compareTo(0) != 0);
		
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
      	 		 	 "Exiting approved().");
	}

	/**
	 * denied() displays a DENIED account.
	 */
	@Override
	public void denied() {
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
      	 		 	 "Entered denied().");
		String firstname = attributes.get("Firstname").get();
		System.out.println("Hi " + firstname + ", unfortunately your " + 
						   "account has been denied by the admin. We " + 
				           "apologize for the inconvenience.");
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
 	 		 	 	 "Exiting denied().");
	}

	/**
	 * enter() chooses the appropriate view of the account to display.
	 */
	@Override
	public void enter() {
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
 	 		 	 	 "Entered entered().");
		attributes.get("Status").display(this);
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
 		 	 	 "Exiting entered().");
	}

	/**
	 * makeDeposit() contains the logic for making a deposit.
	 */
	private void makeDeposit() {
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
 		 	 	 	 "Entered makeDeposit().");
		attributes.get("Balance").deposit();
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
					 "Exiting makeDeposit().");
	}

	/**
	 * makewithDrawal() contains the logic for making a withdrawal.
	 */
	private void makeWithdrawal() {
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Entered makeWithdrawal().");
		attributes.get("Balance").withdraw();
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Exiting makeWithdrawal().");
	}

	/**
	 * pending() displays a PENDING account.
	 */
	@Override
	public void pending() {
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Entered pending().");
		String firstname = attributes.get("Firstname").get();
		System.out.println("Hi " + firstname + ", thank you for creating an " +
		                   "account. Your account is awaiting approval from " + 
						   "the admin. We apologize for the inconvenience.");
		logger.debug("Project0_PartII/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Exiting pending().");
	}

}
