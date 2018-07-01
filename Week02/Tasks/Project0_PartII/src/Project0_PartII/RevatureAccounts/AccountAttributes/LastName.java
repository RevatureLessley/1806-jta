package Project0_PartII.RevatureAccounts.AccountAttributes;

import java.sql.*;

import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;

/**
 * Lastname encapsulates the logic of a last name.
 */
public class LastName extends AccountAttribute implements LogReference{
	private String lastname;
	
	/**
	 * This constructor adds an initialized lastname to an AdminAccount.
	 * 
	 * @param aa AdminAccount to which this lastname belongs.
	 */
	public LastName(AdminAccount aa) {
		super(aa);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "LastName.java: Constructing LastName(AdminAccount).");
		lastname = "admin";
		aa.addAttribute("Lastname", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "LastName.java: Constructed LastName(AdminAccount).");
	}

	/**
	 * This constructor adds an initialized lastname to a UserAccount.
	 * 
	 * @param ua UserAccount to which this lastname belongs.
	 */
	public LastName(UserAccount ua) {
		super(ua);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "LastName.java: Constructing LastName(UserAccount).");
		lastname = askUser();
		ua.addAttribute("Lastname", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "LastName.java: Constructed LastName(UserAccount).");
	}
	
	public LastName(UserAccount ua, ResultSet rs) throws SQLException {
		super(rs);
		lastname = rs.getString("lastname");
		ua.addAttribute("Lastname", this);
	}	

	/**
	 * askUser() contains the logic for asking the user for a last name.
	 */
	@Override
	public String askUser() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "LastName.java: Entered and exiting askUser().");
		
		return console.readLine("Lastname: ");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String get() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "LastName.java: Entered and exiting get().");
		
		return lastname;
	}
	
	@Override
	public void print() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "LastName.java: Entered print().");
		System.out.print("Lastname: " + lastname);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
    	 	 	 	 "LastName.java: Exiting print().");
	}

	/**
	 * @return the lastname ID based of its hashCode.
	 */
	@Override 
	public Integer getID() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
    	 	 	 	 "LastName.java: Entered and exiting getID().");
		
		return lastname.hashCode();
	}
}
