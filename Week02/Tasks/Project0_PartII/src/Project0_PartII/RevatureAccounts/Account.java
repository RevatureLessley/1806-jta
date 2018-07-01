package Project0_PartII.RevatureAccounts;

import java.io.Serializable;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.AccountAttributes.*;

/**
 * AdminAccount encapsulates the logic of an account.
 */
public abstract class Account implements ConsoleReference, LogReference, 
										 Serializable {
	private static final long serialVersionUID = -6559073408113950663L;
	/**
	 * attributes contains all the fields of an Account.
	 */
	protected HashMap<String, AccountAttribute> attributes = new HashMap<>();
	/**
	 * actions contains all the actions an Account could perform.
	 */
	protected ArrayList<Runnable> actions = new ArrayList<>();
	
	/**
	 * This constructor initialized the actions of an account.
	 */
	public Account() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
           	 		 "Constructing Account().");
		actions.add((Runnable & Serializable)() -> signOut());
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
      	 		 	 "Constructed Account().");
	}
	
	/**
	 * addAttributes adds an attribute to an Account.
	 * 
	 * @param field the attribute name.
	 * @param aa the attribute itself.
	 */
 	public void addAttribute(String field, AccountAttribute aa) {
 		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
      	 		 	 "Entered addAttribute().");
  		attributes.put(field, aa);
  		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
 	 		 	 	 "Exiting addAttribute().");
 	}

 	/**
 	 * approve() contains the logic for approving a UserAccount.
 	 */
	public void approve() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
 	 		 	 	 "Entered approve().");
		attributes.get("Status").approve();
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
 		 	 	 	 "Exiting approve().");
	}	


	public void approved() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
 		 	 	 	 "Entered approved().");
		logger.error("Project0_PartII/RevatureAccounts/Account.java: " + 
 		 	 	 	 "Account.approved() was incorrectly called or is not " + 
					 "overridden.");
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 "Exiting approved().");
	}

	/**
	 * askUser() asks the user for input.
	 * 
	 * @param regex the regular expression for checking input validity.
	 * @return String containing the user input.
	 */
	protected String askUser(String regex) {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Entered askUser().");
		Pattern p = Pattern.compile(regex);
		String action;
		Matcher m;

		do {
			action = console.readLine("> ");
			m = p.matcher(action);
		} while(!m.matches());
		
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Exiting askUser().");

		return action;
	}

	/**
 	 * deny() contains the logic for denying a UserAccount.
 	 */
	public void deny() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Entered deny().");
		attributes.get("Status").deny();
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Exiting deny().");
	}


	public void denied() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Entered denied().");
		logger.error("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Account.denied() was incorrectly called or is not " + 
				     "overridden.");
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	 	 "Exiting denied().");
	}

	abstract public void enter();
	
	public BigDecimal getBalance() {
		return attributes.get("Balance").get();
	}
	
	public String getFirstName() {
		return attributes.get("Firstname").get();
	}

	/**
	 * @return hashCode ID based of the username and password.
	 */
	public Integer getID(){
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	 	     "Entered getID().");
		Integer index = attributes.get("Username").getID() + 
						attributes.get("Password").getID();
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	     	 "Exiting getID().");
		
		return index.hashCode();
	}
	
	public String getLastName() {
		return attributes.get("Lastname").get();
	}
	
	public String getPassword() {
		return attributes.get("Password").get();
	}
	
	public AccountStatus getStatus() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	 	     	 "Entered and exiting getID().");
		
		return attributes.get("Status").get();
	}

	public String getUsername() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	     	 	 "Entered and exiting getUsername().");
		
		return attributes.get("Username").get();
	}


	public void pending() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	     	 	 "Entered pending().");
		logger.error("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	     	 	 "Account.pending() was incorrectly called or is not " + 
				     "overridden.");
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	     	 	     "Exiting pending().");
	}

 	public void print(){
 		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	     	 	     "Entered print().");
		attributes.get("Status").print();
		System.out.print(" | ");
		attributes.get("Username").print();
		System.out.print(" | ");
		attributes.get("Firstname").print();
		System.out.print(" | ");
		attributes.get("Lastname").print();
		System.out.println();
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
    	 	     	 "Exiting print().");
 	}

 	/**
 	 * signIn() contains the logic for account sign in.
 	 * 
 	 * @return the account ID based of its hashCode.
 	 */
	public static Integer signIn(){
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
    	 	     	 "Entered signIn().");
		Integer username = console.readLine("Username: ").hashCode();
		Integer password = String.valueOf(console.readPassword("Password: "))
				                 .hashCode();
		Integer index = username + password;
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	     	 	 "Exiting signIn().");
		
		return index.hashCode();	
	}

	/**
	 * signOut() exists an account.
	 */
	private void signOut() {
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	     	 "Entered signOut().");
		System.out.println("Signed out. Thank you for choosing RevatureBank.");
		logger.debug("Project0_PartII/RevatureAccounts/Account.java: " + 
	 	     	 "Exiting signOut().");
	}
	
	public void write(Connection connection) throws SQLException {
		for(AccountAttribute aa : attributes.values()) {
			aa.write(connection, this.getUsername());
		}
 	}
}
