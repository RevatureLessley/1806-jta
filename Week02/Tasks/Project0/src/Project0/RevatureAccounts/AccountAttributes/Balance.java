package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import Project0.*;
import Project0.RevatureAccounts.*;

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
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	     	 "Balance.java: Constructing Balance(AdminAccount).");
		balance = new BigDecimal(0.0);
		ua.addAttribute("Balance", this);
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Balance.java: Constructed Balance(AdminAccount).");
	}

	/**
	 * askUser() contains the logic for asking the user for input.
	 * 
	 * @return the user input.
	 */
	@Override
	public String askUser() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	     	 	 "Balance.java: Entered and exiting askUser().");
		
		return console.readLine("Please enter a $ followed by positive " + 
						        "value:\n>");
	}

	/**
	 * deposit() contains the logic for making a deposit.
	 */
	@Override
	public void deposit() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
					 "Balance.java: Entered deposit().");
		balance = balance.add(getAmount());
		System.out.println("Transaction approved.");
		print();
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
				 	 "Balance.java: Exiting deposit().");
	}

	/**
	 * getAmount() contains the logic for getting a dollar amount from the 
	 * user.
	 * 
	 * @return the dollar amount.
	 */
	private BigDecimal getAmount() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
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
		
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
				 	 "Balance.java: Exiting getAmount().");

		return new BigDecimal(num.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal get() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
				 	 "Balance.java: Entered and exiting get().");
		
		return balance;
	}
	
	@Override
	public void print() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
			 	 	 "Balance.java: Entered print().");
		System.out.print("Your current balance is: ");
		System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(balance));
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Balance.java: Exiting print().");
	}

	/**
	 * @return the balance ID based of its hashCode.
	 */
	@Override
	public Integer getID() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
		 	 	 	 "Balance.java: Entered and existing getID().");
		
		return balance.hashCode();
	}

	/**
	 * deposit() contains the logic for making a withdrawal.
	 */
	@Override
	public void withdraw() {
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
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
		logger.debug("Project0/RevatureAccounts/AccountAttributes/" + 
	 	 	 	 "Balance.java: Exiting withdraw().");
	}
}
