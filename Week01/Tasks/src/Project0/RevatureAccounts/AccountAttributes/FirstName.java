package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

public class FirstName extends AccountAttribute 
					implements LogReference, Serializable{
	private static final long serialVersionUID = -6837510462814822228L;
	private String firstname;
	
	public FirstName(AdminAccount aa) {
		super(aa);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "FirstName.java: Constructing FirstName(AdminAccount).");
		firstname = "admin";
		aa.addAttribute("Firstname", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "FirstName.java: Constructed FirstName(AdminAccount).");
	}
	
	public FirstName(UserAccount ua) {
		super(ua);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "FirstName.java: Constructing FirstName(UserAccount).");
		firstname = askUser();
		ua.addAttribute("Firstname", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "FirstName.java: Constructed FirstName(UserAccount).");
	}

	@Override
	public String askUser() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 "FirstName.java: Entered and exiting askUser().");
		
		return console.readLine("Firstname: ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public String get() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
					 "FirstName.java: Entered and exiting get().");
		
		return firstname;
	}
	
	@Override
	public void print() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "FirstName.java: Entered print().");
		System.out.print("Firstname: " + firstname);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "FirstName.java: Exiting print().");
	}

	@Override
	public Integer getID() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
				 "FirstName.java: Entered and exiting getID().");
		
		return firstname.hashCode();
	}
}
