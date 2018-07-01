package Project0_PartII.RevatureAccounts.AccountAttributes;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;
import Project0_PartII.RevatureDatabase.DatabaseConnection;

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
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	             	 "Status.java: Constructing Status(AdminAccount).");
		status = AccountStatus.APPROVED;
		aa.addAttribute("Status", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
            	 	 "Status.java: Constructed Status(AdminAccount).");
	}
	
	/**
	 * This constructor adds an initialized account status to a UserAccount.
	 * 
	 * @param uaUserAccount to which this status belongs.
	 */
	public Status(UserAccount ua) {
		super(ua);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
            	 	 "Status.java: Constructing Status(UserAccount).");
		status = AccountStatus.PENDING;
		ua.addAttribute("Status", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "Status.java: Constructed Status(UserAccount).");
	}
	
	public Status(UserAccount ua, ResultSet rs) throws SQLException {
		super(rs);
		status = AccountStatus.getValue(rs.getString("code"));
		ua.addAttribute("Status", this);
	}	
	
	/**
	 * approve() contains the logic that approves an account.
	 */
	@Override
	public void approve() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
       	 	 		 "Status.java: Entered approve().");
		status = AccountStatus.APPROVED;
		dirty = true;
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 		 	 "Status.java: Exiting approve().");
	}
	
	/**
	 * deny() contains the logic that denies an account.
	 */
	@Override
	public void deny() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 		 	 "Status.java: Entered deny().");
		status = AccountStatus.DENIED;
		dirty = true;
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 		 	 "Status.java: Exiting deny().");
	}

	/**
	 * display() chooses the view of an account to display based on its status.
	 */
	@Override
	public void display(Account a) {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 		 	 "Status.java: Entered display().");
		status.display(a);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 		 	 	 "Status.java: Exiting display().");
	}

	@SuppressWarnings("unchecked")
	@Override
	public AccountStatus get() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 		 	 	 "Status.java: Entered and exiting display().");
		
		return status;
	}
	
	@Override
	public void print() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 		 	 	 "Status.java: Entered print().");
		System.out.print("Status: " + status);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
		 	 	 "Status.java: Exiting print().");
	}

	/**
	 * @return the status ID based of its hashCode.
	 */
	@Override
	public Integer getID() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Status.java: Entered and exiting getID().");
		
		return status.hashCode();
	}
	
	@Override
	public void write(Connection connection, String username) throws SQLException {
		if(dirty) {
			sqlUpdate = "{call updateStatus(?, ?)}";
			statement = connection.prepareCall(sqlUpdate);
			statement.setString(1, username);
			statement.setString(2, status.getString());
			statement.execute();
			DatabaseConnection.close(statement);
		}
	}
}
