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
		status = AccountStatus.PENDING;
	}

	@Override
	public void enter() {
		//String firstname = attributes.get("Firstname");
		System.out.println("Hi, welcome to your account.");
		System.out.println("What would you like to do?");
		System.out.println("Signout           [1]:");
		System.out.println("Make a Deposit    [2]:");
		System.out.println("Make a Withdrawal [3]:");
		String action = console.readLine("> "); // NOTE: Check this!!!
		UserAccountActions uaa[] = UserAccountActions.values();
		uaa[1].display();
	}
}
