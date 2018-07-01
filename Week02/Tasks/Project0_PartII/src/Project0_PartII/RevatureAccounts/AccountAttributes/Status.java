package Project0_PartII.RevatureAccounts.AccountAttributes;

import java.sql.*;
import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;
import Project0_PartII.RevatureDatabase.*;

/**
 * Status encapsulates the logic of an account status.
 */
public class Status extends AccountAttribute implements LogReference{
	private AccountStatus status;
	private boolean dirty = false;
	
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
	 * @param ua UserAccount to which this status belongs.
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
	
	/**
	 * This constructor adds an initialized account status to a UserAccount 
	 *  from the database.
	 * 
	 * @throws SQLException
	 * @param ua UserAccount to which this status belongs.
	 * @param rs ResultSet from the database.
	 */
	public Status(UserAccount ua, ResultSet rs) throws SQLException {
		super(rs);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "Status.java: " + 
					 "Constructing Status(UserAccount, ResultSet).");
		status = AccountStatus.getValue(rs.getString("code"));
		ua.addAttribute("Status", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "Status.java: " + 
					 "Constructing Status(UserAccount, ResultSet).");
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
	
	/**
	 * write() updates status on disk.
	 * 
	 * @throws SQLException
	 * @param connection the database connection to write to.
	 * @param username the username to which this status is associated.
	 */
	@Override
	public void write(Connection connection, String username) 
	throws SQLException {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 	 "Status.java: Entered write().");
		
		if(dirty) {
			sqlUpdate = "{call updateStatus(?, ?)}";
			statement = connection.prepareCall(sqlUpdate);
			statement.setString(1, username);
			statement.setString(2, status.getString());
			statement.execute();
			DatabaseConnection.close(statement);
		}
		
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 	 "Status.java: Exiting write().");
	}
}
