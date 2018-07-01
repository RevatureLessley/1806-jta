package Project0_PartII.RevatureAccounts.AccountAttributes;

import java.io.*;
import java.sql.*;
import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;

/**
 * AccountAttribute encapsulates the logic of an account attribute.
 */
public abstract class AccountAttribute implements ConsoleReference, 
												  LogReference, Serializable {
 	
	private static final long serialVersionUID = -1507218302429698089L;
	protected boolean dirty = false;
	protected CallableStatement statement = null;
	protected String sqlUpdate = null;
	

	public AccountAttribute(AdminAccount aa) {}
	
	public AccountAttribute(ResultSet rs) {}
 	
	public AccountAttribute(UserAccount ua) {}

	public void approve() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
					 "AcountAttributes.java: Entered and exiting approve().");
		logger.error("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 		 "AccountAttributes.java: AccountAttributes.approve()" + 
	 	 	 		 "was incorrectly called or is not overridden.");
	}
	
	/**
	 * askUser() contains the logic for requesting user input.
	 * 
	 * @return the user input
	 */
	public String askUser() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 	 "AcountAttributes.java: Entered and exiting withdraw().");
		logger.error("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 	"AccountAttributes.java: AccountAttributes.askUser()" + 
	 	 	 	 	"was incorrectly called or is not overridden.");
		
		return null;
	}

	public void deny () {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
					 "AcountAttributes.java: Entered and exiting deny().");
		logger.error("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 		 "AccountAttributes.java: AccountAttributes.deny()" + 
	 	 	 		 "was incorrectly called or is not overridden.");
	}
	
	public void deposit() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 	 "AcountAttributes.java: Entered and exiting deposit().");
		logger.error("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 		 "AccountAttributes.java: AccountAttributes.deposit()" + 
	 	 	 		 "was incorrectly called or is not overridden.");
	}
	
	public void display(Account a) {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 "AcountAttributes.java: Entered and exiting display().");
		logger.error("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	"AccountAttributes.java: AccountAttributes.display()" + 
	 	 	 	"was incorrectly called or is not overridden.");
	}
	
	public <E> E get() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 "AcountAttributes.java: Entered and exiting get().");
		logger.error("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	"AccountAttributes.java: AccountAttributes.get()" + 
	 	 	 	"was incorrectly called or is not overridden.");
		
		return null;
	}

 	abstract public void print();
 	
 	/**
 	 * @return attribute is based on its hashCode.
 	 */
	abstract public Integer getID();
	
	public void withdraw() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 "AcountAttributes.java: Entered and exiting withdraw().");
		logger.error("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	"AccountAttributes.java: AccountAttributes.withdraw()" + 
	 	 	 	"was incorrectly called or is not overridden.");
	}
	
	public void write(Connection connection, String username) 
	throws SQLException {}
}
