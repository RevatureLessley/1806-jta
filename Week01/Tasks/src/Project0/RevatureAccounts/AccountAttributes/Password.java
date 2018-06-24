package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

public class Password extends AccountAttribute 
					  implements LogReference, Serializable {
	// This class needs to be secured.
	private static final long serialVersionUID = -1240459302631947705L;
	private String password;
	private String p;
	private String q;

	public Password(AdminAccount aa) {
		super(aa);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
            	 	 "Password.java: Constructing Password(AdminAccount).");
		password = "admin";
		aa.addAttribute("Password", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 		 "Password.java: Constructed Password(AdminAccount).");
	}

	public Password(UserAccount ua) {
		super(ua);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "Password.java: Constructing Password(UserAccount).");
		password = askUser();
		ua.addAttribute("Password", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "Password.java: Constructed Password(UserAccount).");
	}

	@Override
	public String askUser() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "Password.java: Entered askUser().");
		do{
			getPassword();
		}while(!p.equals(q));
		
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "Password.java: Exiting askUser().");
		
		return p;
	}	

	private void getPassword(){
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "Password.java: Entered getPassword().");
		p = String.valueOf(console.readPassword("Password: "));
		q = String.valueOf(console.readPassword("Confirm Password: "));
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Password.java: Exiting getPassword().");
	}
	
	@Override
	public void print() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Password.java: Entered print().");
		System.out.println("Password: " + password);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "Password.java: Exiting print().");
	}

	@Override
	public Integer getID() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "Password.java: Entered and exiting print().");
		
		return password.hashCode();
	}
}
