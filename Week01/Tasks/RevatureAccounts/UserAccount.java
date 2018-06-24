package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public class UserAccount extends Account implements Serializable {

 	public UserAccount() {
		new Username(this);
		new Password(this);
		new FirstName(this);
		new LastName(this); 
		new Balance(this);
		new Status(this);
		actions.add((Runnable & Serializable)() -> makeDeposit());
		actions.add((Runnable & Serializable)() -> makeWithdrawal());
	}

	@Override
	public void approved() {
		String firstname = attributes.get("Firstname").get();
		System.out.println("Hi " + firstname + ", welcome to your account.");
		System.out.println("What would you like to do?");
		System.out.println("Signout           [0]:");
		System.out.println("Make a Deposit    [1]:");
		System.out.println("Make a Withdrawal [2]:");
		actions.get(askUser("[0-2]")).run();
	}

	@Override
	public void denied() {	
		String firstname = attributes.get("Firstname").get();
		System.out.print("Hi " + firstname + ", " );
		System.out.print("unfortunately your account has been denied by the admin. ");
		System.out.println("We apologize for the inconvenience.");
	}

	@Override
	public void enter() {
		attributes.get("Status").display(this);
	}

	private void makeDeposit() {
		attributes.get("Balance").deposit();
	}

	private void makeWithdrawal() {	
		attributes.get("Balance").withdraw();
	}

	@Override
	public void pending() {	
		String firstname = attributes.get("Firstname").get();
		System.out.print("Hi " + firstname + ", thank you for creating an account. " );
		System.out.print("Your account is awaiting approval from the admin. ");
		System.out.println("We apologize for the inconvenience.");
	}

}
