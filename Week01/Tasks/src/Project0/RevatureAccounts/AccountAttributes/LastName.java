package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

public class LastName extends AccountAttribute 
					  implements LogReference, Serializable{
	private static final long serialVersionUID = -4784336663257381167L;
	private String lastname;
	
	public LastName(AdminAccount aa) {
		super(aa);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "LastName.java: Constructing LastName(AdminAccount).");
		lastname = "admin";
		aa.addAttribute("Lastname", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "LastName.java: Constructed LastName(AdminAccount).");
	}

	public LastName(UserAccount ua) {
		super(ua);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "LastName.java: Constructing LastName(UserAccount).");
		lastname = askUser();
		ua.addAttribute("Lastname", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "LastName.java: Constructed LastName(UserAccount).");
	}

	@Override
	public String askUser() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "LastName.java: Entered and exiting askUser().");
		
		return console.readLine("Lastname: ");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String get() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "LastName.java: Entered and exiting get().");
		
		return lastname;
	}
	
	@Override
	public void print() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "LastName.java: Entered print().");
		System.out.print("Lastname: " + lastname);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
    	 	 	 	 "LastName.java: Exiting print().");
	}

	@Override 
	public Integer getID() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
    	 	 	 	 "LastName.java: Entered and exiting getID().");
		
		return lastname.hashCode();
	}
}
