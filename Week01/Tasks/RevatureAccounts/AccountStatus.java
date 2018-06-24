package Tasks.RevatureAccounts;

public enum AccountStatus {
		APPROVED() {
			public void display(Account a) {
				a.approved();
			}
		}, 
	
		DENIED() {
			public void display(Account a) { 
				a.denied();
			}
		}, 
	
		PENDING() {
			public void display(Account a) {
				a.pending();
			}
		};

		public abstract void display(Account a);
	};

