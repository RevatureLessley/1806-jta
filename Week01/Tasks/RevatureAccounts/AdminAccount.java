package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import Tasks.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public class AdminAccount extends Account implements Serializable {
	/* 
	 * unapprovedAccounts is Unserializable. No worries since we must collect all 
	 * unapproved accounts every time the admin signs in anyways.
	 */
	private transient ArrayList<Map.Entry<Integer, Account>> unapprovedAccounts;
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
		unapprovedAccounts = new ArrayList<>(RevatureBank.getUnapprovedAccounts());
		Integer numUnapproves = unapprovedAccounts.size();
  		System.out.print("Signed in as admin. ");
  		System.out.println("There are " + numUnapproves + " unapproved accounts.");
  		System.out.println("What would you like to do?");
  		System.out.println("[0]:\tSignout");
  		System.out.println("[1]:\tApprove Accounts");
		actions.get(askUser("[0-1]")).run();
 	}

	public void evaluateAccounts() {
		for(int i = 0; i < unapprovedAccounts.size(); i++) {
			System.out.print("[" + i + "]:\t");
			unapprovedAccounts.get(i).getValue().print();
		}
		System.out.println("[a]:\tAll");
  		System.out.println("[b]:\tGo Back");

	}
}
