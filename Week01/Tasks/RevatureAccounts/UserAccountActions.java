package Tasks.RevatureAccounts;

public enum UserAccountActions implements AccountActions {
	DEPOSIT(2) {
		@Override
		public void display() {
			System.out.println("Depositing.");
		}
	},
	
	WITHDRAWAL(3) {
		@Override
		public void display() {
			System.out.println("Withdrawing.");
		}
	};

	Integer index;

	UserAccountActions(Integer i) {
		index = i;
	}

}
