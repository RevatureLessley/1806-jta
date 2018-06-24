package Project0.RevatureAccounts;

import Project0.*;

public enum AccountStatus implements LogReference{
		APPROVED() {
			public void display(Account a) {
				logger.debug("Project0/RevatureAccounts/AccountStatus.java: " + 
		     	 	 	 	 "Entered APPROVED.display().");
				a.approved();
				logger.debug("Project0/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 	 "Exiting APPROVED.display().");
			}
		}, 
	
		DENIED() {
			public void display(Account a) { 
				logger.debug("Project0/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 "Entered DENIED.display().");
				a.denied();
				logger.debug("Project0/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 "Exiting DENIED.display().");
			}
		}, 
	
		PENDING() {
			public void display(Account a) {
				logger.debug("Project0/RevatureAccounts/AccountStatus.java: " + 
	     	 	 	 	 	 "Entered PENDING.display().");
				a.pending();
				logger.debug("Project0/RevatureAccounts/AccountStatus.java: " + 
    	 	 	 	 	 	 "Exiting PENDING.display().");
			}
		};

		public abstract void display(Account a);
	};

