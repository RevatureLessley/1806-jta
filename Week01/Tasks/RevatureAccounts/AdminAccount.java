package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public class AdminAccount extends Account implements Serializable {
	public static AdminAccount admin = new AdminAccount();

  	private AdminAccount() {
  		new Username(this);
  		new Password(this);
  		new FirstName(this);
  		new LastName(this);
  		new Status(this);
		actions.add((Runnable & Serializable)() -> evaluateAccounts());
 	}	

 	@Override
	public void enter() {
  		System.out.println("Signed in as admin. There are x unapproved accounts.");
  		System.out.println("What would you like to do?");
  		System.out.println("Signout          [0]:");
  		System.out.println("Approve Accounts [1]:");
		actions.get(askUser("[0-1]")).run();
 	}

	public void evaluateAccounts() {
		System.out.println("Evaluating accounts.");
	}
}
