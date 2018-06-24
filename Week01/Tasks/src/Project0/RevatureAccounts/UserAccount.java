package Project0.RevatureAccounts;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.AccountAttributes.*;

public class UserAccount extends Account 
						 implements LogReference, Serializable {
	private static final long serialVersionUID = -1329930768642177273L;

	public UserAccount() {
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
                	 "Constructing UserAccount().");
		new Username(this);
		new Password(this);
		new FirstName(this);
		new LastName(this); 
		new Balance(this);
		new Status(this);
		actions.add((Runnable & Serializable)() -> makeDeposit());
		actions.add((Runnable & Serializable)() -> makeWithdrawal());
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
           	 		 "Constructed UserAccount().");
	}

	@Override
	public void approved() {
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
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
		
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
      	 		 	 "Exiting approved().");
	}

	@Override
	public void denied() {
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
      	 		 	 "Entered denied().");
		String firstname = attributes.get("Firstname").get();
		System.out.println("Hi " + firstname + ", unfortunately your " + 
						   "account has been denied by the admin. We " + 
				           "apologize for the inconvenience.");
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
 	 		 	 	 "Exiting denied().");
	}

	@Override
	public void enter() {
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
 	 		 	 	 "Entered entered().");
		attributes.get("Status").display(this);
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
 		 	 	 "Exiting entered().");
	}

	private void makeDeposit() {
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
 		 	 	 	 "Entered makeDeposit().");
		attributes.get("Balance").deposit();
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
					 "Exiting makeDeposit().");
	}

	private void makeWithdrawal() {
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Entered makeWithdrawal().");
		attributes.get("Balance").withdraw();
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Exiting makeWithdrawal().");
	}

	@Override
	public void pending() {
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Entered pending().");
		String firstname = attributes.get("Firstname").get();
		System.out.println("Hi " + firstname + ", thank you for creating an " +
		                   "account. Your account is awaiting approval from " + 
						   "the admin. We apologize for the inconvenience.");
		logger.debug("Project0/RevatureAccounts/UserAccount.java: " + 
	 	 	 	 	 "Exiting pending().");
	}

}
