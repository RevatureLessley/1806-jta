package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

/**
 * Username encapsulates the logic of a username.
 */
public class Username extends AccountAttribute 
					  implements LogReference, Serializable {
	private static final long serialVersionUID = -3539688509393580418L;
	private String username;
	private String u;
	
	/**
	 * This constructor adds an initialized username to an AdminAccount.
	 * 
	 * @param aa AdminAccount to which this username belongs.
	 */
	public Username(AdminAccount aa) {
		super(aa);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
		             "Username.java: Constructing Username(AdminAccount).");
		username = "admin";
		aa.addAttribute("Username", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	             	 "Username.java: Constructed Username(AdminAccount).");
	}
	
	/**
	 * This constructor adds an initialized username to a UserAccount.
	 * 
	 * @param ua UserAccount to which this username belongs.
	 */
	public Username(UserAccount ua) {
		super(ua);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	             "Username.java: Constructing Username(UserAccount).");
		username = askUser();
		ua.addAttribute("Username", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	             "Username.java: Constructed Username(UserAccount).");
	}

	/**
	 * askUser() contains the logic for asking the user for a username.
	 */
	@Override
	public String askUser() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	             	 "Username.java: Entered askUser().");
		do{
			getUsername();
		} while(RevatureBank.accountExists(u));

		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
            	 "Username.java: Exiting askUser().");
		
		return u;
	}

	/**
	 * getUsername() contains the logic for getting the username from the user.
	 */
	private void getUsername() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
            	 	 "Username.java: Entered getUsername().");
		u = console.readLine("Username: ");
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "Username.java: Exiting getUsername().");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String get() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 		 "Username.java: Entered and exiting get().");
		
		return username;
	}
	
	@Override
	public void print() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 		 	 "Username.java: Entered print().");
		System.out.print("Username: " + username);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 		 	 "Username.java: Exiting print().");
	}

	/**
	 * @return the username ID based of its hashCode.
	 */
	@Override
	public Integer getID() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 		 	 "Username.java: Entered and exiting getID().");
		
		return username.hashCode();
	}
}
