package Project0.RevatureAccounts;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import Project0.*;
import Project0.RevatureAccounts.AccountAttributes.*;

public abstract class Account implements ConsoleReference, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6559073408113950663L;
	protected HashMap<String, AccountAttribute> attributes = new HashMap<>();
	protected ArrayList<Runnable> actions = new ArrayList<>();
	
	public Account() {
		actions.add((Runnable & Serializable)() -> signOut());
	}

 	public void addAttribute(String field, AccountAttribute aa) {
  		attributes.put(field, aa);
 	}

	public void approve() {
		attributes.get("Status").approve();
	}

	public void approved() {
		System.err.println("Account.approved() was incorrectly called or is not overridden.");
	}

	protected String askUser(String regex) {
		Pattern p = Pattern.compile(regex);
		String action;
		Matcher m;

		do {
			action = console.readLine("> ");
			m = p.matcher(action);
		} while(!m.matches());

		return action;
	}

	public void deny() {
		attributes.get("Status").deny();
	}


	public void denied() {
		System.err.println("Account.denied() was incorrectly called or is not overridden.");
	}


	abstract public void enter();

	public Integer getID(){
		Integer index = attributes.get("Username").getID() + 
				attributes.get("Password").getID();
		return index.hashCode();
	}
	
	public AccountStatus getStatus() {
		return attributes.get("Status").get();
	}

	public String getUsername() {
		return attributes.get("Username").get();
	}


	public void pending() {
		System.err.println("Account.pending() was incorrectly called or is not overridden.");
	}


 	public void print(){
		attributes.get("Status").print();
		System.out.print(" | ");
		attributes.get("Username").print();
		System.out.print(" | ");
		attributes.get("Firstname").print();
		System.out.print(" | ");
		attributes.get("Lastname").print();
		System.out.println();
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
