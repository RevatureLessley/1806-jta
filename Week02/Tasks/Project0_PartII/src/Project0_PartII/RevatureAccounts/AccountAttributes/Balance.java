package Project0_PartII.RevatureAccounts.AccountAttributes;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.Locale;

import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;
import Project0_PartII.RevatureDatabase.*;

/**
 * Balance encapsulates the logic of an account balance.
 */
public class Balance extends AccountAttribute 
					 implements LogReference, Serializable {
	private static final long serialVersionUID = -5263664987376027348L;
	private BigDecimal balance;
	
	/**
	 * This constructor adds an initialized account balance to a UserAccount.
	 * 
	 * @param ua UserAccount to which this account balance belongs.
	 */
	
	public Balance(UserAccount ua) {
		super(ua);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "Balance.java: Constructing Balance(AdminAccount).");
		balance = new BigDecimal(0.0);
		ua.addAttribute("Balance", this);
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Balance.java: Constructed Balance(AdminAccount).");
	}
	
	public Balance(UserAccount ua, ResultSet rs) throws SQLException {
		super(rs);
		balance = rs.getBigDecimal("acc_dyn_balance");
		ua.addAttribute("Balance", this);
	}	

	/**
	 * askUser() contains the logic for asking the user for input.
	 * 
	 * @return the user input.
	 */
	@Override
	public String askUser() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Balance.java: Entered and exiting askUser().");
		
		return console.readLine("Please enter a $ followed by positive " + 
						        "value:\n>");
	}

	/**
	 * deposit() contains the logic for making a deposit.
	 */
	@Override
	public void deposit() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
					 "Balance.java: Entered deposit().");
		balance = balance.add(getAmount());
		dirty = true;
		System.out.println("Transaction approved.");
		print();
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
				 	 "Balance.java: Exiting deposit().");
	}
	
	/**
	 * getAmount() contains the logic for getting a dollar amount from the 
	 * user.
	 * 
	 * @return the dollar amount.
	 */
	private BigDecimal getAmount() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
				 "Balance.java: Entered getAmount().");
		String value;
		Number num;

		do {
			try {
				value = askUser();
				num = NumberFormat.getCurrencyInstance(Locale.US).parse(value);
			}

			catch(ParseException pe) {
				num = null;
			}
		} while(num == null);
		
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
				 	 "Balance.java: Exiting getAmount().");

		return new BigDecimal(num.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal get() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
				 	 "Balance.java: Entered and exiting get().");
		
		return balance;
	}
	
	@Override
	public void print() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
			 	 	 "Balance.java: Entered print().");
		System.out.print("Your current balance is: ");
		System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(balance));
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Balance.java: Exiting print().");
	}

	/**
	 * @return the balance ID based of its hashCode.
	 */
	@Override
	public Integer getID() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Balance.java: Entered and existing getID().");
		
		return balance.hashCode();
	}

	/**
	 * deposit() contains the logic for making a withdrawal.
	 */
	@Override
	public void withdraw() {
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Balance.java: Entered withdraw().");
		BigDecimal amount = getAmount();
		
		if(balance.compareTo(amount) < 0) {
			System.out.println("Transaction not approved.");
			System.out.println("You cannot withdraw more money than you have!.");

		}

		else {
			balance = balance.subtract(amount);
			System.out.println("Transaction approved.");

		}

		print();
		logger.debug("Project0_PartII/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 "Balance.java: Exiting withdraw().");
	}
	
	@Override
	public void write(Connection connection, String username) throws SQLException {
		if(dirty) {
			sqlUpdate = "{call updateBalance(?, ?)}";
			statement = connection.prepareCall(sqlUpdate);
			statement.setString(1, username);
			statement.setBigDecimal(2, balance);
			statement.execute();
			DatabaseConnection.close(statement);
		}
	}
}
