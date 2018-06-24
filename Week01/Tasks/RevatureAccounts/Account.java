package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import Tasks.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public abstract class Account implements ConsoleReference, Serializable {
	protected HashMap<String, AccountAttribute> attributes = new HashMap<>();
	protected ArrayList<Runnable> actions = new ArrayList<>();
	/*protected enum States {
		APPROVED() {
			public void display(Account a) {
				a.approved();
			}
		}, 
	
		DENIED() {
			public void display(Account a) { 
				a.denied();
			}
		}, 
	
		PENDING() {
			public void display(Account a) {
				a.pending();
			}
		};

		public abstract void display(Account a);
	};*/

 	public Account() {
		actions.add((Runnable & Serializable)() -> signOut());
	}

 	public void addAttribute(String field, AccountAttribute aa) {
  		attributes.put(field, aa);
 	}

	public void approved() {
		System.err.println("Account.approved() was incorreectly called or is not overridden.");
	}

	protected Integer askUser(String regex) {
		Pattern p = Pattern.compile(regex);
		String action;
		Matcher m;

		do {
			action = console.readLine("> ");
			m = p.matcher(action);
		} while(!m.matches());

		return Integer.valueOf(action);
	}

	public void denied() {
		System.err.println("Account.denied() was incorreectly called or is not overridden.");
	}


	abstract public void enter();

	public Integer getID(){
		Integer index = attributes.get("Username").getID() + 
				attributes.get("Password").getID();
		return index.hashCode();
	}

	public void pending() {
		System.err.println("Account.pending() was incorreectly called or is not overridden.");
	}


 	public void print(){
		for(AccountAttribute aa : attributes.values())
			aa.print();
 	}

	public static Integer signIn(){
		Integer username = console.readLine("Username: ").hashCode();
		Integer password = String.valueOf(console.readPassword("Password: ")).hashCode();
		Integer index = username + password;

		return index.hashCode();	
	}

	private void signOut() {
		System.out.println("Signed out. Thank you for choosing RevatureBank.");
	}
}
