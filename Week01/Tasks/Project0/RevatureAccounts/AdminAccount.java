package Project0.RevatureAccounts;

import java.io.*;
import java.util.*;
import Project0.*;
import Project0.RevatureAccounts.AccountAttributes.*;

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
		Integer action;

		do {
			unapprovedAccounts = new ArrayList<>(RevatureBank.getUnapprovedAccounts());
			Integer numUnapproves = unapprovedAccounts.size();
  			System.out.print("Signed in as admin. ");
  			System.out.println("There are " + numUnapproves + " unapproved accounts.");
  			System.out.println("What would you like to do?");
  			System.out.println("[0]:\tSignout");
  			System.out.println("[1]:\tApprove Accounts");
			String input = askUser("[0-1]");
			action = Integer.valueOf(input);
			actions.get(action).run();
		} while(action.compareTo(0) != 0);
 	}

	private void evaluateAccounts() {
		String input;

		do {
			System.out.println("Please select one of the following:");
  			System.out.println("[b]:\tGo Back");

			int numAccounts = unapprovedAccounts.size();
			
			if (numAccounts > 0)
				System.out.println("[a]:\tAll Accounts");

			for(int i = 0; i < numAccounts; i++) {
				System.out.print("[" + i + "]:\t");
				unapprovedAccounts.get(i).getValue().print();
			}
		
			input = askUser("[0-" + String.valueOf(numAccounts) + "ab]");

			switch(input) {
				case "a" : 
				{
					decideAll();
					break;
				}
				case "b" :
					return;
				default : {
					int account = Integer.valueOf(input);
					decide(account);
					break;
				}
			}

		} while(true); 
	}

	private void decide(int account) {
		Account a = unapprovedAccounts.get(account).getValue();

		if(getDecision()) {
			a.approve();
			unapprovedAccounts.remove(account);
		}

		else a.deny();
	}

	private void decideAll() {
		if(getDecision()) { 
			unapprovedAccounts.stream().forEach(me -> me.getValue().approve());
			unapprovedAccounts.clear();
		}
		else 
			unapprovedAccounts.stream().forEach(me -> me.getValue().deny());

	}

	private boolean getDecision() {
		String decision;
		boolean y;
		boolean n;

		do {
			decision = console.readLine("Approve?(y/n): ");
			y = decision.equals("y");
			n = decision.equals("n");
		} while(!(y || n));
		
		return y;
	}
}
