package Project0_PartII.RevatureAccounts;

import java.util.*;
import Project0_PartII.*;

/**
 * AdminAccount enumerates all possible statuses an account could be in.
 */
public enum AccountStatus implements LogReference{
		APPROVED("APPROVED") {
			public void display(Account a) {
				logger.debug("Project0_PartII/RevatureAccounts/AccountStatus.java: " + 
		     	 	 	 	 "Entered APPROVED.display().");
				a.approved();
				logger.debug("Project0_PartII/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 	 "Exiting APPROVED.display().");
			}
		}, 
	
		DENIED("DENIED") {
			public void display(Account a) { 
				logger.debug("Project0_PartII/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 "Entered DENIED.display().");
				a.denied();
				logger.debug("Project0_PartII/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 "Exiting DENIED.display().");
			}
		}, 
	
		PENDING("PENDING") {
			public void display(Account a) {
				logger.debug("Project0_PartII/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 	 "Entered PENDING.display().");
				a.pending();
				logger.debug("Project0_PartII/RevatureAccounts/AccountStatus.java: " + 
    	 	 	 	 	 	 "Exiting PENDING.display().");
			}
		};
	
		private String value;
		private static HashMap<String, AccountStatus> hm = new HashMap<>();
		
		private AccountStatus(String s) {
			value = s;
		}
		
		static {
			for (AccountStatus as : AccountStatus.values()) {
	            hm.put(as.value, as);
	        }
		}

		/**
		 * display() chooses the correct view of an account to display
		 * depending on its status.
		 * 
		 * @param a the account to display. 
		 */
		public abstract void display(Account a);
		
		public String getString() {
			return value;
		}
		
		public static AccountStatus getValue(String s) {
				return hm.get(s);
		}
	};

