package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

public class Status extends AccountAttribute 
					implements LogReference, Serializable {
	private static final long serialVersionUID = -1583445065455260161L;
	private AccountStatus status;
	
	public Status(AdminAccount aa) {
		super(aa);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	             	 "Status.java: Constructing Status(AdminAccount).");
		status = AccountStatus.APPROVED;
		aa.addAttribute("Status", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
            	 	 "Status.java: Constructed Status(AdminAccount).");
	}
	
	public Status(UserAccount ua) {
		super(ua);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
            	 	 "Status.java: Constructing Status(UserAccount).");
		status = AccountStatus.PENDING;
		ua.addAttribute("Status", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "Status.java: Constructed Status(UserAccount).");
	}
	
	@Override
	public void approve() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 		 "Status.java: Entered approve().");
		status = AccountStatus.APPROVED;
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 		 	 "Status.java: Exiting approve().");
	}
	
	@Override
	public void deny() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 		 	 "Status.java: Entered deny().");
		status = AccountStatus.DENIED;
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 		 	 "Status.java: Exiting deny().");
	}

	@Override
	public void display(Account a) {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 		 	 "Status.java: Entered display().");
		status.display(a);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 		 	 	 "Status.java: Exiting display().");
	}

	@SuppressWarnings("unchecked")
	@Override
	public AccountStatus get() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 		 	 	 "Status.java: Entered and exiting display().");
		
		return status;
	}
	
	@Override
	public void print() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 		 	 	 "Status.java: Entered print().");
		System.out.print("Status: " + status);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
		 	 	 "Status.java: Exiting print().");
	}

	@Override
	public Integer getID() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Status.java: Entered and exiting getID().");
		
		return status.hashCode();
	}
}
