package Project0.RevatureAccounts;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import Project0.*;
import Project0.RevatureAccounts.AccountAttributes.*;

public abstract class Account implements ConsoleReference, LogReference, 
										 Serializable {
	private static final long serialVersionUID = -6559073408113950663L;
	protected HashMap<String, AccountAttribute> attributes = new HashMap<>();
	protected ArrayList<Runnable> actions = new ArrayList<>();
	
	public Account() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
           	 		 "Constructing Account().");
		actions.add((Runnable & Serializable)() -> signOut());
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
      	 		 	 "Constructed Account().");
	}

 	public void addAttribute(String field, AccountAttribute aa) {
 		logger.debug("Project0/RevatureAccounts/Account.java: " + 
      	 		 	 "Entered addAttribute().");
  		attributes.put(field, aa);
  		logger.debug("Project0/RevatureAccounts/Account.java: " + 
 	 		 	 	 "Exiting addAttribute().");
 	}

	public void approve() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
 	 		 	 	 "Entered approve().");
		attributes.get("Status").approve();
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
 		 	 	 	 "Exiting approve().");
	}	

	public void approved() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
 		 	 	 	 "Entered approved().");
		logger.error("Project0/RevatureAccounts/Account.java: " + 
 		 	 	 	 "Account.approved() was incorrectly called or is not " + 
					 "overridden.");
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 "Exiting approved().");
	}

	protected String askUser(String regex) {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Entered askUser().");
		Pattern p = Pattern.compile(regex);
		String action;
		Matcher m;

		do {
			action = console.readLine("> ");
			m = p.matcher(action);
		} while(!m.matches());
		
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Exiting askUser().");

		return action;
	}

	public void deny() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Entered deny().");
		attributes.get("Status").deny();
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Exiting deny().");
	}


	public void denied() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Entered denied().");
		logger.error("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Account.denied() was incorrectly called or is not " + 
				     "overridden.");
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Exiting denied().");
	}


	abstract public void enter();

	public Integer getID(){
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	 	     "Entered getID().");
		Integer index = attributes.get("Username").getID() + 
						attributes.get("Password").getID();
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	     	 "Exiting getID().");
		
		return index.hashCode();
	}
	
	public AccountStatus getStatus() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	 	     	 "Entered and exiting getID().");
		
		return attributes.get("Status").get();
	}

	public String getUsername() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	     	 	 "Entered and exiting getUsername().");
		
		return attributes.get("Username").get();
	}


	public void pending() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	     	 	 "Entered pending().");
		logger.error("Project0/RevatureAccounts/Account.java: " + 
	 	     	 	 "Account.pending() was incorrectly called or is not " + 
				     "overridden.");
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	     	 	     "Exiting pending().");
	}

 	public void print(){
 		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	     	 	     "Entered print().");
		attributes.get("Status").print();
		System.out.print(" | ");
		attributes.get("Username").print();
		System.out.print(" | ");
		attributes.get("Firstname").print();
		System.out.print(" | ");
		attributes.get("Lastname").print();
		System.out.println();
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
    	 	     	 "Exiting print().");
 	}

	public static Integer signIn(){
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
    	 	     	 "Entered signIn().");
		Integer username = console.readLine("Username: ").hashCode();
		Integer password = String.valueOf(console.readPassword("Password: "))
				                 .hashCode();
		Integer index = username + password;
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	     	 	 "Exiting signIn().");

		return index.hashCode();	
	}

	private void signOut() {
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	     	 "Entered signOut().");
		System.out.println("Signed out. Thank you for choosing RevatureBank.");
		logger.debug("Project0/RevatureAccounts/Account.java: " + 
	 	     	 "Exiting signOut().");
	}
}
