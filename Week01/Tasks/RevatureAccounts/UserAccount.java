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
		status = AccountStatus.PENDING;
		actions.add((Runnable & Serializable)() -> makeDeposit());
		actions.add((Runnable & Serializable)() -> makeWithdrawal());
	}

	@Override
	public void enter() {
		String firstname = attributes.get("Firstname").get();
		System.out.println("Hi " + firstname + ", welcome to your account.");
		System.out.println("What would you like to do?");
		System.out.println("Signout           [0]:");
		System.out.println("Make a Deposit    [1]:");
		System.out.println("Make a Withdrawal [2]:");
		actions.get(askUser("[0-2]")).run();
	}

	private void makeDeposit() {
		attributes.get("Balance").deposit();
	}

	private void makeWithdrawal() {	
		attributes.get("Balance").withdraw();
	}
}
