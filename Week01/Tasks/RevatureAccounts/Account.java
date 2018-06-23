package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import Tasks.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public abstract class Account implements ConsoleReference, Serializable {
	protected AccountStatus status;
	protected HashMap<String, AccountAttribute> attributes = new HashMap<>();
	protected ArrayList<Runnable> actions = new ArrayList<>();

 	public Account() {
		actions.add((Runnable & Serializable)() -> signOut());
	}

 	public void addAttribute(String field, AccountAttribute aa) {
  		attributes.put(field, aa);
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

	abstract public void enter();

	public Integer getID(){
		Integer index = attributes.get("Username").getID() + 
				attributes.get("Password").getID();
		return index.hashCode();
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
