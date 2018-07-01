package Project0_PartII.RevatureAccounts.AccountAttributes;

import java.sql.*;
import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;
/**
 * Password encapsulates the logic of a password.
 */
public class Password extends AccountAttribute implements LogReference {
	// This class needs to be secured.
	private String password;
	private String p;
	private String q;

	/**
	 * This constructor adds an initialized password to an AdminAccount.
	 * 
	 * @param aa AdminAccount to which this password belongs.
	 */
	public Password(AdminAccount aa) {
		super(aa);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
            	 	 "Password.java: Constructing Password(AdminAccount).");
		password = "admin";
		aa.addAttribute("Password", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
       	 	 		 "Password.java: Constructed Password(AdminAccount).");
	}

	/**
	 * This constructor adds an initialized password to a UserAccount.
	 * 
	 * @param ua UserAccount to which this password belongs.
	 */
	public Password(UserAccount ua) {
		super(ua);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
       	 	 	     "Password.java: Constructing Password(UserAccount).");
		password = askUser();
		ua.addAttribute("Password", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "Password.java: Constructed Password(UserAccount).");
	}
	
	/**
	 * This constructor adds an initialized password to a UserAccount from the
	 * database.
	 * 
	 * @throws SQLException
	 * @param ua UserAccount to which this password belongs.
	 * @param rs ResultSet from the database.
	 */
	public Password(UserAccount ua, ResultSet rs) throws SQLException {
		super(rs);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "Password.java: " + 
					 "Constructing Password(UserAccount, ResultSet).");
		password = rs.getString("acc_sta_password");
		ua.addAttribute("Password", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "Password.java: " + 
				 	 "Constructed Password(UserAccount, ResultSet).");
	}	

	/**
	 * askUser() contains the logic for asking the user for a password.
	 */
	@Override
	public String askUser() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
  	 	 	     	 "Password.java: Entered askUser().");
		do{
			getPassword();
		}while(!p.equals(q));
		
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "Password.java: Exiting askUser().");
		
		return p;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String get() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "Password.java: Entered and exiting get().");
		
		return password;
	}

	/**
	 * getPassword() contains the logic for getting the password from the user.
	 */
	private void getPassword(){
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "Password.java: Entered getPassword().");
		p = String.valueOf(console.readPassword("Password: "));
		q = String.valueOf(console.readPassword("Confirm Password: "));
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Password.java: Exiting getPassword().");
	}
	
	@Override
	public void print() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Password.java: Entered print().");
		System.out.println("Password: " + password);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "Password.java: Exiting print().");
	}

	/**
	 * @return the password ID based of its hashCode.
	 */
	@Override
	public Integer getID() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	     	 	 	 "Password.java: Entered and exiting print().");
		
		return password.hashCode();
	}
}
