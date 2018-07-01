package Project0_PartII.RevatureAccounts.AccountAttributes;

import java.sql.*;
import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;

/**
 * Firstname encapsulates the logic of a first name.
 */
public class FirstName extends AccountAttribute implements LogReference {
	private String firstname;
	
	/**
	 * This constructor adds an initialized firstname to an AdminAccount.
	 * 
	 * @param aa AdminAccount to which this firstname belongs.
	 */
	public FirstName(AdminAccount aa) {
		super(aa);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "FirstName.java: Constructing FirstName(AdminAccount).");
		firstname = "admin";
		aa.addAttribute("Firstname", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "FirstName.java: Constructed FirstName(AdminAccount).");
	}
	
	/**
	 * This constructor adds an initialized firstname to a UserAccount.
	 * 
	 * @param ua UserAccount to which this firstname belongs.
	 */
	public FirstName(UserAccount ua) {
		super(ua);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "FirstName.java: Constructing FirstName(UserAccount).");
		firstname = askUser();
		ua.addAttribute("Firstname", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "FirstName.java: Constructed FirstName(UserAccount).");
	}
	
	/**
	 * This constructor adds an initialized firstname to a UserAccount from the
	 * database.
	 * 
	 * @throws SQLException
	 * @param ua UserAccount to which this firstname belongs.
	 * @param rs ResultSet from the database.
	 */
	public FirstName(UserAccount ua, ResultSet rs) throws SQLException {
		super(rs);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "FirstName.java: " + 
					 "Constructing FirstName(UserAccount, ResultSet).");
		firstname = rs.getString("firstname");
		ua.addAttribute("Firstname", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	     	 	 "FirstName.java: " + 
				 "Constructed FirstName(UserAccount, ResultSet).");
	}	

	/**
	 * askUser() contains the logic for asking the user for a first name.
	 */
	@Override
	public String askUser() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 "FirstName.java: Entered and exiting askUser().");
		
		return console.readLine("Firstname: ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public String get() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
					 "FirstName.java: Entered and exiting get().");
		
		return firstname;
	}
	
	@Override
	public void print() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "FirstName.java: Entered print().");
		System.out.print("Firstname: " + firstname);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "FirstName.java: Exiting print().");
	}

	/**
	 * @return the firstname ID based of its hashCode.
	 */
	@Override
	public Integer getID() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
				 "FirstName.java: Entered and exiting getID().");
		
		return firstname.hashCode();
	}
}
