package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

/**
 * Status encapsulates the logic of an account status.
 */
public class Status extends AccountAttribute 
					implements LogReference, Serializable {
	private static final long serialVersionUID = -1583445065455260161L;
	private AccountStatus status;
	
	/**
	 * This constructor adds an initialized account status to an AdminAccount.
	 * 
	 * @param aa AdminAccount to which this status belongs.
	 */
	public Status(AdminAccount aa) {
		super(aa);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	             	 "Status.java: Constructing Status(AdminAccount).");
		status = AccountStatus.APPROVED;
		aa.addAttribute("Status", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
            	 	 "Status.java: Constructed Status(AdminAccount).");
	}
	
	/**
	 * This constructor adds an initialized account status to a UserAccount.
	 * 
	 * @param uaUserAccount to which this status belongs.
	 */
	public Status(UserAccount ua) {
		super(ua);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
            	 	 "Status.java: Constructing Status(UserAccount).");
		status = AccountStatus.PENDING;
		ua.addAttribute("Status", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "Status.java: Constructed Status(UserAccount).");
	}
	
	/**
	 * approve() contains the logic that approves an account.
	 */
	@Override
	public void approve() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
       	 	 		 "Status.java: Entered approve().");
		status = AccountStatus.APPROVED;
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 		 	 "Status.java: Exiting approve().");
	}
	
	/**
	 * deny() contains the logic that denies an account.
	 */
	@Override
	public void deny() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
  	 	 		 	 "Status.java: Entered deny().");
		status = AccountStatus.DENIED;
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 		 	 "Status.java: Exiting deny().");
	}

	/**
	 * display() chooses the view of an account to display based on its status.
	 */
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

	/**
	 * @return the status ID based of its hashCode.
	 */
	@Override
	public Integer getID() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Status.java: Entered and exiting getID().");
		
		return status.hashCode();
	}
}
